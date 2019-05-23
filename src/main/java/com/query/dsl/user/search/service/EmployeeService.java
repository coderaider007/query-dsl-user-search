package com.query.dsl.user.search.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.query.dsl.user.search.command.EmployeeCommand;
import com.query.dsl.user.search.command.EmployeeSearchCommand;

public interface EmployeeService {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public EmployeeCommand findById(Long id) throws Exception;
	
	/**
	 * 
	 * @param employeeSearchCommand
	 * @param pageable
	 * @return
	 */
	public Page<EmployeeCommand> searchEmployee(EmployeeSearchCommand employeeSearchCommand, Pageable pageable) throws Exception;
	
	/**
	 * 
	 * @param employeeCommand
	 * @return
	 * @throws Exception
	 */
	public EmployeeCommand saveOrUpdateEmployee(EmployeeCommand employeeCommand) throws Exception;
	
	/**
	 * 
	 * @param employeeSearchCommand
	 * @param pageable
	 * @param isIncrementPage
	 * @return
	 */
	public Page<EmployeeCommand> loadPrevNexPage(EmployeeSearchCommand employeeSearchCommand, Pageable pageable, Boolean isIncrementPage) throws Exception;
}
