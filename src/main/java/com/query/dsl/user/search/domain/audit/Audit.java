package com.query.dsl.user.search.domain.audit;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Embeddable
@Data
public class Audit {

	@Temporal(value=TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createDate;
	
	@Temporal(value=TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateDate;
}
