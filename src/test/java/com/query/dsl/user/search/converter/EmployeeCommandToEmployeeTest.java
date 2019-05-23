package com.query.dsl.user.search.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.command.AddressCommand;
import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.command.EmployeeCommand;
import com.query.dsl.user.search.command.PositionCommand;
import com.query.dsl.user.search.command.StateCommand;
import com.query.dsl.user.search.command.StatusCommand;
import com.query.dsl.user.search.domain.Address;
import com.query.dsl.user.search.domain.Employee;

public class EmployeeCommandToEmployeeTest {
	
	private AddressCommandToAddress addressCommandToAddress;
	private StatusCommandToStatus statusCommandToStatus;
	private PositionCommandToPosition positionCommandToPosition;
	private CountryCommandToCountry countryCommandToCountry;
	
	private EmployeeCommandToEmployee employeeCommandToEmployee;

	@Before
	public void setUp() throws Exception {
		this.countryCommandToCountry = new CountryCommandToCountry();
		this.addressCommandToAddress = new AddressCommandToAddress(countryCommandToCountry, new StateCommandToState(new CountryCommandToCountry()));
		this.statusCommandToStatus = new StatusCommandToStatus();
		this.positionCommandToPosition = new PositionCommandToPosition();
		
		this.employeeCommandToEmployee = new EmployeeCommandToEmployee(addressCommandToAddress, statusCommandToStatus, positionCommandToPosition);
		
		
	}

	@Test
	public void testConvert() {
		Date date = Calendar.getInstance().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		Date endDate = calendar.getTime();
		EmployeeCommand employeeCommand = new EmployeeCommand();
		employeeCommand.setEndDate(endDate);
		employeeCommand.setFirstName("John");
		employeeCommand.setLastName("Doe");
		employeeCommand.setId(Long.valueOf(1));
		
		PositionCommand positionCommand = new PositionCommand();
		positionCommand.setId(Long.valueOf(2));
		positionCommand.setPosition("ECONOMIST");
		employeeCommand.setPositionCommand(positionCommand);
		
		employeeCommand.setStartDate(date);
		
		StatusCommand statusCommand = new StatusCommand();
		statusCommand.setId(Long.valueOf(3));
		statusCommand.setStatus("ACTIVE");
		employeeCommand.setStatusCommand(statusCommand);
		
		AddressCommand addressCommand = new AddressCommand();
		addressCommand.setAddress1("123 Roxbury Rd");
		addressCommand.setAddress2("null");
		addressCommand.setCity("Working Town");
		addressCommand.setId(Long.valueOf(4));
		addressCommand.setIsMainAddress(true);
		addressCommand.setPostalCode("90132");
		
		CountryCommand countryCommand = new CountryCommand();
		countryCommand.setId(Long.valueOf(1));
		countryCommand.setName("United States");
		addressCommand.setCountryCommand(countryCommand);
		
		StateCommand stateCommand = new StateCommand();
		stateCommand.setCountryCommand(countryCommand);
		stateCommand.setId(Long.valueOf(5));
		stateCommand.setName("California");
		addressCommand.setStateCommand(stateCommand);
		
		employeeCommand.setAddressCommand(addressCommand);
		
		Employee  employee = this.employeeCommandToEmployee.convert(employeeCommand);
		
		assertNotNull(employee);
		assertEquals(Long.valueOf(1), employee.getId());
		assertNotNull(employee.getPosition());
		assertEquals("ECONOMIST", employee.getPosition().getPosition());
		assertNotNull(employee.getStatus());
		assertEquals("ACTIVE", employee.getStatus().getStatus());
		assertNotNull(employee.getAddress());
		//assertEquals(Integer.valueOf(1), Integer.valueOf(employee.getAddresses().size()));
		Address address = employee.getAddress();
		assertNotNull(address);
		assertEquals(address.getId(), Long.valueOf(4));
		assertNotNull(address.getCountry());
		
	}
	
	@Test
	public void testConvertNull() {
		Employee  employee = this.employeeCommandToEmployee.convert(null);
		assertNull(employee);
	}

}
