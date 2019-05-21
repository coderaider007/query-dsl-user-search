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
@Table(name="POSITION")
public class Position extends MainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2779389158950802344L;
	private String position;
	
	@Builder
	public Position(Long id, Long version, Audit audit, String position) {
		super(id, version, audit);
		this.position = position;
	}
	
	
}
