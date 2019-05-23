package com.query.dsl.user.search.validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.command.EmployeeSearchCommand;

public class EmployeeSearchCommandValidTest {

	private EmployeeSearchCommandValid employeeSearchCommandValid;
	
	@Before
	public void setUp() throws Exception {
		this.employeeSearchCommandValid = new EmployeeSearchCommandValid();
	}

	@Test
	public void testIsEmployeeSearchCommandValid() {
		EmployeeSearchCommand employeeSearchCommand = new EmployeeSearchCommand();
		employeeSearchCommand.setAddress("123 Main Rd.");
		
		Boolean boolean1 = this.employeeSearchCommandValid.isEmployeeSearchCommandValid(employeeSearchCommand);
		assertNotNull(boolean1);
		assertEquals(boolean1, Boolean.TRUE);
	}

}
