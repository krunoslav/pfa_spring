package hr.ponge.pfa.service.env.user;

import hr.ponge.pfa.PfaException;
import hr.ponge.pfa.model.User;
import hr.ponge.pfa.service.base.CrudBussinesLogicIntf;

public interface UserBL extends CrudBussinesLogicIntf<User, UserFilterOptions> {

	public boolean userAuthentication(String username, String password)
			throws PfaException;

	public boolean userAuthorization(String username, String resource);

}