package com.query.dsl.user.search.domain.listener;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.query.dsl.user.search.domain.audit.Audit;
import com.query.dsl.user.search.domain.audit.Auditable;



public class AuditListener {

	@PrePersist
    public void setCreatedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();
 
        if(audit == null) {
            audit = new Audit();
        }
        Date date = Calendar.getInstance().getTime();
        audit.setCreateDate(date);
        audit.setUpdateDate(date);
        auditable.setAudit(audit);
               
    }
 
    @PreUpdate
    public void setUpdatedOn(Auditable auditable) {
        Audit audit = auditable.getAudit();
        audit.setUpdateDate(Calendar.getInstance().getTime());
    }
}
