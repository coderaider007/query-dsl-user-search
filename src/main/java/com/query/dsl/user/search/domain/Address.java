package com.query.dsl.user.search.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.query.dsl.user.search.domain.audit.Audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Table(name="ADDRESS")
public class Address extends MainEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6550180310967188092L;
	
	@NotBlank
	private String address1;
	private String address2;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String postalCode;
	
	@NotNull
	private Boolean isMainAddress = Boolean.FALSE;
	
	@NotNull
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private State state;
	
	@NotNull
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Country country;

	@Builder
	public Address(Long id, Long version, Audit audit, String address1, String address2, String city, String postalCode,
			Boolean isMainAddress, Country country) {
		super(id, version, audit);
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.postalCode = postalCode;
		this.isMainAddress = isMainAddress;
		this.country = country;
	}
	
	
}
