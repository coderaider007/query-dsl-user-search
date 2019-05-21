package com.query.dsl.user.search.converter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.command.StatusCommand;
import com.query.dsl.user.search.domain.Status;

public class StatusToStatusCommandTest {

	private StatusToStatusCommand statusToStatusCommand;
	
	@Before
	public void setUp() throws Exception {
		this.statusToStatusCommand = new StatusToStatusCommand();
	}

	@Test
	public void testConvert() {
		Status status = new Status();
		status.setId(Long.valueOf(5));
		status.setStatus("INACTIVE");
		
		StatusCommand statusCommand = this.statusToStatusCommand.convert(status);
		
		assertNotNull(statusCommand);
		assertEquals(Long.valueOf(5), statusCommand.getId());
		assertEquals("INACTIVE", statusCommand.getStatus());
	}

}
