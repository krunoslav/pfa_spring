package hr.ponge.pfa.model;

import hr.ponge.pfa.web.Severity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SequenceGenerator(name = "TENANT_SEQ", sequenceName = "PFA_TENANT_SEQUENCE", allocationSize = 1, initialValue = 1000)
@Entity
@Table(name = "PFA_TENANT")
public class Tenant extends EntityPfa {

	@Override
	public String toString() {
		return "Tenant [id=" + id + ", name=" + name + "]";
	}

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

	@Size(min = 3, max = 254)
	@NotNull
	private String name = "";

	@Column(name = "tenant_name", unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private TenantProperties tenantProperties = new TenantProperties();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_properties_id", unique = true)
	public TenantProperties getTenantProperties() {
		return tenantProperties;
	}

	public void setTenantProperties(TenantProperties tenantProperties) {
		this.tenantProperties = tenantProperties;
	}

}
