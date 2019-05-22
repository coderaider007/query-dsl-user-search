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
		
		if(!EmployeeSearchCommandValid.isEmployeeSearchCommandValid(esc)) {
			errors.rejectValue("error", "error.employeeSearchCommand.form", "You must fill at least one field, for the employee search to be conducted");
		}
	}
}
