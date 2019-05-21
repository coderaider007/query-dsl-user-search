package com.query.dsl.user.search.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.command.PositionCommand;
import com.query.dsl.user.search.domain.Position;

public class PositionCommandToPositionTest {

	private PositionCommandToPosition positionCommandToPosition;
	
	@Before
	public void setUp() throws Exception {
		this.positionCommandToPosition = new PositionCommandToPosition();
	}

	@Test
	public void testConvert() {
		PositionCommand positionCommand = new PositionCommand();
		positionCommand.setId(Long.valueOf(1));
		positionCommand.setPosition("Engineer");
		
		Position position = this.positionCommandToPosition.convert(positionCommand);
		
		assertNotNull(position);
		assertEquals("Engineer", position.getPosition());
	}

}
