package com.query.dsl.user.search.domain;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.query.dsl.user.search.domain.audit.Audit;
import com.query.dsl.user.search.domain.audit.Auditable;
import com.query.dsl.user.search.domain.listener.AuditListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditListener.class)
public class MainEntity implements Serializable, Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1594225914305819507L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@Embedded
	private Audit audit;
}
