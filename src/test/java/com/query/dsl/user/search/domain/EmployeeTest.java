package com.query.dsl.user.search.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.query.dsl.user.search.domain.audit.Audit;

import javassist.expr.NewArray;

public class EmployeeTest {

	private Employee employee;
	
	@Before
	public void setUp() throws Exception {
		this.employee = Employee.builder().id(Long.valueOf(1)).address(new Address())
				.audit(new Audit())
				.endDate(Calendar.getInstance().getTime())
				.firstName("FASFSF")
				.lastName("FSAFSF")
				.position(new Position())
				.startDate(Calendar.getInstance().getTime())
				.status(new Status())
				.version(Long.valueOf(23))
				.build();
		
	}

	@Test
	public void testHashCode() {
		Integer i = this.employee.hashCode();
		assertNotNull(i);
	}

	@Test
	public void testEqualsObject() {
		Boolean boolean1 = this.employee.equals(employee);
		assertEquals(boolean1, Boolean.TRUE);
	}

	
	@Test
	public void testBuilder() {
		Employee employee = Employee.builder().id(Long.valueOf(3)).build();
		Long id = employee.getId();
		assertEquals(Long.valueOf(3), id);
	}

	@Test
	public void testGetFirstName() {
		String firstName = this.employee.getFirstName();
		assertNotNull(firstName);
	}

	@Test
	public void testGetLastName() {
		String lastName = this.employee.getLastName();
		assertNotNull(lastName);
	}

	@Test
	public void testGetStartDate() {
		Date date = this.employee.getStartDate();
		assertNotNull(date);
	}

	@Test
	public void testGetEndDate() {
		Date date = this.employee.getEndDate();
		assertNotNull(date);
	}

	@Test
	public void testGetAddress() {
		Address address = this.employee.getAddress();
		assertNotNull(address);
	}

	@Test
	public void testGetPosition() {
		Position position = this.employee.getPosition();
		assertNotNull(position);
	}

	@Test
	public void testGetStatus() {
		Status status = this.employee.getStatus();
		assertNotNull(status);
		
	}

	@Test
	public void testSetFirstName() {
		this.employee.setFirstName("Alexander");
		String firstName = this.employee.getFirstName();
		assertNotNull(firstName);
		assertEquals("Alexander", firstName);
	}

	@Test
	public void testSetLastName() {
		this.employee.setLastName("Alexander");
		String lastName = this.employee.getLastName();
		assertNotNull(lastName);
		assertEquals("Alexander", lastName);
	}

	@Test
	public void testSetStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		this.employee.setStartDate(calendar.getTime());
		Date date = this.employee.getStartDate();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(date.getTime());
		assertEquals(Integer.valueOf(2017), Integer.valueOf(calendar2.get(Calendar.YEAR)));
	}

	@Test
	public void testSetEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2017);
		this.employee.setEndDate(calendar.getTime());
		Date date = this.employee.getEndDate();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(date.getTime());
		assertEquals(Integer.valueOf(2017), Integer.valueOf(calendar2.get(Calendar.YEAR)));
	}

	@Test
	public void testSetAddress() {
		Address address = Address.builder().id(Long.valueOf(5))
				.address1("123 LaLaLand")
				.city("Los Angeles")
				.country(Country.builder().id(Long.valueOf(4)).name("United States").build())
				.isMainAddress(Boolean.TRUE)
				.postalCode("90063")
				.version(Long.valueOf(0))
				.build();
		this.employee.setAddress(address);
		
		assertNotNull(this.employee.getAddress());
		assertEquals("Los Angeles", this.employee.getAddress().getCity());
	}

	@Test
	public void testSetPosition() {
		Position position = Position.builder().id(Long.valueOf(1))
				.position("Software Engineer")
				.version(Long.valueOf(0))
				.build();
		Position position2 = this.employee.getPosition();
		assertNull(position2.getId());
		
		this.employee.setPosition(position);
		Position position3 = this.employee.getPosition();
		assertNotNull(position3);
		assertEquals("Software Engineer" , position3.getPosition());
	}

	@Test
	public void testSetStatus() {
		Status status = Status.builder().id(Long.valueOf(2))
				
				.build();
	}

	@Test
	public void testEmployee() {
		
	}

	
	@Test
	public void testGetId() {
		
	}

	@Test
	public void testGetVersion() {
		
	}

	@Test
	public void testGetAudit() {
		
	}

	@Test
	public void testSetId() {
		
	}

	@Test
	public void testSetVersion() {
		
	}

	@Test
	public void testSetAudit() {
		
	}
}
