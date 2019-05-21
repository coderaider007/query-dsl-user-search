package com.query.dsl.user.search.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
@Table(name="STATE")
public class State extends MainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2165927335486146420L;

	private String name;
	
	@ManyToOne
	private Country country;

	@Builder
	public State(Long id, Long version, Audit audit, String name, Country country) {
		super(id, version, audit);
		this.name = name;
		this.country = country;
	}
	
}
