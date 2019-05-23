package com.query.dsl.user.search.service.Impl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.query.dsl.user.search.command.AddressCommand;
import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.command.EmployeeCommand;
import com.query.dsl.user.search.command.EmployeeSearchCommand;
import com.query.dsl.user.search.command.PositionCommand;
import com.query.dsl.user.search.command.StateCommand;
import com.query.dsl.user.search.command.StatusCommand;
import com.query.dsl.user.search.converter.AddressCommandToAddress;
import com.query.dsl.user.search.converter.AddressToAddressCommand;
import com.query.dsl.user.search.converter.CountryCommandToCountry;
import com.query.dsl.user.search.converter.CountryToCountryCommand;
import com.query.dsl.user.search.converter.EmployeeCommandToEmployee;
import com.query.dsl.user.search.converter.EmployeeToEmployeeCommand;
import com.query.dsl.user.search.converter.PositionCommandToPosition;
import com.query.dsl.user.search.converter.PositionToPositionCommand;
import com.query.dsl.user.search.converter.StateCommandToState;
import com.query.dsl.user.search.converter.StateToStateCommand;
import com.query.dsl.user.search.converter.StatusCommandToStatus;
import com.query.dsl.user.search.converter.StatusToStatusCommand;
import com.query.dsl.user.search.domain.Address;
import com.query.dsl.user.search.domain.Country;
import com.query.dsl.user.search.domain.Employee;
import com.query.dsl.user.search.domain.Position;
import com.query.dsl.user.search.domain.State;
import com.query.dsl.user.search.domain.Status;
import com.query.dsl.user.search.predicate.EmployeePredicate;
import com.query.dsl.user.search.repository.CountryRepository;
import com.query.dsl.user.search.repository.EmployeeRepository;
import com.query.dsl.user.search.repository.PositionRepository;
import com.query.dsl.user.search.repository.StateRepository;
import com.query.dsl.user.search.repository.StatusRepository;
import com.query.dsl.user.search.service.EmployeeService;
import com.querydsl.core.types.dsl.BooleanExpression;


