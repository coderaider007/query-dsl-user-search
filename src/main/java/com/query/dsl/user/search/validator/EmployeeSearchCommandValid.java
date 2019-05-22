package com.query.dsl.user.search.validator;

import org.springframework.util.StringUtils;

import com.query.dsl.user.search.command.EmployeeSearchCommand;

public class EmployeeSearchCommandValid {

	public static Boolean isEmployeeSearchCommandValid(EmployeeSearchCommand esc) {
		
		if(isBlank(esc.getAddress()) 
				&& esc.getId() == null 
				&& esc.getEndDateGreaterThan() == null 
				&& esc.getEndDateLessThan() == null 
				&& isBlank(esc.getFirstName()) 
				&& isBlank(esc.getLastName()) 
				&& esc.getPositionId() == null 
				&& esc.getStartDateGreaterThan() == null 
				&& esc.getStartDateLessThan() == null 
				&& esc.getStatusId() == null) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	private static Boolean isBlank(String s) {
		return (s == null) || StringUtils.isEmpty(s.trim());
	}
}
