package com.query.dsl.user.search.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.query.dsl.user.search.command.PositionCommand;
import com.query.dsl.user.search.converter.PositionToPositionCommand;
import com.query.dsl.user.search.domain.Position;
import com.query.dsl.user.search.repository.PositionRepository;
import com.query.dsl.user.search.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {
	
	private final PositionRepository positionRepository;
	private final PositionToPositionCommand positionToPositionCommand;
	
	public PositionServiceImpl(PositionRepository positionRepository, PositionToPositionCommand positionToPositionCommand) {
		super();
		this.positionRepository = positionRepository;
		this.positionToPositionCommand = positionToPositionCommand;
	}
	
	@Override
	public List<PositionCommand> loadAllPositions() {
		List<String> properties = new ArrayList<>();
		properties.add("position");
		Sort sort = new Sort(Sort.Direction.ASC, properties);
		Iterable<Position> positions = this.positionRepository.findAll(sort); 
		if(positions != null) {
			List<PositionCommand> positionCommands = new ArrayList<>();
			positions.forEach(position -> {
				positionCommands.add(this.positionToPositionCommand.convert(position));
			});
			return positionCommands;
		}
		return null;
	}

}