public class EmployeeServiceImplTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	private EmployeeToEmployeeCommand employeeToEmployeeCommand;
	
	@Mock
	private EmployeePredicate employeePredicate;
	
	@Mock
	private PositionRepository positionRepository;
	
	@Mock
	private StatusRepository statusRepository;
	
	@Mock
	private Pageable pageable;
	
	@Mock
	private CountryRepository countryRepository;
	
	@Mock
	private StateRepository stateRepository;
	
	private EmployeeCommandToEmployee employeeCommandToEmployee;
	
	private EmployeeService employeeService;
	
	@Mock
	private BooleanExpression booleanExpression;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.employeeToEmployeeCommand = new EmployeeToEmployeeCommand(new AddressToAddressCommand(new CountryToCountryCommand(), new StateToStateCommand(new CountryToCountryCommand())),
				new StatusToStatusCommand(), new PositionToPositionCommand());
		this.employeeCommandToEmployee = new EmployeeCommandToEmployee(new AddressCommandToAddress(new CountryCommandToCountry(), new StateCommandToState(new CountryCommandToCountry())),
				new StatusCommandToStatus(), new PositionCommandToPosition());
		
		this.employeeService = new EmployeeServiceImpl(employeeRepository, employeeToEmployeeCommand, employeePredicate, positionRepository, 
				statusRepository, employeeCommandToEmployee, countryRepository, stateRepository);
	}

	@Test
	public void testFindById() throws Exception {
		Employee employee = this.loadEmployeeForTest();
		
		when(this.employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
		
		EmployeeCommand employeeCommand = this.employeeService.findById(employee.getId());
		
		assertNotNull(employeeCommand);
		assertEquals(Long.valueOf(1), employeeCommand.getId());
		assertNotNull(employeeCommand.getPositionCommand());
		assertNotNull(employeeCommand.getStatusCommand());
		assertNotNull(employeeCommand.getAddressCommand());
		assertEquals(Long.valueOf(2), employeeCommand.getAddressCommand().getId());
	}

	@Test
	public void testSearchEmployee() throws Exception {
		
		EmployeeSearchCommand employeeSearchCommand = new EmployeeSearchCommand();
		employeeSearchCommand.setAddress("123 Hunt Rd");
		
		Pageable pageable = PageRequest.of(0, 1);
		
		Employee employee = this.loadEmployeeForTest();
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		
		Page<Employee> page = new PageImpl<>(employees, pageable, Long.valueOf(1));
		
		when(this.employeePredicate.getPredicate(any(EmployeeSearchCommand.class))).thenReturn(this.booleanExpression);
		when(this.employeeRepository.findAll(any(BooleanExpression.class), any(Pageable.class))).thenReturn(page);
		
		Page<EmployeeCommand> pageCommand = this.employeeService.searchEmployee(employeeSearchCommand, pageable);
		
		assertNotNull(pageCommand);
		assertNotNull(pageCommand.getContent());
		assertEquals(Long.valueOf(pageCommand.getContent().size()), Long.valueOf(1));
		assertEquals(Integer.valueOf(pageCommand.getNumber()), Integer.valueOf(0));
		
	}

	@Test
	public void testLoadNexPage() throws Exception {
		EmployeeSearchCommand employeeSearchCommand = new EmployeeSearchCommand();
		employeeSearchCommand.setAddress("123 Hunt Rd");
		
		Pageable pageable = PageRequest.of(0, 1);
		
		Employee employee = this.loadEmployeeForTest();
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		
		Page<Employee> page = new PageImpl<>(employees, pageable, Long.valueOf(1));
		
		when(this.employeePredicate.getPredicate(any(EmployeeSearchCommand.class))).thenReturn(this.booleanExpression);
		when(this.employeeRepository.findAll(any(BooleanExpression.class), any(Pageable.class))).thenReturn(page);
		
		Page<EmployeeCommand> pageCommand = this.employeeService.loadPrevNexPage(employeeSearchCommand, pageable, true);
		
		assertNotNull(pageCommand);
		assertNotNull(pageCommand.getContent());
		assertEquals(Long.valueOf(pageCommand.getContent().size()), Long.valueOf(1));
		assertEquals(Integer.valueOf(pageCommand.getNumber()), Integer.valueOf(1));
	}
	
	@Test
	public void testLoadPrevPage() throws Exception {
		EmployeeSearchCommand employeeSearchCommand = new EmployeeSearchCommand();
		employeeSearchCommand.setAddress("123 Hunt Rd");
		
		Pageable pageable = PageRequest.of(1, 1);
		
		Employee employee = this.loadEmployeeForTest();
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		
		Page<Employee> page = new PageImpl<>(employees, pageable, Long.valueOf(1));
		
		when(this.employeePredicate.getPredicate(any(EmployeeSearchCommand.class))).thenReturn(this.booleanExpression);
		when(this.employeeRepository.findAll(any(BooleanExpression.class), any(Pageable.class))).thenReturn(page);
		
		Page<EmployeeCommand> pageCommand = this.employeeService.loadPrevNexPage(employeeSearchCommand, pageable, false);
		
		assertNotNull(pageCommand);
		assertNotNull(pageCommand.getContent());
		assertEquals(Long.valueOf(pageCommand.getContent().size()), Long.valueOf(1));
		assertEquals(Integer.valueOf(pageCommand.getNumber()), Integer.valueOf(0));
	}

	@Test
	public void testSaveEmployee() throws Exception {
		EmployeeCommand employeeCommand = this.loadEmployeeCommandForTest();
		Employee employee = this.loadEmployeeForTest();
		employee.setId(null);
		Employee employee2 = this.loadEmployeeForTest();
		
		when(this.employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
		when(this.employeeRepository.save(any(Employee.class))).thenReturn(employee2);
		when(this.positionRepository.findById(anyLong())).thenReturn(Optional.of(employee.getPosition()));
		when(this.statusRepository.findById(anyLong())).thenReturn(Optional.of(employee.getStatus()));
		when(this.countryRepository.findById(anyLong())).thenReturn(Optional.of(employee.getAddress().getCountry()));
		when(this.stateRepository.findById(anyLong())).thenReturn(Optional.of(employee.getAddress().getState()));
		
		EmployeeCommand employeeCommand2 = this.employeeService.saveOrUpdateEmployee(employeeCommand);
		
		assertNotNull(employeeCommand2);
		assertEquals(employeeCommand2.getId(), Long.valueOf(1));
		assertNotNull(employeeCommand2.getAddressCommand());
		assertEquals(Long.valueOf(employeeCommand2.getAddressCommand().getId()), Long.valueOf(2));
	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		
		EmployeeCommand employeeCommand = this.loadEmployeeCommandForTest();
		Employee employee = this.loadEmployeeForTest();
		
		when(this.employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
		when(this.employeeRepository.save(any(Employee.class))).thenReturn(employee);
		when(this.positionRepository.findById(anyLong())).thenReturn(Optional.of(employee.getPosition()));
		when(this.statusRepository.findById(anyLong())).thenReturn(Optional.of(employee.getStatus()));
		when(this.countryRepository.findById(anyLong())).thenReturn(Optional.of(employee.getAddress().getCountry()));
		when(this.stateRepository.findById(anyLong())).thenReturn(Optional.of(employee.getAddress().getState()));
		
		EmployeeCommand employeeCommand2 = this.employeeService.saveOrUpdateEmployee(employeeCommand);
		
		assertNotNull(employeeCommand2);
		assertEquals(employeeCommand2.getId(), Long.valueOf(1));
		assertNotNull(employeeCommand2.getAddressCommand());
		assertEquals(Long.valueOf(employeeCommand2.getAddressCommand().getId()), Long.valueOf(2));
	}
	
	private Employee loadEmployeeForTest() {
		Country country = new Country();
		country.setId(Long.valueOf(1));
		country.setName("China");
		
		State state = new State();
		state.setCountry(country);
		state.setId(Long.valueOf(5));
		state.setName("California");
		
		Position position = new Position();
		position.setId(Long.valueOf(3));
		position.setPosition("Janitor");
		
		Status status = new Status();
		status.setId(Long.valueOf(5));
		status.setStatus("INACTIVE");
		
		Address address = new Address();
		address.setAddress1("123 Avenue Foch");
		address.setAddress2(null);
		address.setCity("Happy Town");
		address.setId(Long.valueOf(2));
		address.setIsMainAddress(true);
		address.setPostalCode("90112");
		address.setVersion(Long.valueOf(1));
		address.setCountry(country);
		address.setState(state);
		
		Date date = Calendar.getInstance().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		Date endDate = calendar.getTime();
		Employee employee = new Employee();
		employee.setStartDate(date);
		employee.setEndDate(endDate);
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setId(Long.valueOf(1));
		
		employee.setAddress(address);
		employee.setPosition(position);
		employee.setStatus(status);
		
		return employee;
	}
	
	private EmployeeCommand loadEmployeeCommandForTest() {
		CountryCommand countryCommand = new CountryCommand();
		countryCommand.setId(Long.valueOf(1));
		countryCommand.setName("United States");
		
		StateCommand stateCommand = new StateCommand();
		stateCommand.setCountryCommand(countryCommand);
		stateCommand.setId(Long.valueOf(5));
		stateCommand.setName("California");
		
		PositionCommand positionCommand = new PositionCommand();
		positionCommand.setId(Long.valueOf(3));
		positionCommand.setPosition("Janitor");
		
		StatusCommand statusCommand = new StatusCommand();
		statusCommand.setId(Long.valueOf(5));
		statusCommand.setStatus("INACTIVE");
		
		AddressCommand addressCommand = new AddressCommand();
		addressCommand.setAddress1("123 Avenue Foch");
		addressCommand.setAddress2(null);
		addressCommand.setCity("Happy Town");
		addressCommand.setId(Long.valueOf(2));
		addressCommand.setIsMainAddress(true);
		addressCommand.setPostalCode("90112");
		addressCommand.setVersion(Long.valueOf(1));
		addressCommand.setCountryCommand(countryCommand);
		addressCommand.setStateCommand(stateCommand);
		
		Date date = Calendar.getInstance().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		Date endDate = calendar.getTime();
		EmployeeCommand employeeCommand = new EmployeeCommand();
		employeeCommand.setStartDate(date);
		employeeCommand.setEndDate(endDate);
		employeeCommand.setFirstName("John");
		employeeCommand.setLastName("Doe");
		employeeCommand.setId(Long.valueOf(1));
		
		employeeCommand.setAddressCommand(addressCommand);
		employeeCommand.setPositionCommand(positionCommand);
		employeeCommand.setStatusCommand(statusCommand);
		
		return employeeCommand;
	}

}
