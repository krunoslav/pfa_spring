package hr.ponge.pfa.service.util;

import java.net.URL;

import org.hibernate.SQLQuery;

import hr.ponge.pfa.service.base.ServiceBase;

public class DbUtilImpl extends ServiceBase implements DbUtil {

	/* (non-Javadoc)
	 * @see hr.ponge.pfa.service.util.DbUtil#executeSqlScript(java.lang.String)
	 */
	@Override
	public int executeSqlScript(String sqlScript) {

		SQLQuery q = getSessionFactory().getCurrentSession().createSQLQuery(
				"BEGIN; " + sqlScript + "END;");

		int res = q.executeUpdate();
		return res;
	}

}
