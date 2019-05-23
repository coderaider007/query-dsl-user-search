package com.query.dsl.user.search.predicate;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.query.dsl.user.search.command.EmployeeSearchCommand;
import com.querydsl.core.types.dsl.BooleanExpression;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeePredicateIT {
	
	@Autowired
	private EmployeePredicate employeePredicate;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindEmployeeById() {
		Long employeeId = Long.valueOf(1);
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeById(employeeId);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByIdNull() {
		Long employeeId = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeById(employeeId);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByFirstName() {
		String firstName = "firstName";
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByFirstName(firstName);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByFirstNameNull() {
		String firstName = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByFirstName(firstName);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByLastName() {
		String lastName = "FAFS";
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByLastName(lastName);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByLastNameNull() {
		String lastName = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByLastName(lastName);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByStartDateGreaterThanOrEqual() {
		Date date = Calendar.getInstance().getTime();
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByStartDateGreaterThanOrEqual(date);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByStartDateGreaterThanOrEqualNull() {
		Date date = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByStartDateGreaterThanOrEqual(date);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByStartDateLessThanOrEqual() {
		Date date = Calendar.getInstance().getTime();
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByStartDateLessThanOrEqual(date);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByStartDateLessThanOrEqualNull() {
		Date date = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByStartDateLessThanOrEqual(date);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByEndDateGreaterThanOrEqual() {
		Date date = Calendar.getInstance().getTime();
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByEndDateGreaterThanOrEqual(date);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByEndDateGreaterThanOrEqualNull() {
		Date date = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByEndDateGreaterThanOrEqual(date);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByEndDateLessThanOrEqual() {
		Date date = Calendar.getInstance().getTime();
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByEndDateLessThanOrEqual(date);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByEndDateLessThanOrEqualNull() {
		Date date = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByEndDateLessThanOrEqual(date);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByPosition() {
		String position = "Engineer";
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByPosition(position);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByPositionNull() {
		String position = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByPosition(position);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByPositionId() {
		Long positionId = Long.valueOf(1);
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByPositionId(positionId);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByPositionIdNull() {
		Long positionId = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByPositionId(positionId);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByStatus() {
		String status = "INACTIVE";
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByStatus(status);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByStatusNull() {
		String status = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByStatus(status);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByStatusId() {
		Long statusId = Long.valueOf(1);
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByStatusId(statusId);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByStatusIdNull() {
		Long statusId = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByStatusId(statusId);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testFindEmployeeByAddress() {
		String address = "123 Main Rd.";
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByAddress(address);
		assertNotNull(booleanExpression);
	}
	
	@Test
	public void testFindEmployeeByAddressNull() {
		String address = null;
		BooleanExpression booleanExpression = this.employeePredicate.findEmployeeByAddress(address);
		assertNotNull(booleanExpression);
	}

	@Test
	public void testGetPredicate() throws Exception {
		EmployeeSearchCommand employeeSearchCommand = new EmployeeSearchCommand();
		employeeSearchCommand.setPositionId(Long.valueOf(1));
		employeeSearchCommand.setStatusId(Long.valueOf(2));
		
		BooleanExpression booleanExpression = this.employeePredicate.getPredicate(employeeSearchCommand);
		
		assertNotNull(booleanExpression);
	}
	
	@Test(expected=Exception.class)
	public void testGetPredicateNull() throws Exception {
		
		this.employeePredicate.getPredicate(null);
	}

}
