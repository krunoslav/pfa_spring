package hr.ponge.pfa.service.base;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.model.EntityPfa;

import java.util.Date;
import java.util.List;

public abstract class CrudBussinesLogic<T extends EntityPfa, E extends EntityFilterOptions>
		extends ServiceBase implements CrudBussinesLogicIntf<T, E> {

	public static final int OPERATION_CREATE = 1;
	public static final int OPERATION_READ = 2;
	public static final int OPERATION_UPDATE = 3;
	public static final int OPERATION_INSERT = 4;
	public static final int OPERATION_DELETE = 5;

	// CREATE
	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.ponge.pfa.service.CrudBussinesLogicIntf#createEntity()
	 */

	public T createEntity() throws PfaException {
		messages.clear();
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
		messages.clear();

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
	@Override
	public T saveEntity(T entity) throws PfaException {
		messages.clear();
		if (entity.getId() != 0
				&& !getSessionFactory().getCurrentSession().contains(entity)) {
			throw new PfaException(PfaException.ENTITY_NOT_IN_CONTEXT);
		}
		if (entity.getId() == 0) {
			validateEntity(OPERATION_INSERT, entity);
		} else {
			validateEntity(OPERATION_UPDATE, entity);
		}

		saveEntityCallback(entity);
		if (checkForRollbackErrors(messages)) {
			throw new PfaException(PfaException.VALIDATION_FAILED_ROLLBACK);
		}
		entity.setLastChangeDate(new Date());
		getSessionFactory().getCurrentSession().persist(entity);

		return entity;
	}

	protected abstract void saveEntityCallback(T entity);

	protected abstract void validateEntity(int operation, T entity)
			throws PfaException;

	// DELETE

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.ponge.pfa.service.CrudBussinesLogicIntf#deleteEntity(T)
	 */
	@Override
	public void deleteEntity(T entity) throws PfaException {
		messages.clear();
		
		if (entity.getId() == 0) {
			return;
		}

		if (!getSessionFactory().getCurrentSession().contains(entity)) {
			throw new PfaException(PfaException.ENTITY_NOT_IN_CONTEXT);
		}
		validateEntity(OPERATION_DELETE, entity);
		deleteEntityCallback(entity);
		getSessionFactory().getCurrentSession().delete(entity);
		
		
	}

	protected abstract void deleteEntityCallback(T entity);

}
