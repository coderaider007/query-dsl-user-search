package com.query.dsl.user.search.command;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.query.dsl.user.search.domain.Country;

import lombok.Data;

@Data
public class AddressCommand {

	private Long id;
	
	@NotBlank
	private String address1;
	
	private String address2;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String postalCode;
	
	@NotNull
	@Valid
	private CountryCommand countryCommand;
	
	@NotNull
	@Valid
	private StateCommand StateCommand;
	
	@NotNull
	private Boolean  isMainAddress = Boolean.TRUE;
	
	private Long version;
}
