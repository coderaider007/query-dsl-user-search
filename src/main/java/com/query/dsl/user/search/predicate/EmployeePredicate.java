package com.query.dsl.user.search.predicate;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.query.dsl.user.search.command.EmployeeSearchCommand;
import com.query.dsl.user.search.domain.QEmployee;
import com.querydsl.core.types.dsl.BooleanExpression;


@Component
public class EmployeePredicate {

	private QEmployee qEmployee = QEmployee.employee;
	private BooleanExpression anyBoolean = qEmployee.isNotNull();
	
	public BooleanExpression findEmployeeById(Long employeeId) {
		if(employeeId == null) {
			return anyBoolean;
		}
		return qEmployee.id.eq(employeeId);
	}
	
	public BooleanExpression findEmployeeByFirstName(String firstName) {
		if(firstName == null || firstName.isBlank()) {
			return anyBoolean;
		}
		return qEmployee.firstName.toLowerCase().like("%"+firstName.toLowerCase()+"%");
	}
	
	public BooleanExpression findEmployeeByLastName(String lastName) {
		if(lastName == null || lastName.isBlank()) {
			return anyBoolean;
		}
		return qEmployee.lastName.toLowerCase().like("%"+lastName.toLowerCase()+"%");
	}
	
	public BooleanExpression findEmployeeByStartDateGreaterThanOrEqual(Date startDate) {
		if(startDate == null) {
			return anyBoolean;
		}
		return qEmployee.startDate.goe(startDate);
	}
	
	public BooleanExpression findEmployeeByStartDateLessThanOrEqual(Date startDate) {
		if(startDate == null) {
			return anyBoolean;
		}
		return qEmployee.startDate.loe(startDate);
	}
	
	public BooleanExpression findEmployeeByEndDateGreaterThanOrEqual(Date endDate) {
		if(endDate == null) {
			return anyBoolean;
		}
		return qEmployee.endDate.goe(endDate);
	}
	
	public BooleanExpression findEmployeeByEndDateLessThanOrEqual(Date endDate) {
		if(endDate == null) {
			return anyBoolean;
		}
		return qEmployee.endDate.loe(endDate);
	}
	
	public BooleanExpression findEmployeeByPosition(String position) {
		if(position == null || position.isBlank()) {
			return anyBoolean;
		}
		return qEmployee.position.position.toLowerCase().like("%"+position.toLowerCase()+"%");
	}
	
	public BooleanExpression findEmployerByPositionId(Long positionId) {
		if(positionId == null || positionId <= 0) {
			return anyBoolean;
		}
		return qEmployee.position.id.eq(positionId);
	}
	
	
	public BooleanExpression findEmployeeByStatus(String status) {
		if(status == null || status.isBlank()) {
			return anyBoolean;
		}
		return qEmployee.status.status.toLowerCase().like("%"+status.toLowerCase()+"%");
	}
	
	public BooleanExpression findEmployeeByStatusId(Long statusId) {
		if(statusId == null || statusId <= 0) {
			return anyBoolean;
		}
		return qEmployee.status.id.eq(statusId);
	}
	
	public BooleanExpression findEmployeeByAddress(String address) {
		if(address == null || address.isBlank()) {
			return anyBoolean;
		}
		return qEmployee.address.address1.toLowerCase().like("%"+address.toLowerCase()+"%");
	}
	
	/**
	 * 
	 * @param employeeSearchCommand
	 * @return
	 */
	public BooleanExpression getPredicate(EmployeeSearchCommand employeeSearchCommand) {
		BooleanExpression be = this.findEmployeeByAddress(employeeSearchCommand.getAddress())
				.and(this.findEmployeeByEndDateGreaterThanOrEqual(employeeSearchCommand.getEndDateGreaterThan()))
				.and(this.findEmployeeByEndDateLessThanOrEqual(employeeSearchCommand.getEndDateLessThan()))
				.and(this.findEmployeeByFirstName(employeeSearchCommand.getFirstName()))
				.and(this.findEmployeeById(employeeSearchCommand.getId()))
				.and(this.findEmployeeByLastName(employeeSearchCommand.getLastName()))
				.and(this.findEmployerByPositionId(employeeSearchCommand.getPositionId()))
				.and(this.findEmployeeByStartDateGreaterThanOrEqual(employeeSearchCommand.getStartDateGreaterThan()))
				.and(this.findEmployeeByStartDateLessThanOrEqual(employeeSearchCommand.getStartDateLessThan()))
				.and(this.findEmployeeByStatusId(employeeSearchCommand.getStatusId()));
		
		return be;
	}
	
}
