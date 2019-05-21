package com.query.dsl.user.search.service.Impl;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import com.query.dsl.user.search.command.PositionCommand;
import com.query.dsl.user.search.converter.PositionToPositionCommand;
import com.query.dsl.user.search.domain.Position;
import com.query.dsl.user.search.repository.PositionRepository;
import com.query.dsl.user.search.service.PositionService;

public class PositionServiceImplTest {

	@Mock
	private PositionRepository positionRepository;
	
	private PositionToPositionCommand positionToPositionCommand;
	
	private PositionService positionService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.positionToPositionCommand = new PositionToPositionCommand();
		this.positionService = new PositionServiceImpl(positionRepository, positionToPositionCommand);
	}

	@Test
	public void testLoadAllPositions() {
		ArrayList<Position> positions = new ArrayList<>();
		
		Position position = new Position();
		position.setId(Long.valueOf(1));
		position.setPosition("Janitor");
		positions.add(position);
		
		Position position1 = new Position();
		position1.setId(Long.valueOf(2));
		position1.setPosition("Software Engineer");
		positions.add(position1);
		
		Position position2 = new Position();
		position2.setId(Long.valueOf(3));
		position2.setPosition("Administrator");
		positions.add(position2);
		
		when(this.positionRepository.findAll(any(Sort.class))).thenReturn(positions);
		
		List<PositionCommand> positionCommands = this.positionService.loadAllPositions();
		
		assertNotNull(positionCommands);
		assertEquals(Long.valueOf(3), Long.valueOf(positionCommands.size()));
	}

}
