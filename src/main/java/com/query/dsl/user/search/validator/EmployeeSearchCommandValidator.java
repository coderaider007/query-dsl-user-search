package com.query.dsl.user.search.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.query.dsl.user.search.command.EmployeeSearchCommand;

@Component
public class EmployeeSearchCommandValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeSearchCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EmployeeSearchCommand esc = (EmployeeSearchCommand)target;
		
		if(StringUtils.isEmpty(esc.getAddress()) && esc.getId() == null && esc.getEndDateGreaterThan() == null && esc.getEndDateLessThan() == null 
				&& StringUtils.isEmpty(esc.getFirstName()) && StringUtils.isEmpty(esc.getLastName()) && esc.getPositionId() == null 
				&& esc.getStartDateGreaterThan() == null && esc.getStartDateLessThan() == null && esc.getStatusId() == null) {
			errors.rejectValue("error", "error.employeeSearchCommand.form", "You must fill at least one field, for the employee search to be conducted");			
		}
	}
	
}
