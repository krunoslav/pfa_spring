package hr.ponge.pfa.model;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class EntityListener {

	
	private static final XLogger log = XLoggerFactory.getXLogger(EntityListener.class);

	@PrePersist
	@PreUpdate
	public void setAtributes(EntityPfa entitet) {

		entitet.setLastChangeDate(new Date());

		if (entitet.getCreationDate() == null) {
			entitet.setCreationDate(new Date());
		}

	

	}

	
}
