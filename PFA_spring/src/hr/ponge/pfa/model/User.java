package hr.ponge.pfa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SequenceGenerator(name = "USER_SEQ", sequenceName = "PFA_USER_SEQUENCE", allocationSize = 1, initialValue=1000)
@Entity
@Table(name="PFA_USER")
public class User extends EntityPfa {
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

	private Tenant tenant;

	@ManyToOne
	@JoinColumn(name = "tenant_id", nullable = false, updatable = false)
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	private String name = "";

	@Column(name = "users_name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String surname = "";

	@Column(name = "surname", length = 50)
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * This is actually email
	 */
	private String username = "";

	@Column(name = "username", length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String password = "";

	@Column(name = "users_password", length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String address = "";

	@Column(name = "address", length = 300)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private Date dateOfBirth;

	@Column(name = "date_of_birth", columnDefinition = "timestamp with time zone")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	private Date lastAccessTime;

	@Column(name = "last_access_time", columnDefinition = "timestamp with time zone")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	private UserProperties userProperties;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_properties", unique = true)
	public UserProperties getUserProperties() {
		return userProperties;
	}

	public void setUserProperties(UserProperties userProperties) {
		this.userProperties = userProperties;
	}

}
