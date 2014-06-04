package hr.ponge.pfa.service.core.pictureTmpl;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.model.TemplatePicture;
import hr.ponge.pfa.service.base.CrudBussinesLogic;
import hr.ponge.util.ParamSelect;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class PictureTmplBLImpl extends
		CrudBussinesLogic<TemplatePicture, TemplatePictureFilterOptions>
		implements PictureTmplBL {

	private static final XLogger log = XLoggerFactory
			.getXLogger(PictureTmplBLImpl.class);

	@Override
	protected TemplatePicture createEntityCallback(TemplatePicture entity)
			throws PfaException {
		return entity;
	}

	@Override
	protected TemplatePicture newEntity() {
		return new TemplatePicture();
	}

	@Override
	protected List<TemplatePicture> readEntityCallback(
			TemplatePictureFilterOptions filter) {
		ParamSelect ps = new ParamSelect();
		String sel = "Select p from TemplatePicture p "
				+ " inner join fetch p.tenant as t " + " where p.id != 0 ";

		if (filter.isIdSpecified()) {
			sel = sel + " and p.id=:id ";
			ps.addParametar("id", filter.getId());
		}
		if (filter.isTenantIdSpecified()) {
			sel = sel + " and t.id=:tId ";
			ps.addParametar("tId", filter.getTenantId());
		}

		if (filter.isLimitsSpecified()) {
			ps.setFirstRow(filter.getLimits().getFirstRecord());
			ps.setMaxRows(filter.getLimits().getMaxRecords());
		}

		Query query = getSessionFactory().getCurrentSession().createQuery(sel);
		ps.processQuery(query);
		List<TemplatePicture> l = query.list();
		return l;
	}

	@Override
	protected void saveEntityCallback(TemplatePicture entity) {

	}

	@Override
	protected void validateEntity(int operation, TemplatePicture entity)
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
		if (entity.getPicture() == null || entity.getPicture().length > 0) {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "Picture" });
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
	protected void deleteEntityCallback(TemplatePicture entity) {

	}

}
