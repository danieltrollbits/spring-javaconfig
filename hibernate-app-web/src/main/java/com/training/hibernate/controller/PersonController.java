package com.training.hibernate.controller;

import java.util.List;
import java.util.ArrayList;

import com.training.hibernate.services.PersonService;
import com.training.hibernate.dto.PersonDto;
import com.training.hibernate.dto.RoleDto;
import com.training.hibernate.dto.ContactDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import com.training.hibernate.editor.DateEditor;
import com.training.hibernate.editor.GenderEditor;
import com.training.hibernate.model.Gender;
import java.util.Date;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;


@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
        binder.registerCustomEditor(Gender.class, new GenderEditor());
    }

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView list(){
		List<PersonDto> personDtos = personService.getAllPersons();
		ModelAndView model = new ModelAndView("index");
		model.addObject("persons",personDtos);
		model.addObject("roles",personService.getRoles());
		return model;
	}

	@RequestMapping(value="view/{personId}", method = RequestMethod.POST)
	public ModelAndView view(@PathVariable int[] personId){
		PersonDto personDto = new PersonDto();
		if(personId != null && personId.length == 1){
			personDto = personService.getPersonById(personId[0]);
			ModelAndView model = new ModelAndView("view");
			model.addObject("personDto",personDto);
			model.addObject("roles",personService.getRoles());
    		return model;
		}
		else{
			return new ModelAndView("redirect:/?message=Please select one person");
		}
	}	


	@RequestMapping(value="/add")
	public ModelAndView add(){
		ModelAndView model = new ModelAndView("person");
		PersonDto personDto = new PersonDto();
		model.addObject("roles",personService.getRoles());
		model.addObject("personDto",personDto);
		return model;
	}

	@RequestMapping(value="/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam(value="personId",required=false) int[] personId){
		PersonDto personDto = new PersonDto();
		if (personId != null && personId.length == 1){
			personDto = personService.getPersonById(personId[0]);
			ModelAndView model = new ModelAndView("person");
			model.addObject("personDto",personDto);
			model.addObject("roles",personService.getRoles());
    		return model;
    	}
    	else{
    		return new ModelAndView("redirect:/?message=Please select one person");
    	}
	}

	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam(value="personId",required=false) int[] personId){
		if(personId != null){	
			for(int id : personId){
				personService.deletePerson(id);
			}
			return new ModelAndView("redirect:/?message=Person/s deleted");
		}else{
			return new ModelAndView("redirect:/?message=Please select person/s to delete");
		}
	}

	@RequestMapping(value="/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam(value="firstName",required=false) String firstName,
		@RequestParam(value="middleName",required=false) String middleName,
		@RequestParam(value="lastName",required=false) String lastName,
		@RequestParam(value="roles",required=false) String roles){
		ModelAndView model = new ModelAndView("index");
		List<PersonDto> personDtos = null;
		if (lastName.isEmpty() && firstName.isEmpty() && middleName.isEmpty() && roles.isEmpty()){
			personDtos = personService.getAllPersons();
		}
		else{
			personDtos = personService.searchPerson(lastName,firstName,
			middleName,roles);
		}
		model.addObject("persons",personDtos);
		model.addObject("roles",personService.getRoles());
		return model;
	}

	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ModelAndView search(@Valid PersonDto personDto, BindingResult result){
		List<ContactDto> contactDtos = new ArrayList<>();
		for (ContactDto c : personDto.getContactDtos()){
			if (c.getType() != null && c.getValue() != ""){
				contactDtos.add(c);
			}
		}
		personDto.setContactDtos(contactDtos);
		if(result.hasErrors()){
			ModelAndView model = new ModelAndView("person");
			model.addObject("roles",personService.getRoles());
			return model;
		}
		else{
			personService.saveOrUpdatePerson(personDto);
			return new ModelAndView("redirect:/?message=Person saved");
		}
	}

}
