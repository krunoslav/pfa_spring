package hr.ponge.pfa.service.base;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.PfaRollbackException;
import hr.ponge.pfa.model.EntityPfa;

import java.util.Date;
import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public abstract class CrudBussinesLogic<T extends EntityPfa, E extends EntityFilterOptions>
		extends ServiceBase implements CrudBussinesLogicIntf<T, E> {

	public static final int OPERATION_CREATE = 1;
	public static final int OPERATION_READ = 2;
	public static final int OPERATION_UPDATE = 3;
	public static final int OPERATION_INSERT = 4;
	public static final int OPERATION_DELETE = 5;
	
	private static final XLogger log = XLoggerFactory
			.getXLogger(CrudBussinesLogic.class);

	// CREATE
	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.ponge.pfa.service.CrudBussinesLogicIntf#createEntity()
	 */

	public T createEntity() throws PfaException {
		messages.get().clear();
		T entity = newEntity();
		entity.setCreationDate(new Date());
		entity.setLastChangeDate(new Date());
		entity = createEntityCallback(entity);

		return entity;
	}

	protected abstract T createEntityCallback(T entity) throws PfaException;

	protected abstract T newEntity();

	// READ

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.ponge.pfa.service.CrudBussinesLogicIntf#readEntity(hr.ponge.pfa.service
	 * .EntityFilterOptions)
	 */
	@Override
	public List<T> readEntity(E filter) throws PfaException {
		messages.get().clear();

		List<T> res = readEntityCallback(filter);

		return res;
	}

	protected abstract List<T> readEntityCallback(E filter);

	// UPDATE
	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.ponge.pfa.service.CrudBussinesLogicIntf#saveEntity(T)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T saveEntity(T entity) throws PfaException {
		messages.get().clear();
		if (entity.getId() != 0
				&& !getSessionFactory().getCurrentSession().contains(entity)) {
		entity=(T) getSessionFactory().getCurrentSession().merge(entity);
		}
		if (entity.getId() == 0) {
			validateEntity(OPERATION_INSERT, entity);
		} else {
			validateEntity(OPERATION_UPDATE, entity);
		}

		saveEntityCallback(entity);
		if (checkForRollbackErrors(messages.get())) {
			throw new PfaRollbackException();
		}
		entity.setLastChangeDate(new Date());
		getSessionFactory().getCurrentSession().persist(entity);
		
		return entity;
	}

	protected abstract void saveEntityCallback(T entity);

	protected abstract void validateEntity(int operation, T entity)
			throws PfaException;

	// DELETE

	@SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.ponge.pfa.service.CrudBussinesLogicIntf#deleteEntity(T)
	 */
	@Override
	public void deleteEntity(T entity) throws PfaException {
		messages.get().clear();
		
		if (entity.getId() == 0) {
			return;
		}

		if (!getSessionFactory().getCurrentSession().contains(entity)) {
			entity=(T) getSessionFactory().getCurrentSession().merge(entity);
		}
		validateEntity(OPERATION_DELETE, entity);
		if (checkForRollbackErrors(messages.get())) {
			throw new PfaRollbackException();
		}
		deleteEntityCallback(entity);
		getSessionFactory().getCurrentSession().delete(entity);
		
		
	}

	protected abstract void deleteEntityCallback(T entity);

}
