package ar.edu.undav.semillero.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.entity.Interview;
import ar.edu.undav.semillero.service.CompanyService;
import ar.edu.undav.semillero.view.View;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	// Agregar una company
	@RequestMapping(value = "", method = RequestMethod.POST)
	Company addCompany(@RequestParam(value = "name") String name, @RequestParam(value = "contact") String contactName) {
		Company company = new Company(name, contactName);
		companyService.save(company);
		return company;
	}

	// Obtener company por ID
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Company> getCompany(@PathVariable Long id) {
		Company company = companyService.findById(id);
		if(company == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}else{
			return ResponseEntity.ok(company);
		}
	}


	// Obtener todas las companias, si se pasa parametro, se devuelve por nombre
	@ResponseBody
	@JsonView(View.Summary.class)
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Collection<Company>> getCompany(@RequestParam(value = "name", defaultValue = "null") String name) {
		if (name.equals("null")) {
			Collection<Company> companies = companyService.findAll();
			if(companies == null){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}
				else{
					return ResponseEntity.ok(companies);
				}
		}
		else{
			Collection<Company> companies = companyService.findByName(name);
			if(companies.isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}
				else{
					return ResponseEntity.ok(companies);
				}
			}
	}

}
