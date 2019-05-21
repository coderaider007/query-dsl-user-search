package com.query.dsl.user.search.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.command.PositionCommand;
import com.query.dsl.user.search.domain.Position;

public class PositionToPositionCommandTest {
	
	private PositionToPositionCommand positionToPositionCommand;

	@Before
	public void setUp() throws Exception {
		this.positionToPositionCommand = new PositionToPositionCommand();
	}

	@Test
	public void testConvert() {
		Position position = new Position();
		position.setId(Long.valueOf(3));
		position.setPosition("Janitor");
		
		PositionCommand positionCommand = this.positionToPositionCommand.convert(position);
		
		assertNotNull(positionCommand);
		assertEquals("Janitor", position.getPosition());
	}

}
