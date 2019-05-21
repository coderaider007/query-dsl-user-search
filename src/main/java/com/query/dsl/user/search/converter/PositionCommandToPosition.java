package com.query.dsl.user.search.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.PositionCommand;
import com.query.dsl.user.search.domain.Position;

@Component
public class PositionCommandToPosition implements Converter<PositionCommand, Position> {

	@Override
	public Position convert(PositionCommand source) {
		if(source == null) {
			return null;
		}
		Position position = new Position();
		position.setId(source.getId());
		position.setPosition(source.getPosition());
		return position;
	}

}
