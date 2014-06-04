package hr.ponge.pfa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

@EntityListeners(EntityListener.class)
@MappedSuperclass
public abstract class EntityPfa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final XLogger log = XLoggerFactory.getXLogger(EntityPfa.class);

	@Transient
	public abstract long getId();

	public abstract void setId(long id);

	private Date creationDate;

	@Column(name = "creation_date", updatable = false, columnDefinition = "timestamp with time zone")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	private Date lastChangeDate;

	@Column(name = "last_change_date",columnDefinition = "timestamp with time zone")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

}
