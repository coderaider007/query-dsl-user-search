package com.query.dsl.user.search.controller;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.query.dsl.user.search.command.AddressCommand;
import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.command.EmployeeCommand;
import com.query.dsl.user.search.command.EmployeeSearchCommand;
import com.query.dsl.user.search.command.PositionCommand;
import com.query.dsl.user.search.command.StatusCommand;
import com.query.dsl.user.search.domain.Address;
import com.query.dsl.user.search.domain.Country;
import com.query.dsl.user.search.domain.Employee;
import com.query.dsl.user.search.domain.Position;
import com.query.dsl.user.search.domain.Status;
import com.query.dsl.user.search.service.CountryService;
import com.query.dsl.user.search.service.EmployeeService;
import com.query.dsl.user.search.service.PositionService;
import com.query.dsl.user.search.service.StateService;
import com.query.dsl.user.search.service.StatusService;
import com.query.dsl.user.search.validator.EmployeeSearchCommandValidator;

public class EmployeeControllerTest {
	
	private static final String EMPLOYEE_FORM = "employee/employeeForm";
	private static final String INDEX = "index";
	
	@Mock
	private EmployeeService employeeService;
	
	@Mock
	private StatusService statusService;
	
	@Mock
	private PositionService positionService;
	
	@Mock
	private StateService stateService;
	
	@Mock
	private CountryService countryService;
	
