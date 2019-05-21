package com.query.dsl.user.search.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.command.StatusCommand;
import com.query.dsl.user.search.domain.Status;

public class StatusCommandToStatusTest {

	private StatusCommandToStatus statusCommandToStatus;
	
	@Before
	public void setUp() throws Exception {
		this.statusCommandToStatus = new StatusCommandToStatus();
	}

	@Test
	public void testConvert() {
		StatusCommand statusCommand = new StatusCommand();
		statusCommand.setId(Long.valueOf(2));
		statusCommand.setStatus("Active");
		
		Status status = this.statusCommandToStatus.convert(statusCommand);
		
		assertNotNull(status);
		assertEquals("Active", status.getStatus());
	}

}
