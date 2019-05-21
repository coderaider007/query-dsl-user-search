package com.query.dsl.user.search.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name="EMPLOYEE")
public class Employee extends MainEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5885356490857476694L;
	@NotNull
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@Temporal(value=TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@Temporal(value=TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	@NotNull
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Address address;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@NotNull
	private Position position;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@NotNull
	private Status status;

	@Builder
	public Employee(Long id, Long version, Audit audit, String firstName, String lastName, Date startDate, Date endDate,
			Address address, Position position, Status status) {
		super(id, version, audit);
		this.firstName = firstName;
		this.lastName = lastName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.position = position;
		this.status = status;
	}

	
	
	
	
}
