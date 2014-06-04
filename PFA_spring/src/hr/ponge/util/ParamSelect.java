package hr.ponge.util;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.type.Type;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


public class ParamSelect {
	
	private static final XLogger log = XLoggerFactory.getXLogger(ParamSelect.class);

    private List<Parameter> parameters = new Vector<Parameter>();

  
    public String clearParam() {
	parameters.clear();
	return "";
    }

    public void addParametar(String paramName, Object data) {

	parameters.add(new Parameter(paramName, data, null, 1));

    }

    public void addParametar(String paramName, Object data, org.hibernate.type.Type type) {
	parameters.add(new Parameter(paramName, data, type, 2));

    }

    public void removeParametar(String paramName) {
	Parameter remove = null;
	for (Parameter p : parameters) {
	    if (p.paramName.equals(paramName)) {
		remove = p;
		break;
	    }
	}

	parameters.remove(remove);

    }

    public void processQuery(Query query) {
	Iterator<Parameter> it = parameters.iterator();
	while (it.hasNext()) {
	    Parameter p = it.next();
	    p.addToQuery(query);

	}

	if (getFirstRow() != null && getFirstRow().intValue() != -1) {
	    query.setFirstResult(getFirstRow());
	}

	if (getMaxRows() != null && getMaxRows().intValue() != -1) {
	    query.setMaxResults(getMaxRows());
	}

    }

    public class Parameter {

	private String paramName;

	private Object data;

	private Type paramType;

	private int t;

	private Parameter(String paramName, Object data, Type type, int tip) {
	    this.paramName = paramName;
	    this.data = data;
	    this.paramType = type;
	    this.t = tip;

	}

	public Parameter(String paramName, Object data) {
	    this.paramName = paramName;
	    this.data = data;
	    t = 1;
	}

	public Parameter(String paramName, Object data, Type type) {
	    this.paramName = paramName;
	    this.data = data;
	    this.paramType = type;
	    t = 2;

	}

	protected void addToQuery(Query query) {
	    switch (t) {
	    case 2:
		query.setParameter(paramName, data, paramType);

		break;

	    default:
		query.setParameter(paramName, data);
		break;
	    }

	}

	public Object getData() {
	    return data;
	}

    }

    public List<Parameter> getParameters() {
	return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
	this.parameters = parameters;
    }

    private Integer firstRow;

    public Integer getFirstRow() {
	return firstRow;
    }

    public void setFirstRow(Integer firstRow) {
	this.firstRow = firstRow;
    }

    private Integer maxRows;

    public Integer getMaxRows() {
	return maxRows;
    }

    public void setMaxRows(Integer maxRows) {
	this.maxRows = maxRows;
    }
}
