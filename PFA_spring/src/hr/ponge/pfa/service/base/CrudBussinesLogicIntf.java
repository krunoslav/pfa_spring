package hr.ponge.pfa.service.base;

import java.util.List;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.model.EntityPfa;

public interface CrudBussinesLogicIntf<T extends EntityPfa, E extends EntityFilterOptions> extends Service {

	public T createEntity() throws PfaException;

	public List<T> readEntity(E filter) throws PfaException;

	public T saveEntity(T entity) throws PfaException;

	public void deleteEntity(T entity) throws PfaException;

}