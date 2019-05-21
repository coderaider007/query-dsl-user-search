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
@Table(name="STATUS")
public class Status extends MainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6752465346129051250L;
	
	private String status;

	@Builder
	public Status(Long id, Long version, Audit audit, String status) {
		super(id, version, audit);
		this.status = status;
	}
	
}
