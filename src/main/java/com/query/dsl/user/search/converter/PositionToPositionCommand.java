package com.query.dsl.user.search.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.PositionCommand;
import com.query.dsl.user.search.domain.Position;

@Component
public class PositionToPositionCommand implements Converter<Position, PositionCommand> {

	@Override
	public PositionCommand convert(Position source) {
		if(source == null) {
			return null;
		}
		PositionCommand positionCommand = new PositionCommand();
		positionCommand.setId(source.getId());
		positionCommand.setPosition(source.getPosition());
		return positionCommand;
	}

}
