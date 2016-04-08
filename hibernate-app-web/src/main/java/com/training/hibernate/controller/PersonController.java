package com.training.hibernate.controller;

import java.util.List;

import com.training.hibernate.services.PersonService;
import com.training.hibernate.dto.PersonDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {
	@Autowired
	private PersonService personService;

	@RequestMapping(value="/")
	public ModelAndView getAllPersons(){
		List<PersonDto> personDtos = personService.getAllPersons();
		ModelAndView model = new ModelAndView("index");
		model.addObject("persons",personDtos);
		model.addObject("roles",personService.getRoles());
		return model;
	}
}
