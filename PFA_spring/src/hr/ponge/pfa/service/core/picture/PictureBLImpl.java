package hr.ponge.pfa.service.core.picture;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.model.Picture;
import hr.ponge.pfa.service.base.CrudBussinesLogic;
import hr.ponge.util.ParamSelect;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class PictureBLImpl extends
		CrudBussinesLogic<Picture, PictureFilterOptions> implements PictureBL {

	private static final XLogger log = XLoggerFactory
			.getXLogger(PictureBLImpl.class);

	@Override
	protected Picture createEntityCallback(Picture entity) throws PfaException {

		return entity;
	}

	@Override
	protected Picture newEntity() {

		return new Picture();
	}

	@Override
	protected List<Picture> readEntityCallback(PictureFilterOptions filter) {
		ParamSelect ps = new ParamSelect();
		String sel = "Select p from Picture p "
				+ " inner join fetch p.user as u"
				+ " inner join fetch u.tenant as t " + " where p.id != 0 ";

		if (filter.isIdSpecified()) {
			sel = sel + " and p.id=:id ";
			ps.addParametar("id", filter.getId());
		}
		if (filter.isTenantIdSpecified()) {
			sel = sel + " and t.id=:tId ";
			ps.addParametar("tId", filter.getTenantId());
		}
		if (filter.isUserIdSpecified()) {
			sel = sel + " and u.id=:uId ";
			ps.addParametar("uId", filter.getUserId());
		}
		if (filter.isLimitsSpecified()) {
			ps.setFirstRow(filter.getLimits().getFirstRecord());
			ps.setMaxRows(filter.getLimits().getMaxRecords());
		}

		Query query = getSessionFactory().getCurrentSession().createQuery(sel);
		ps.processQuery(query);
		List<Picture> l = query.list();

		return l;
	}

	@Override
	protected void saveEntityCallback(Picture entity) {

	}

	@Override
	protected void validateEntity(int operation, Picture entity)
			throws PfaException {
		if (entity.getMd5Code() != null) {
			if (!entity.getMd5Code().toUpperCase().matches("[a-fA-F0-9]{32}")) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"md5WorngFormat", new String[] { entity.getMd5Code() });
			}
		} else {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "Md5Code" });
		}

		if (entity.getPicByteSize() != 0) {
			// test picture size
		} else {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "PicByteSize" });
		}
		if (entity.getPicture() == null || entity.getPicture().length == 0) {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "Picture" });
		}
		if (entity.getUser() != null) {
			List l = getSessionFactory()
					.getCurrentSession()
					.createQuery(
							"Select u.id from User u where u.id="
									+ entity.getUser().getId()).list();
			if (l.size() != 1) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"noUserFoundWithId", new String[] { entity.getUser()
								.getId() + "" });
			}
		} else {

			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "UserId" });

		}
		if (entity.getxSize() == 0) {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "Xsize" });
		}
		if (entity.getySize() == 0) {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "Ysize" });
		}

		if (entity.getMd5Code() != null
				&& (entity.getPicture() != null && entity.getPicture().length > 0)) {
			String md5 = entity.getMd5Code();
			String picMd = "";
			byte[] dh = entity.getPicture();

			picMd = DigestUtils.md5Hex(dh);

			if (!md5.equalsIgnoreCase(picMd)) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"md5DoesNotMatchPicture", new String[] { md5, picMd });
			}

		}

	}

	@Override
	protected void deleteEntityCallback(Picture entity) {

	}
}
