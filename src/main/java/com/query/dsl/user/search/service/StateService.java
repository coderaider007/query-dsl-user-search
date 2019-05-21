package com.query.dsl.user.search.service;

import java.util.List;

import com.query.dsl.user.search.command.StateCommand;

public interface StateService {

	public List<StateCommand> loadAllStates();
}
