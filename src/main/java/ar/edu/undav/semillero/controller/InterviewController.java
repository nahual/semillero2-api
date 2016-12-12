package ar.edu.undav.semillero.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;
import ar.edu.undav.semillero.service.CompanyService;
import ar.edu.undav.semillero.service.GraduatedService;
import ar.edu.undav.semillero.service.InterviewService;
import ar.edu.undav.semillero.view.View;

@RestController
@RequestMapping("/interview")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;
	@Autowired
	private GraduatedService graduatedService;
	@Autowired
	private CompanyService companyService;

	// Agregar una entrevista
	@RequestMapping(value = "", method = RequestMethod.POST)
	Interview addInterview(@RequestParam(value = "graduatedId") long gId, @RequestParam(value = "companyId") long cId) {

		// Busco el graduado y la compania de la DB segun el ID dado
		Graduated graduated = graduatedService.findById(gId);
		Company company = companyService.findById(cId);

		Interview interview = new Interview(graduated, company, new Date(), "Sin comentarios");
		interviewService.save(interview);
		return interview;
	}

	// Obtener entrevista por id
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public  ResponseEntity<Interview> getInterview(@PathVariable long id) {
		Interview interview = interviewService.findById(id);
		if(interview == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}else{
			return ResponseEntity.ok(interview);
		}
	}


	// Obtener todas las entrevistas
	@JsonView(View.Summary.class)
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Interview> getInterview(@RequestParam(value = "order", defaultValue = "-1") int order,
			@RequestParam(value = "año", defaultValue = "-1") int año,
			@RequestParam(value = "mes", defaultValue = "-1") int mes,
			@RequestParam(value = "dia", defaultValue = "-1") int dia,
			@RequestParam(value = "gId", defaultValue = "-1") long gId) throws ParseException {

		if (dia != -1) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			Date fecha = df.parse(año + "/" + mes + "/" + dia + "/");
			Collection<Interview> interviews = interviewService.findByDate(fecha);
			return interviews;
		} else if (gId != -1) {
			Graduated graduated = graduatedService.findById(gId);
			Collection<Interview> interviews = interviewService.findByGraduated(graduated);
			return interviews;
		} else if (order != -1){
			Collection<Interview> interviews = interviewService.findAllByOrderByIdDesc();
			return interviews;
		} else {
			Collection<Interview> interviews = interviewService.findAll();
			return interviews;
		}

	}
}