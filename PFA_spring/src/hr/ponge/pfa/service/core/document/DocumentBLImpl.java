package hr.ponge.pfa.service.core.document;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.model.Document;
import hr.ponge.pfa.service.base.CrudBussinesLogic;
import hr.ponge.util.ParamSelect;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class DocumentBLImpl extends
		CrudBussinesLogic<Document, DocumentFilterOptions> implements
		DocumentBL {

	private static final XLogger log = XLoggerFactory
			.getXLogger(DocumentBLImpl.class);

	@Override
	protected Document createEntityCallback(Document entity)
			throws PfaException {
		return entity;
	}

	@Override
	protected Document newEntity() {
		return new Document();
	}

	@Override
	protected List<Document> readEntityCallback(DocumentFilterOptions filter) {

		ParamSelect ps = new ParamSelect();
		String sel = "Select d from Document d "
				+ " inner join fetch d.user as u "
				+ " inner join u.tenant as t " + " where d.id != 0 ";

		if (filter.isIdSpecified()) {
			sel = sel + " and p.id=:id ";
			ps.addParametar("id", filter.getId());
		}

		if (filter.isUserIdSpecified()) {
			sel = sel + " and u.id=:uId ";
			ps.addParametar("uId", filter.getUserId());
		}

		if (filter.isDescriptionSpecified()) {
			sel = sel + " and upper(d.description) like :desc ";
			ps.addParametar("desc", "%" + filter.getDescription().toUpperCase()
					+ "%");
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
		List<Document> l = query.list();
		return l;
	}

	@Override
	protected void saveEntityCallback(Document entity) {

	}

	@Override
	protected void validateEntity(int operation, Document entity)
			throws PfaException {
		if (entity.getDescription() == null) {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "Description" });
		}

		if (entity.getDocumentForm() == null) {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "DocumentForm" });
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

	}

	@Override
	protected void deleteEntityCallback(Document entity) {

	}
}
