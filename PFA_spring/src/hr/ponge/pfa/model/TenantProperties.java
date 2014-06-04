package hr.ponge.pfa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SequenceGenerator(name = "TENANT_PROP_SEQ", sequenceName = "PFA_TENANT_PROP_SEQUENCE", allocationSize = 1, initialValue=1000)
@Entity
@Table(name="PFA_TENANT_PROPERTIES")
public class TenantProperties extends EntityPfa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TENANT_PROP_SEQ")
	public long getId() {

		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;

	}
	
	private Tenant tenant;

	@OneToOne(mappedBy="tenantProperties")
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
	
}
