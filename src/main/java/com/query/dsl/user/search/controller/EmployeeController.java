package com.query.dsl.user.search.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.query.dsl.user.search.command.CountryCommand;
import com.query.dsl.user.search.command.EmployeeCommand;
import com.query.dsl.user.search.command.EmployeeSearchCommand;
import com.query.dsl.user.search.command.PositionCommand;
import com.query.dsl.user.search.command.StateCommand;
import com.query.dsl.user.search.command.StatusCommand;
import com.query.dsl.user.search.service.CountryService;
import com.query.dsl.user.search.service.EmployeeService;
import com.query.dsl.user.search.service.PositionService;
import com.query.dsl.user.search.service.StateService;
import com.query.dsl.user.search.service.StatusService;
import com.query.dsl.user.search.validator.EmployeeSearchCommandValidator;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

	private static final String EMPLOYEE_FORM = "employee/employeeForm";
	private static final String INDEX = "index";
	private final EmployeeService employeeService;
	private final StatusService statusService;
	private final PositionService positionService;
	private final StateService stateService;
	private final CountryService countryService;
	private final EmployeeSearchCommandValidator employeeSearchCommandValidator;
	
	public EmployeeController(EmployeeService employeeService, StatusService statusService,
			PositionService positionService, StateService stateService, CountryService countryService,
			EmployeeSearchCommandValidator employeeSearchCommandValidator) {
		super();
		this.employeeService = employeeService;
		this.statusService = statusService;
		this.positionService = positionService;
		this.stateService = stateService;
		this.countryService = countryService;
		this.employeeSearchCommandValidator = employeeSearchCommandValidator;
	}

	@GetMapping("{employeeId}/show")
	public String findById(@PathVariable("employeeId") Long employeeId, Model model) throws Exception {
		model.addAttribute("employee", this.employeeService.findById(employeeId));
		model.addAttribute("employeeSearchCommand", new EmployeeSearchCommand());
		return "employee/show";
	}
	
	@PostMapping("{employeeId}/show")
	public String findById(@ModelAttribute("employeeSearchCommand") EmployeeSearchCommand employeeSearchCommand, @PathVariable("employeeId") Long employeeId, Model model) throws Exception {
		model.addAttribute("employee", this.employeeService.findById(employeeId));
		model.addAttribute("employeeSearchCommand", employeeSearchCommand);
		return "employee/show";
	}
	
	@PostMapping("/search/new")
	public String searchEmployee(@Valid @ModelAttribute("employeeSearchCommand") EmployeeSearchCommand employeeSearchCommand, BindingResult result, Pageable pageable, Model model) {
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> {
				log.error(error.getDefaultMessage());
			});
			return INDEX;
		}
		model.addAttribute("searchResults", this.employeeService.searchEmployee(employeeSearchCommand, pageable));
		model.addAttribute("employeeSearchCommand", employeeSearchCommand);
		return INDEX;
	}
	
	@PostMapping("/search/next")
	public String searchEmployeeNextPage(@ModelAttribute("employeeSearchCommand") EmployeeSearchCommand employeeSearchCommand, Pageable pageable, Model model) {
		
		model.addAttribute("searchResults", this.employeeService.loadPrevNexPage(employeeSearchCommand, pageable, true));
		model.addAttribute("employeeSearchCommand", employeeSearchCommand);
		return INDEX;
	}
	
	@PostMapping("/search/prev")
	public String searchEmployeePrevPage(@ModelAttribute("employeeSearchCommand") EmployeeSearchCommand employeeSearchCommand, Pageable pageable, Model model) {
		
		model.addAttribute("searchResults", this.employeeService.loadPrevNexPage(employeeSearchCommand, pageable, false));
		model.addAttribute("employeeSearchCommand", employeeSearchCommand);
		return INDEX;
	}
	
	@GetMapping("/search/reset")
	public String searchEmployeeReset(Model model) {
		model.addAttribute("employeeSearchCommand", new EmployeeSearchCommand());
		return INDEX;
	}
	
	@GetMapping("/new")
	public String saveEmployeeNew(Model model) {
		model.addAttribute("employeeForm", new EmployeeCommand());
		return EMPLOYEE_FORM;
	}
	
	@RequestMapping(value="/{employeeId}/update", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateEmployee(@ModelAttribute("employeeSearchCommand") EmployeeSearchCommand employeeSearchCommand, @PathVariable("employeeId") Long employeeId, Model model) throws Exception {
		model.addAttribute("employeeForm", this.employeeService.findById(employeeId));
		model.addAttribute("employeeSearchCommand", employeeSearchCommand);
		return EMPLOYEE_FORM;
	}
	
	@PostMapping("/save")
	public String saveOrUpdateEmployee(@Valid @ModelAttribute("employeeForm") EmployeeCommand employeeCommand, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> {
				log.error(error.getDefaultMessage());
			});
			return EMPLOYEE_FORM;
		}
		employeeCommand = this.employeeService.saveOrUpdateEmployee(employeeCommand);
		return "redirect:/employee/"+employeeCommand.getId()+"/show";
	}
	
	@ModelAttribute("positions")
	public List<PositionCommand> loadAllPositions(){
		return this.positionService.loadAllPositions();
	}
	
	@ModelAttribute("statuses")
	public List<StatusCommand> loadAllStatuses(){
		return this.statusService.loadAllStatuses();
	}
	
	@ModelAttribute("states")
	public List<StateCommand> loadAllStates(){
		return this.stateService.loadAllStates();
	}
	
	@ModelAttribute("countries")
	public List<CountryCommand> loadAllCountries(){
		return this.countryService.loadAllCountries();
	}
	
	@InitBinder("employeeSearchCommand")
	protected void setupBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(this.employeeSearchCommandValidator);
	}
}
