package com.query.dsl.user.search.service.Impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import com.query.dsl.user.search.command.StatusCommand;
import com.query.dsl.user.search.converter.StatusToStatusCommand;
import com.query.dsl.user.search.domain.Status;
import com.query.dsl.user.search.repository.StatusRepository;
import com.query.dsl.user.search.service.StatusService;

public class StatusServiceImplTest {
	
	@Mock
	private StatusRepository statusRepository;
	
	private StatusToStatusCommand statusToStatusCommand;
	
	private StatusService statusService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.statusToStatusCommand = new StatusToStatusCommand();
		this.statusService = new StatusServiceImpl(statusRepository, statusToStatusCommand);
	}

	@Test
	public void testLoadAllStatuses() {
		ArrayList<Status> statuses = new ArrayList<>();
		
		Status status = new Status();
		status.setId(Long.valueOf(1));
		status.setStatus("INACTIVE");
		statuses.add(status);
		
		Status status1 = new Status();
		status1.setId(Long.valueOf(2));
		status1.setStatus("ACTIVE");
		statuses.add(status1);
		
		Status status2 = new Status();
		status2.setId(Long.valueOf(3));
		status2.setStatus("LAYOFF");
		statuses.add(status2);
		
		when(this.statusRepository.findAll(any(Sort.class))).thenReturn(statuses);
		
		List<StatusCommand> statusCommands = this.statusService.loadAllStatuses();
		
		assertNotNull(statusCommands);
		assertEquals(Long.valueOf(statusCommands.size()), Long.valueOf(3));
	}

}
