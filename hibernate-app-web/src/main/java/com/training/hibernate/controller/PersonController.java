package com.training.hibernate.controller;

import java.util.List;
import java.util.ArrayList;

import com.training.hibernate.services.PersonService;
import com.training.hibernate.dto.PersonDto;
import com.training.hibernate.dto.PersonTest;

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
	public ModelAndView view(@PathVariable String personId){
		PersonDto personDto = new PersonDto();
		if (personId != null){
			personDto = personService.getPersonById(Integer.parseInt(personId));
			ModelAndView model = new ModelAndView("person");
			model.addObject("person",personDto);
    		return model;
    	}
    	else{
    		return new ModelAndView("redirect:/?message=Please select one person");
    	}
	}	


	@RequestMapping(value="/add")
	public ModelAndView add(){
		ModelAndView model = new ModelAndView("person");
		return model;
	}

	@RequestMapping(value="/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam(value="personId",required=false) int[] personId){
		PersonDto personDto = new PersonDto();
		if (personId != null && personId.length == 1){
			personDto = personService.getPersonById(personId[0]);
			ModelAndView model = new ModelAndView("person");
			model.addObject("person",personDto);
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

	@RequestMapping(value="/test",method = RequestMethod.POST)
	public ModelAndView test(@Valid PersonDto personDto, BindingResult result){
		if(result.hasErrors()){
			return new ModelAndView("redirect:/?error="+"Missing");
		}
		else{
			return new ModelAndView("redirect:/?message="+"Person saved");	
		}
		
	}

	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request){
		PersonDto personDto = personService.createPersonDto(request.getParameter("personId"), request.getParameter("firstName"),
				request.getParameter("middleName"), request.getParameter("lastName"),
				request.getParameter("gender"), request.getParameter("birthdate"), request.getParameter("employed"),
				request.getParameter("gwa"), request.getParameter("street"), request.getParameter("houseNo"),
				request.getParameter("barangay"), request.getParameter("subdivision"), request.getParameter("city"),
				request.getParameter("zipcode"), request.getParameterValues("contactType"),
				request.getParameterValues("contactValue"), request.getParameterValues("contactId"),
				request.getParameterValues("role"),request.getParameterValues("savedContactValue"),request.getParameterValues("savedContactType"));

		boolean isRequired = personService.isRequired(request.getParameter("firstName"),
				request.getParameter("middleName"), request.getParameter("lastName"),
				request.getParameter("gender"), request.getParameter("employed"), request.getParameter("street"),
				request.getParameter("barangay"), request.getParameter("subdivision"),
				request.getParameter("city"), request.getParameter("zipcode"),
				request.getParameterValues("contactType"), request.getParameterValues("contactValue"),
				request.getParameterValues("role"));

		boolean isNumber = personService.isNumber(request.getParameter("houseNo"));
		boolean isDate = personService.isDate(request.getParameter("birthdate"));
		boolean isDecimal = personService.isDecimal(request.getParameter("gwa"));

		if (isRequired && isNumber && isDate && isDecimal){
			personService.saveOrUpdatePerson(personDto);
			return new ModelAndView("redirect:/?message="+"Person saved");
		}
		else{
			List<String> errors = new ArrayList<>();
			if(!isRequired){
				errors.add("Missing required fields");
			}
			if(!isDate){
				errors.add("Invalid date format");
			}
			if(!isNumber){
				errors.add("Invalid House no");
			}
			if(!isDecimal){
				errors.add("Invalid gwa");
			}
			ModelAndView model = new ModelAndView("person");
			model.addObject("errors",errors);
			model.addObject("person",personDto);
			return model;
		}	
	}

}
