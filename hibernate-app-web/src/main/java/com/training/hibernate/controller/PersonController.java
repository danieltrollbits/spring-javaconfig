package com.training.hibernate.controller;

import java.util.List;

import com.training.hibernate.services.PersonService;
import com.training.hibernate.dto.PersonDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView getAllPersons(){
		List<PersonDto> personDtos = personService.getAllPersons();
		ModelAndView model = new ModelAndView("index");
		model.addObject("persons",personDtos);
		model.addObject("roles",personService.getRoles());
		return model;
	}

	@RequestMapping(value="/add")
	public ModelAndView add(){
		ModelAndView model = new ModelAndView("person");
		return model;
	}

	@RequestMapping(value="/update", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request){
		PersonDto personDto = new PersonDto();
		if (request.getParameterMap().containsKey("personId")){
			model = new ModelAndView("person");
			personDto = personService.getPersonById(Integer.parseInt(request.getParameter("personId")));
			ModelAndView model.addObject("person",personDto);
    		return model;
    	}
    	else{
    		return new ModelAndView("redirect:/");
    	}
		
	}
}
