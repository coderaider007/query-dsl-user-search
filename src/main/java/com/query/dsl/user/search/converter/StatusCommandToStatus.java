package com.query.dsl.user.search.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.StatusCommand;
import com.query.dsl.user.search.domain.Status;

@Component
public class StatusCommandToStatus implements Converter<StatusCommand, Status> {

	@Override
	public Status convert(StatusCommand source) {
		if(source == null) {
			return null;
		}
		Status status = new Status();
		status.setId(source.getId());
		status.setStatus(source.getStatus());
		return status;
	}

}
