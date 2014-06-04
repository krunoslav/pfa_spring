package hr.ponge.pfa.service.env.user;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.model.User;
import hr.ponge.pfa.service.base.CrudBussinesLogic;
import hr.ponge.util.ParamSelect;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class UserBLImpl extends CrudBussinesLogic<User, UserFilterOptions>
		implements UserBL {

	private static final XLogger log = XLoggerFactory
			.getXLogger(UserBLImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.ponge.pfa.service.env.user.UserBL#userAuthentication(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean userAuthentication(String username, String password)
			throws PfaException {

		List<?> l = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"Select u.username from User u "
								+ " where u.username=:user "
								+ " and u.password=:pass")
				.setParameter("user", username).setParameter("pass", password)
				.list();
		if (l == null || l.size() == 0) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.ponge.pfa.service.env.user.UserBL#userAuthorization(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean userAuthorization(String username, String resource) {
		return true;

	}

	@Override
	protected User createEntityCallback(User entity) throws PfaException {
		return entity;
	}

	@Override
	protected User newEntity() {

		return new User();
	}

	@Override
	protected List<User> readEntityCallback(UserFilterOptions filter) {
		if (!filter.isTenantIdSpecified()) {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"tenantIdMustBeSpecified", new String[] {});
			return null;
		}

		ParamSelect ps = new ParamSelect();
		String sel = "Select u from User u "
				+ " inner join fetch u.tenant as t " + " where u.id != 0 ";

		if (filter.isIdSpecified()) {
			sel = sel + " and u.id=:id ";
			ps.addParametar("id", filter.getId());
		}
		if (filter.isNameSpecified()) {
			sel = sel + " and upper(u.name) like :name ";
			ps.addParametar("name", filter.getName().toUpperCase());
		}
		if (filter.isAddressSpecified()) {
			sel = sel + " and upper(u.address) like :address ";
			ps.addParametar("address", filter.getAddress().toUpperCase());
		}
		if (filter.isSurnameSpecified()) {
			sel = sel + " and upper(u.surname) like :surname ";
			ps.addParametar("surname", filter.getSurname().toUpperCase());
		}

		if (filter.isUsernameSpecified()) {
			sel = sel + " and upper(u.username) like :username ";
			ps.addParametar("username", filter.getUsername().toUpperCase());
		}
		if (filter.isLimitsSpecified()) {
			ps.setFirstRow(filter.getLimits().getFirstRecord());
			ps.setMaxRows(filter.getLimits().getMaxRecords());
		}
		if (filter.isTenantIdSpecified()) {
			sel = sel + " and t.id=:tenId ";
			ps.addParametar("tenId", filter.getTenantId());
		}

		Query query = getSessionFactory().getCurrentSession().createQuery(sel);
		ps.processQuery(query);
		List<User> l = query.list();
		return l;
	}

	@Override
	protected void saveEntityCallback(User entity) {

	}

	@Override
	protected void validateEntity(int operation, User entity)
			throws PfaException {
		if (entity.getName() != null) {
			if (entity.getName().length() < 3) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"fieldMinChars", new String[] { "name", "3" });

			}

			if (entity.getName().length() > 250) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"fieldMaxChars", new String[] { "name", "250" });

			}
		} else {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "name" });

		}

		if (entity.getSurname() != null) {
			if (entity.getSurname().length() < 3) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"fieldMinChars", new String[] { "surname", "3" });

			}
			if (entity.getSurname().length() > 250) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"fieldMaxChars", new String[] { "surname", "250" });

			}
		} else {

			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "surname" });

		}

		if (entity.getUsername() != null) {
			if (entity.getUsername().length() < 5) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"fieldMinChars", new String[] { "username", "5" });

			}
			if (entity.getUsername().length() > 250) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"fieldMaxChars", new String[] { "username", "250" });

			}
		} else {

			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "username" });

		}

		if (entity.getPassword() != null) {
			if (entity.getPassword().length() < 5) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"fieldMinChars", new String[] { "password", "5" });

			}
			if (entity.getPassword().length() > 250) {
				createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
						"fieldMaxChars", new String[] { "password", "250" });

			}
		} else {

			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "password" });

		}

		if (entity.getTenant() == null) {
			createErrorAndSend(PfaException.REQUEST_VALIDATION_ERROR,
					"fieldNotSpecified", new String[] { "tenant" });

		}

		if (messages.size() > 0) {
			return;
		}

		if (entity.getUsername() != null) {
			try {
				String sql = "Select u.id from User u where u.username=:usrname and u.tenant.id=:tenId ";
				ParamSelect ps = new ParamSelect();
				ps.addParametar("usrname", entity.getUsername());
				ps.addParametar("tenId", entity.getTenant().getId());
				sql = sql + " and u.id<>:id ";
				ps.addParametar("id", entity.getId());

				Query q = getSessionFactory().getCurrentSession().createQuery(
						sql);
				ps.processQuery(q);

				List lb = q.list();

				if (lb.size() > 0) {
					createErrorAndSend(
							PfaException.REQUEST_VALIDATION_ERROR,
							"usernameTaken",
							new String[] { entity.getUsername() });

				}
			} catch (HibernateException e) {
				PfaException pf = new PfaException(
						PfaException.PERSISTENCE_ERROR, e);
				throw pf;
			}
		}
	}

	@Override
	protected void deleteEntityCallback(User entity) {

	}

}
