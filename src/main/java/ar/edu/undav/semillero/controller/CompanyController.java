package ar.edu.undav.semillero.controller;


import java.util.Collection;

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
import ar.edu.undav.semillero.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired 
    private CompanyService companyService;
	   
    
	//Agregar una company
	@RequestMapping(value="",method=RequestMethod.POST)
    String addCompany(@RequestParam(value="name") String name,@RequestParam(value="contact") String contactName) {
		Company company = new Company(name,contactName);
		companyService.save(company);
	    return "La compañia "+ name + " y el contacto "+ contactName +" se han ingresado correctamente a la base de datos";
	}
	
	//Obtener companias. Si no se pasan parametros, se obtienen todas. Si se especifica parametro, se busca segun ese criterio (id, nombre)
    @RequestMapping(value="", method = RequestMethod.GET)
    public Collection<Company> getCompany(@RequestParam(value="id",defaultValue="-1") Long id,@RequestParam(value="name",defaultValue="null") String name) {
        if(id!=-1){
        	Collection<Company> company = companyService.findById(id);
            return company;
        }
        else if(!name.equals("null")){
        	Collection<Company> company = companyService.findByName(name);
            return company;        	
        }
        else{
        	Collection<Company> companies = companyService.findAll();
            return companies;
        }

    }
	
}