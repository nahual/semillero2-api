package ar.edu.undav.semillero.controller;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Interview;
import ar.edu.undav.semillero.service.GraduatedService;
import ar.edu.undav.semillero.service.InterviewService;
import ar.edu.undav.semillero.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/interview")
@CrossOrigin
public class InterviewController {

    private final InterviewService interviewService;
    private final GraduatedService graduatedService;

    public InterviewController(InterviewService interviewService, GraduatedService graduatedService) {
        this.interviewService = interviewService;
        this.graduatedService = graduatedService;
    }

    // Agregar una entrevista
    @PostMapping("")
    public Interview addInterview(@RequestParam(value = "graduatedId") long gId, @RequestParam(value = "companyId") long cId) {
        return interviewService.save(gId, cId);
    }

    // Obtener entrevista por id
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Interview> getInterview(@PathVariable long id) {
        return WebUtils.nullToNotFound(interviewService.findById(id));
    }

    // Obtener todas las entrevistas
    @JsonView(View.Summary.class)
    @GetMapping("")
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
            Graduated graduated = graduatedService.findById(gId).get();
            Collection<Interview> interviews = interviewService.findByGraduated(graduated);
            return interviews;
        } else if (order != -1) {
            Collection<Interview> interviews = interviewService.findAllByOrderByIdDesc();
            return interviews;
        } else {
            Collection<Interview> interviews = interviewService.findAll();
            return interviews;
        }
    }
}