	private EmployeeController employeeController;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.employeeController = new EmployeeController(employeeService, statusService, positionService, stateService, countryService,
				new EmployeeSearchCommandValidator());
		mockMvc = MockMvcBuilders.standaloneSetup(this.employeeController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()) //Needed to resolve issues with the pageable interface as 
				//argument to the methods of the controller
				.build();
	}

	@Test
	public void testFindById() throws Exception {
		EmployeeCommand employeeCommand = this.loadEmployeeCommandForTest();
		
		when(this.employeeService.findById(anyLong())).thenReturn(employeeCommand);
		
		mockMvc.perform(get("/employee/1/show"))
		.andExpect(status().isOk())
		.andExpect(view().name("employee/show"))
		.andExpect(model().attribute("employee", notNullValue()));
	}
	
	@Test
	public void testFindByIdPost() throws Exception {
		EmployeeCommand employeeCommand = this.loadEmployeeCommandForTest();
		
		when(this.employeeService.findById(anyLong())).thenReturn(employeeCommand);
		
		mockMvc.perform(post("/employee/1/show")
				.param("firstName", "John")
				.param("lastName", "Doe")
				.param("startDate", "2019-05-20")
				.param("positionCommand.id", "1")
				.param("positionCommand.position", "Engineer")
				.param("statusCommand.id", "2")
				.param("statusCommand.status", "ACTIVE")
				.param("addressCommand.address1", "21 Jump Street")
				.param("addressCommand.city", "HappyVille")
				.param("addressCommand.postalCode", "90123")
				.param("addressCommand.countryCommand.id", "1")
				.param("addressCommand.countryCommand.name", "United States")
				.param("addressCommand.isMainAddress", "true"))
		.andExpect(status().isOk())
		.andExpect(view().name("employee/show"))
		.andExpect(model().attribute("employee", notNullValue()))
		.andExpect(model().attribute("employeeSearchCommand", notNullValue()));
	}

	@Test
	public void testSearchEmployee() throws Exception {
		List<EmployeeCommand> employeeCommands = new ArrayList<>();
		EmployeeCommand employeeCommand = this.loadEmployeeCommandForTest();
		employeeCommands.add(employeeCommand);
		
		Pageable pageable = PageRequest.of(0, 1);
		Page<EmployeeCommand> pageCommand = new PageImpl<>(employeeCommands, pageable, Long.valueOf(1));
		when(this.employeeService.searchEmployee(any(EmployeeSearchCommand.class), any(Pageable.class))).thenReturn(pageCommand);
		
		mockMvc.perform(post("/employee/search/new")
				.param("id", "1")
				.param("firstName", "John")
				.param("page", "0")
				.param("size", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name(INDEX))
		.andExpect(model().attribute("employeeSearchCommand", notNullValue()));
	}

	@Test
	public void testSearchEmployeeNextPage() throws Exception {
		List<EmployeeCommand> employeeCommands = new ArrayList<>();
		EmployeeCommand employeeCommand = this.loadEmployeeCommandForTest();
		employeeCommands.add(employeeCommand);
		
		Pageable pageable = PageRequest.of(0, 1);
		Page<EmployeeCommand> pageCommand = new PageImpl<>(employeeCommands, pageable, Long.valueOf(1));
		when(this.employeeService.loadPrevNexPage(any(EmployeeSearchCommand.class), any(Pageable.class), any(Boolean.class))).thenReturn(pageCommand);
		
		mockMvc.perform(post("/employee/search/new")
				.param("id", "1")
				.param("firstName", "John")
				.param("page", "0")
				.param("size", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name(INDEX))
		.andExpect(model().attribute("employeeSearchCommand", notNullValue()));
	}

	@Test
	public void testSearchEmployeePrevPage() throws Exception {
		List<EmployeeCommand> employeeCommands = new ArrayList<>();
		EmployeeCommand employeeCommand = this.loadEmployeeCommandForTest();
		employeeCommands.add(employeeCommand);
		
		Pageable pageable = PageRequest.of(1, 1);
		Page<EmployeeCommand> pageCommand = new PageImpl<>(employeeCommands, pageable, Long.valueOf(1));
		when(this.employeeService.loadPrevNexPage(any(EmployeeSearchCommand.class), any(Pageable.class), any(Boolean.class))).thenReturn(pageCommand);
		
		mockMvc.perform(post("/employee/search/new")
				.param("id", "1")
				.param("firstName", "John")
				.param("page", "1")
				.param("size", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name(INDEX))
		.andExpect(model().attribute("employeeSearchCommand", notNullValue()));
	}

	@Test
	public void testSaveEmployeeNew() throws Exception {
		mockMvc.perform(get("/employee/new"))
		.andExpect(status().isOk())
		.andExpect(view().name(EMPLOYEE_FORM))
		.andExpect(model().attribute("employeeForm", notNullValue()));
	}

	@Test
	public void testUpdateEmployee() throws Exception {

		EmployeeCommand employeeCommand = this.loadEmployeeCommandForTest();
		when(this.employeeService.findById(anyLong())).thenReturn(employeeCommand);
		
		mockMvc.perform(get("/employee/1/update"))
		.andExpect(status().isOk())
		.andExpect(view().name(EMPLOYEE_FORM))
		.andExpect(model().attribute("employeeForm", notNullValue()));
	}

	@Test
	public void testSaveOrUpdateEmployee() throws Exception {
		EmployeeCommand employeeCommand = this.loadEmployeeCommandForTest();
		when(this.employeeService.saveOrUpdateEmployee(any(EmployeeCommand.class))).thenReturn(employeeCommand);
		
		mockMvc.perform(post("/employee/save")
				.param("firstName", "John")
				.param("lastName", "Doe")
				.param("startDate", "2019-05-20")
				.param("positionCommand.id", "1")
				.param("positionCommand.position", "Engineer")
				.param("statusCommand.id", "2")
				.param("statusCommand.status", "ACTIVE")
				.param("addressCommand.address1", "21 Jump Street")
				.param("addressCommand.city", "HappyVille")
				.param("addressCommand.postalCode", "90123")
				.param("addressCommand.countryCommand.id", "1")
				.param("addressCommand.countryCommand.name", "United States")
				.param("addressCommand.isMainAddress", "true")
				.param("addressCommand.stateCommand.id", "1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/employee/1/show"));
	}
	
	@Test
	public void testSaveOrUpdateEmployeeWithMissingParams() throws Exception {
		EmployeeCommand employeeCommand = this.loadEmployeeCommandForTest();
		when(this.employeeService.saveOrUpdateEmployee(any(EmployeeCommand.class))).thenReturn(employeeCommand);
		
		mockMvc.perform(post("/employee/save")
				.param("firstName", "John")
				.param("lastName", "Doe")
				.param("startDate", "2019-05-20")
				//.param("positionCommand.id", "1")
				//.param("positionCommand.position", "Engineer")
				.param("statusCommand.id", "2")
				.param("statusCommand.status", "ACTIVE")
				.param("addressCommand.address1", "21 Jump Street")
				.param("addressCommand.city", "HappyVille")
				.param("addressCommand.postalCode", "90123")
				.param("addressCommand.countryCommand.id", "1")
				.param("addressCommand.countryCommand.name", "United States")
				.param("addressCommand.isMainAddress", "true"))
		.andExpect(status().isOk())
		.andExpect(view().name(EMPLOYEE_FORM));
	}

	@Test
	public void testLoadAllPositions() {
		ArrayList<PositionCommand> positions = new ArrayList<>();
		
		PositionCommand position = new PositionCommand();
		position.setId(Long.valueOf(1));
		position.setPosition("Janitor");
		positions.add(position);
		
		PositionCommand position1 = new PositionCommand();
		position1.setId(Long.valueOf(2));
		position1.setPosition("Software Engineer");
		positions.add(position1);
		
		PositionCommand position2 = new PositionCommand();
		position2.setId(Long.valueOf(3));
		position2.setPosition("Administrator");
		positions.add(position2);
		
		when(this.positionService.loadAllPositions()).thenReturn(positions);
		
		List<PositionCommand> positionCommands = this.employeeController.loadAllPositions();
		assertNotNull(positionCommands);
		assertEquals(Long.valueOf(positionCommands.size()), Long.valueOf(3));
	}

	@Test
	public void testLoadAllStatuses() {
		ArrayList<StatusCommand> statuses = new ArrayList<>();
		
		StatusCommand status = new StatusCommand();
		status.setId(Long.valueOf(1));
		status.setStatus("INACTIVE");
		statuses.add(status);
		
		StatusCommand status1 = new StatusCommand();
		status1.setId(Long.valueOf(2));
		status1.setStatus("ACTIVE");
		statuses.add(status1);
		
		StatusCommand status2 = new StatusCommand();
		status2.setId(Long.valueOf(3));
		status2.setStatus("LAYOFF");
		statuses.add(status2);
		
		when(this.statusService.loadAllStatuses()).thenReturn(statuses);
		
		List<StatusCommand> statusCommands = this.employeeController.loadAllStatuses();
		
		assertNotNull(statusCommands);
		assertEquals(Long.valueOf(statusCommands.size()), Long.valueOf(3));
		
	}
	
	private Employee loadEmployeeForTest() {
		Country country = new Country();
		country.setId(Long.valueOf(1));
		country.setName("China");
		
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
		countryCommand.setName("China");
		
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
