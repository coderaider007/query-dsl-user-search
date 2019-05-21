package com.query.dsl.user.search.converter;

import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.util.http.fileupload.ThresholdingOutputStream;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.EmployeeCommand;
import com.query.dsl.user.search.domain.Address;
import com.query.dsl.user.search.domain.Employee;

@Component
public class EmployeeCommandToEmployee implements Converter<EmployeeCommand, Employee> {

	private final AddressCommandToAddress addressCommandToAddress;
	private final StatusCommandToStatus statusCommandToStatus;
	private final PositionCommandToPosition positionCommandToPosition;
	
	public EmployeeCommandToEmployee(AddressCommandToAddress addressCommandToAddress,
			StatusCommandToStatus statusCommandToStatus, PositionCommandToPosition positionCommandToPosition) {
		super();
		this.addressCommandToAddress = addressCommandToAddress;
		this.statusCommandToStatus = statusCommandToStatus;
		this.positionCommandToPosition = positionCommandToPosition;
	}

	@Override
	public Employee convert(EmployeeCommand source) {
		if(source == null) {
			return null;
		}
		Employee employee = new Employee();
		employee.setAddress(this.addressCommandToAddress.convert(source.getAddressCommand()));
		employee.setEndDate(source.getEndDate());
		employee.setFirstName(source.getFirstName());
		employee.setId(source.getId());
		employee.setLastName(source.getLastName());
		employee.setPosition(this.positionCommandToPosition.convert(source.getPositionCommand()));
		employee.setStartDate(source.getStartDate());
		employee.setStatus(this.statusCommandToStatus.convert(source.getStatusCommand()));
		
		return employee;
	}

}
