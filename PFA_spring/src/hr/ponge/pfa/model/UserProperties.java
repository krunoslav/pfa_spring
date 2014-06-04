package hr.ponge.pfa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SequenceGenerator(name = "USER_SEQ", sequenceName = "PFA_USER_SEQUENCE", allocationSize = 1, initialValue=1000)
@Entity
@Table(name="PFA_USER_PROPERTIES")
public class UserProperties extends EntityPfa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	private User user;

	@OneToOne(mappedBy = "userProperties")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
