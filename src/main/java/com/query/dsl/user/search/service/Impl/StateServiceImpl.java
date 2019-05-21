package com.query.dsl.user.search.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.query.dsl.user.search.command.StateCommand;
import com.query.dsl.user.search.converter.StateToStateCommand;
import com.query.dsl.user.search.domain.State;
import com.query.dsl.user.search.repository.StateRepository;
import com.query.dsl.user.search.service.StateService;

@Service
public class StateServiceImpl implements StateService {

	private final StateRepository stateRepository;
	private final StateToStateCommand stateToStateCommand;
	
	public StateServiceImpl(StateRepository stateRepository, StateToStateCommand stateToStateCommand) {
		super();
		this.stateRepository = stateRepository;
		this.stateToStateCommand = stateToStateCommand;
	}

	@Override
	public List<StateCommand> loadAllStates() {
		List<StateCommand> stateCommands = new ArrayList<>();
		Iterable<State> states = this.stateRepository.findAll(); 
		states.forEach(state -> stateCommands.add(this.stateToStateCommand.convert(state)));
		return stateCommands;
	}

}
