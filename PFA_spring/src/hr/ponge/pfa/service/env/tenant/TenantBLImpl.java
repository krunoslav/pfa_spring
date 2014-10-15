package hr.ponge.pfa.service.env.tenant;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.model.Tenant;
import hr.ponge.pfa.service.base.CrudBussinesLogic;
import hr.ponge.pfa.service.base.PfaMessage;
import hr.ponge.util.ParamSelect;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class TenantBLImpl extends
		CrudBussinesLogic<Tenant, TenantFilterOptions> implements TenantBL {

	private static final XLogger log = XLoggerFactory
			.getXLogger(TenantBLImpl.class);

	private boolean nameExists(String name, long id) throws PfaException {
		String sql = "Select t.id from Tenant t where t.name=:name and t.id<>:id ";
		ParamSelect ps = new ParamSelect();
		ps.addParametar("name", name);
		ps.addParametar("id", id);

		Query q = getSessionFactory().getCurrentSession().createQuery(sql);
		ps.processQuery(q);
		@SuppressWarnings("rawtypes")
		List l = q.list();
		if (l.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	protected Tenant newEntity() {

		return new Tenant();
	}

	@Override
	protected void validateEntity(int operation, Tenant tenant)
			throws PfaException {
	
			if(tenant.getName().length()<5){
				createAndSendMessage(PfaMessage.MESSAGE_TYPE_WARN,
						PfaException.REQUEST_VALIDATION_ERROR, "tenantNameShouldBeMoreThan5Chars",  new String[] { tenant.getName() });
			}
		
			
			if ((operation == OPERATION_INSERT || operation==OPERATION_UPDATE)
					&& nameExists(tenant.getName(), tenant.getId())) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"tenantNameTaken", new String[] { tenant.getName() });

			}
			

		

	}

	@Override
	protected Tenant createEntityCallback(Tenant entity) throws PfaException {

		return entity;
	}

	@Override
	protected List<Tenant> readEntityCallback(TenantFilterOptions filter) {
		ParamSelect ps = new ParamSelect();
		String sel = "Select distinct t from Tenant t " + " where t.id != 0 ";

		if (filter.isIdSpecified()) {
			sel = sel + " and t.id=:id ";
			ps.addParametar("id", filter.getId());
		}
		if (filter.isNameSpecified()) {
			sel = sel + " and upper(t.name) like :name ";
			ps.addParametar("name", filter.getName().toUpperCase());
		}

		if (filter.isLimitsSpecified()) {
			ps.setFirstRow(filter.getLimits().getFirstRecord());
			ps.setMaxRows(filter.getLimits().getMaxRecords());
		}

		Query query = getSessionFactory().getCurrentSession().createQuery(sel);
		ps.processQuery(query);
		@SuppressWarnings("unchecked")
		List<Tenant> l = query.list();
		return l;
	}

	@Override
	protected void saveEntityCallback(Tenant entity) {

	}

	@Override
	protected void deleteEntityCallback(Tenant entity) {

	}

}
