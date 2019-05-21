package com.query.dsl.user.search.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.AddressCommand;
import com.query.dsl.user.search.command.EmployeeCommand;
import com.query.dsl.user.search.domain.Employee;

@Component
public class EmployeeToEmployeeCommand implements Converter<Employee, EmployeeCommand> {
	
	private final AddressToAddressCommand addressToAddressCommand;
	private final StatusToStatusCommand statusToStatusCommand;
	private final PositionToPositionCommand positionToPositionCommand;
	
	public EmployeeToEmployeeCommand(AddressToAddressCommand addressToAddressCommand,
			StatusToStatusCommand statusToStatusCommand, PositionToPositionCommand positionToPositionCommand) {
		super();
		this.addressToAddressCommand = addressToAddressCommand;
		this.statusToStatusCommand = statusToStatusCommand;
		this.positionToPositionCommand = positionToPositionCommand;
	}

	@Override
	public EmployeeCommand convert(Employee source) {
		if(source == null) {
			return null;
		}
		EmployeeCommand employeeCommand = new EmployeeCommand();
		employeeCommand.setAddressCommand(this.addressToAddressCommand.convert(source.getAddress()));
		employeeCommand.setEndDate(source.getEndDate());
		employeeCommand.setFirstName(source.getFirstName());
		employeeCommand.setId(source.getId());
		employeeCommand.setLastName(source.getLastName());
		employeeCommand.setPositionCommand(this.positionToPositionCommand.convert(source.getPosition()));
		employeeCommand.setStartDate(source.getStartDate());
		employeeCommand.setStatusCommand(this.statusToStatusCommand.convert(source.getStatus()));
		
		return employeeCommand;
	}

}
