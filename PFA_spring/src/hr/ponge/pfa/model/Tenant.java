package hr.ponge.pfa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SequenceGenerator(name = "TENANT_SEQ", sequenceName = "PFA_TENANT_SEQUENCE", allocationSize = 1, initialValue=1000)
@Entity
@Table(name="PFA_TENANT")
public class Tenant extends EntityPfa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TENANT_SEQ")
	public long getId() {

		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;

	}

	private String name = "";

	@Column(name = "tenant_name", unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	private TenantProperties tenantProperties=new TenantProperties();

	@OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="tenant_properties_id",unique=true)
	public TenantProperties getTenantProperties() {
		return tenantProperties;
	}

	public void setTenantProperties(TenantProperties tenantProperties) {
		this.tenantProperties = tenantProperties;
	}
	
	

}
