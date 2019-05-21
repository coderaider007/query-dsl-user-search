package com.query.dsl.user.search.domain.audit;

public interface Auditable {

	Audit getAudit();
	 
    void setAudit(Audit audit);
}
