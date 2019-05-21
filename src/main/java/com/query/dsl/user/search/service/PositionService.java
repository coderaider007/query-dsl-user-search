package com.query.dsl.user.search.service;

import java.util.List;

import com.query.dsl.user.search.command.PositionCommand;

public interface PositionService {

	public List<PositionCommand> loadAllPositions();
}
