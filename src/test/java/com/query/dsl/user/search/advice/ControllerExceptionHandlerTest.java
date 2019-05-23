package com.query.dsl.user.search.advice;

import static org.junit.Assert.*;

import java.net.BindException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javassist.NotFoundException;

public class ControllerExceptionHandlerTest {

	private ControllerExceptionHandler controllerExceptionHandler;
	@Before
	public void setUp() throws Exception {
		this.controllerExceptionHandler = new ControllerExceptionHandler();
	} 

	@Test
	public void testHandleBindException() {
		BindException bindException = new BindException("Unable to bind values to object");
		ModelAndView modelAndView = this.controllerExceptionHandler.handleBindException(bindException);
		assertNotNull(modelAndView);
		assertEquals(modelAndView.getViewName(), "400error");
		assertNotNull(modelAndView.getModel().get("exception"));
	}

	@Test
	public void testHandleNotFoundException() {
		NotFoundException notFoundException = new NotFoundException("Not found");
		ModelAndView modelAndView = this.controllerExceptionHandler.handleNotFoundException(notFoundException);
		assertNotNull(modelAndView);
		assertEquals(modelAndView.getViewName(), "404error");
		assertNotNull(modelAndView.getModel().get("exception"));
	}

}
