package com.query.dsl.user.search.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.query.dsl.user.search.command.StatusCommand;
import com.query.dsl.user.search.converter.StatusToStatusCommand;
import com.query.dsl.user.search.domain.Status;
import com.query.dsl.user.search.repository.StatusRepository;
import com.query.dsl.user.search.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	private final StatusRepository statusRepository;
	private final StatusToStatusCommand statusToStatusCommand;
	
	public StatusServiceImpl(StatusRepository statusRepository, StatusToStatusCommand statusToStatusCommand) {
		super();
		this.statusRepository = statusRepository;
		this.statusToStatusCommand = statusToStatusCommand;
	}

	@Override
	public List<StatusCommand> loadAllStatuses() {
		List<String> properties = new ArrayList<>();
		properties.add("status");
		Sort sort = new Sort(Sort.Direction.ASC, properties);
		Iterable<Status> statuses = this.statusRepository.findAll(sort); 
		if(statuses != null) {
			List<StatusCommand> statusCommands = new ArrayList<>();
			statuses.forEach(status -> statusCommands.add(this.statusToStatusCommand.convert(status)));
			return statusCommands;
		}
		return null;
	}

}
