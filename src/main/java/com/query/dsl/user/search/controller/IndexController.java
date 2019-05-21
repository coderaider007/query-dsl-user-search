package com.query.dsl.user.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.query.dsl.user.search.command.EmployeeSearchCommand;
import com.query.dsl.user.search.service.PositionService;
import com.query.dsl.user.search.service.StatusService;

@Controller
public class IndexController {
	
	private final StatusService statusService;
	private final PositionService positionService;
	
	public IndexController(StatusService statusService, PositionService positionService) {
		super();
		this.statusService = statusService;
		this.positionService = positionService;
	}

	@GetMapping({"/", "index","index.html"})
	public String getIndexPage(Model model) {
		model.addAttribute("employeeSearchCommand", new EmployeeSearchCommand());
		model.addAttribute("positions", this.positionService.loadAllPositions());
		model.addAttribute("statuses", this.statusService.loadAllStatuses());
		return "index";
	}
}
