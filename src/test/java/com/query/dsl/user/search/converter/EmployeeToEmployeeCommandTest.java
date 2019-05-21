package com.query.dsl.user.search.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.command.AddressCommand;
import com.query.dsl.user.search.command.EmployeeCommand;
import com.query.dsl.user.search.domain.Address;
import com.query.dsl.user.search.domain.Country;
import com.query.dsl.user.search.domain.Employee;
import com.query.dsl.user.search.domain.Position;
import com.query.dsl.user.search.domain.State;
import com.query.dsl.user.search.domain.Status;

public class EmployeeToEmployeeCommandTest {

	private AddressToAddressCommand addressToAddressCommand;
	private StatusToStatusCommand statusToStatusCommand;
	private PositionToPositionCommand positionToPositionCommand;
	
	private EmployeeToEmployeeCommand employeeToEmployeeCommand;
	
	@Before
	public void setUp() throws Exception {
		this.addressToAddressCommand = new AddressToAddressCommand(new CountryToCountryCommand(), new StateToStateCommand(new CountryToCountryCommand()));
		this.statusToStatusCommand = new StatusToStatusCommand();
		this.positionToPositionCommand = new PositionToPositionCommand();
		this.employeeToEmployeeCommand = new EmployeeToEmployeeCommand(addressToAddressCommand, statusToStatusCommand, positionToPositionCommand);
		
	}

	@Test
	public void testConvert() {
		Date date = Calendar.getInstance().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		Date endDate = calendar.getTime();
		Employee employee = new Employee();
		employee.setEndDate(endDate);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setId(Long.valueOf(1));
		
		Position position = new Position();
		position.setId(Long.valueOf(2));
		position.setPosition("ECONOMIST");
		employee.setPosition(position);
		
		employee.setStartDate(date);
		
		Status status = new Status();
		status.setId(Long.valueOf(3));
		status.setStatus("ACTIVE");
		employee.setStatus(status);
		
		Address address = new Address();
		address.setAddress1("123 Roxbury Rd");
		address.setAddress2("null");
		address.setCity("Working Town");
		address.setId(Long.valueOf(4));
		address.setIsMainAddress(true);
		address.setPostalCode("90132");
		
		Country country = new Country();
		country.setId(Long.valueOf(1));
		country.setName("United States");
		address.setCountry(country);
		
		State state = new State();
		state.setCountry(country);
		state.setId(Long.valueOf(5));
		state.setName("California");
		address.setState(state);
		
		Set<Address> addresses = new HashSet<>();
		addresses.add(address);
		
		employee.setAddress(address);
		
		EmployeeCommand  employeeCommand = this.employeeToEmployeeCommand.convert(employee);
		
		assertNotNull(employeeCommand);
		assertEquals(Long.valueOf(1), employeeCommand.getId());
		assertNotNull(employeeCommand.getPositionCommand());
		assertEquals("ECONOMIST", employeeCommand.getPositionCommand().getPosition());
		assertNotNull(employeeCommand.getStatusCommand());
		assertEquals("ACTIVE", employeeCommand.getStatusCommand().getStatus());
		assertNotNull(employeeCommand.getAddressCommand());
		//assertEquals(Integer.valueOf(1), Integer.valueOf(employeeCommand.getAddresseCommands().size()));
		AddressCommand addressCommand = employeeCommand.getAddressCommand();
		assertNotNull(addressCommand);
		assertEquals(addressCommand.getId(), Long.valueOf(4));
		assertNotNull(addressCommand.getCountryCommand());
	}

}
