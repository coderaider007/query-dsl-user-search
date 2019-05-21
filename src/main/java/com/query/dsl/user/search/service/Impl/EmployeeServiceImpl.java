package com.query.dsl.user.search.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.query.dsl.user.search.command.EmployeeCommand;
import com.query.dsl.user.search.command.EmployeeSearchCommand;
import com.query.dsl.user.search.converter.EmployeeCommandToEmployee;
import com.query.dsl.user.search.converter.EmployeeToEmployeeCommand;
import com.query.dsl.user.search.domain.Employee;
import com.query.dsl.user.search.predicate.EmployeePredicate;
import com.query.dsl.user.search.repository.CountryRepository;
import com.query.dsl.user.search.repository.EmployeeRepository;
import com.query.dsl.user.search.repository.PositionRepository;
import com.query.dsl.user.search.repository.StateRepository;
import com.query.dsl.user.search.repository.StatusRepository;
import com.query.dsl.user.search.service.EmployeeService;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final EmployeeToEmployeeCommand employeeToEmployeeCommand;
	private final EmployeePredicate employeePredicate;
	private final PositionRepository positionRepository;
	private final StatusRepository statusRepository;
	private final EmployeeCommandToEmployee employeeCommandToEmployee;
	private final CountryRepository countryRepository;
	private final StateRepository stateRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository,
			EmployeeToEmployeeCommand employeeToEmployeeCommand, EmployeePredicate employeePredicate,
			PositionRepository positionRepository, StatusRepository statusRepository,
			EmployeeCommandToEmployee employeeCommandToEmployee, CountryRepository countryRepository,
			StateRepository stateRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.employeeToEmployeeCommand = employeeToEmployeeCommand;
		this.employeePredicate = employeePredicate;
		this.positionRepository = positionRepository;
		this.statusRepository = statusRepository;
		this.employeeCommandToEmployee = employeeCommandToEmployee;
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
	}


	/**
	 * 
	 */
	@Override
	public EmployeeCommand findById(Long id) throws Exception {
		Optional<Employee> oEmployee = this.employeeRepository.findById(id);
		if(oEmployee != null && oEmployee.isPresent()) {
			return this.employeeToEmployeeCommand.convert(oEmployee.get());
		}
		throw new Exception("Could not find the employee with ID : "+id);
	}
	

	/**
	 * 
	 */
	@Override
	public Page<EmployeeCommand> searchEmployee(EmployeeSearchCommand employeeSearchCommand, Pageable pageable){
		List<EmployeeCommand> employees = new ArrayList<>();
		BooleanExpression predicate = this.employeePredicate.getPredicate(employeeSearchCommand);
		Page<Employee> employeePage = this.employeeRepository.findAll(predicate, pageable); 
		if(!employeePage.isEmpty()) {
			employeePage.forEach(employee -> {
				employees.add(this.employeeToEmployeeCommand.convert(employee));
			});
		}
		Page<EmployeeCommand> pageCommand = new PageImpl<>(employees, pageable, employeePage.getTotalElements());
		employeeSearchCommand.setPage(employeePage.getNumber());
		return pageCommand;
	}
	
	/**
	 * 
	 */
	@Override
	public Page<EmployeeCommand> loadPrevNexPage(EmployeeSearchCommand employeeSearchCommand, Pageable pageable,
			Boolean isIncrementPage) {
		if(isIncrementPage) {
			pageable = this.incrementPage(pageable);
		}
		else {
			pageable = this.decrementPage(pageable);
		}
		List<EmployeeCommand> employees = new ArrayList<>();
		BooleanExpression predicate = this.employeePredicate.getPredicate(employeeSearchCommand);
		Page<Employee> employeePage = this.employeeRepository.findAll(predicate, pageable); 
		if(!employeePage.isEmpty()) {
			employeePage.forEach(employee -> {
				employees.add(this.employeeToEmployeeCommand.convert(employee));
			});
		}
		Page<EmployeeCommand> pageCommand = new PageImpl<>(employees, pageable, employeePage.getTotalElements());
		employeeSearchCommand.setPage(employeePage.getNumber());
		return pageCommand;
	}

	/**
	 * 
	 */
	@Override
	@Transactional
	public EmployeeCommand saveOrUpdateEmployee(EmployeeCommand employeeCommand) throws Exception {
		if(employeeCommand == null){
			throw new Exception("Invalid employeeCommand object");
		}
		Employee newEmployee = this.employeeCommandToEmployee.convert(employeeCommand);
		if(newEmployee.getId() != null) {
			Optional<Employee> opEmployee = this.employeeRepository.findById(newEmployee.getId());
			if(opEmployee.isPresent()) {
				Employee oldEmployee = opEmployee.get();
				this.updateEmployeeToNewValue(oldEmployee, newEmployee);
				newEmployee = this.employeeRepository.save(oldEmployee);
			}			
		}
		else {
			newEmployee.setPosition(this.positionRepository.findById(newEmployee.getPosition().getId()).get());
			newEmployee.setStatus(this.statusRepository.findById(newEmployee.getStatus().getId()).get());
			newEmployee.getAddress().setCountry(this.countryRepository.findById(newEmployee.getAddress().getCountry().getId()).get());
			newEmployee.getAddress().setState(this.stateRepository.findById(newEmployee.getAddress().getState().getId()).get());
			newEmployee = this.employeeRepository.save(newEmployee);
		}
		
		return this.employeeToEmployeeCommand.convert(newEmployee);
	}
	
	
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	private Pageable incrementPage(Pageable pageable) {
		return PageRequest.of(pageable.getPageNumber() + 1, pageable.getPageSize());
	}
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	private Pageable decrementPage(Pageable pageable) {
		return PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
	}
	
	/**
	 * 
	 * @param oldEmployee
	 * @param newEmployee
	 */
	private void updateEmployeeToNewValue(Employee oldEmployee,Employee newEmployee) {
		oldEmployee.setEndDate(newEmployee.getEndDate());
		oldEmployee.setFirstName(newEmployee.getFirstName());
		oldEmployee.setLastName(newEmployee.getLastName());
		oldEmployee.setPosition(this.positionRepository.findById(newEmployee.getPosition().getId()).get());
		oldEmployee.setStatus(this.statusRepository.findById(newEmployee.getStatus().getId()).get());
		oldEmployee.setStartDate(newEmployee.getStartDate());
		
		oldEmployee.getAddress().setAddress1(newEmployee.getAddress().getAddress1());
		oldEmployee.getAddress().setAddress2(newEmployee.getAddress().getAddress2());
		oldEmployee.getAddress().setCity(newEmployee.getAddress().getCity());
		oldEmployee.getAddress().setCountry(this.countryRepository.findById(newEmployee.getAddress().getCountry().getId()).get());
		oldEmployee.getAddress().setIsMainAddress(newEmployee.getAddress().getIsMainAddress());
		oldEmployee.getAddress().setPostalCode(newEmployee.getAddress().getPostalCode());
		oldEmployee.getAddress().setState(this.stateRepository.findById(newEmployee.getAddress().getState().getId()).get());
	}


}
