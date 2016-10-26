package ar.edu.undav.semillero.controller;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
/******************************************************
 * Al runnear esto, si entro a localhost:8080/saveGraduated guardo un graduado en la DB semillero
 *****************************************************/
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;
import ar.edu.undav.semillero.service.CompanyService;
import ar.edu.undav.semillero.service.GraduatedService;
import ar.edu.undav.semillero.service.InterviewService;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Autowired 
    private InterviewService interviewService;
    private GraduatedService graduatedService;
    private CompanyService companyService;
    
	//Agregar una entrevista
	@RequestMapping(value="",method=RequestMethod.POST)
    String addInterview(@RequestParam(value="graduatedId") Long gId,@RequestParam(value="companyId") Long cId) {
		
		//Busco el graduado y la compania de la DB segun el ID dado
		Collection<Graduated> graduated = graduatedService.findById(gId);
		Collection<Company> company = companyService.findById(cId);
	        
			
	    Interview interview = new Interview(graduated.iterator().next(),company.iterator().next(),new Date(),"Sin comentarios");
		interviewService.save(interview);
		return "La entrevista se ha guardado satisfactoriamente";
	}
		
}