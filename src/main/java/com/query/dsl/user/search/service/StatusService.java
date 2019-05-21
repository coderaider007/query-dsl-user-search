package com.query.dsl.user.search.service;

import java.util.List;

import com.query.dsl.user.search.command.StatusCommand;

public interface StatusService {
	
	public List<StatusCommand> loadAllStatuses();
}
