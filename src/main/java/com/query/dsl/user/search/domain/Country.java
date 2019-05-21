package com.query.dsl.user.search.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name="COUNTRY")
public class Country extends MainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6422560479588011467L;
	private String name;
	
	@Builder
	public Country(Long id, Long version, Audit audit, String name) {
		super(id, version, audit);
		this.name = name;
	}
	
	
}
