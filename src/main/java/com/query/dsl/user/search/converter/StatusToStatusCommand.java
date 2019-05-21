package com.query.dsl.user.search.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.StatusCommand;
import com.query.dsl.user.search.domain.Status;

@Component
public class StatusToStatusCommand implements Converter<Status, StatusCommand> {

	@Override
	public StatusCommand convert(Status source) {
		if(source == null) {
			return null;
		}
		StatusCommand statusCommand = new StatusCommand();
		statusCommand.setId(source.getId());
		statusCommand.setStatus(source.getStatus());
		return statusCommand;
	}

}
