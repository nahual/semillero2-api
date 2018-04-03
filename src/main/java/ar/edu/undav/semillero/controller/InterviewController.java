package ar.edu.undav.semillero.controller;

import ar.edu.undav.semillero.domain.entity.Interview;
import ar.edu.undav.semillero.service.InterviewService;
import ar.edu.undav.semillero.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/interview")
@CrossOrigin
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
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
    public Collection<Interview> getInterview(@RequestParam(value = "order", required = false) Integer order,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "when", required = false) LocalDate when,
            @RequestParam(value = "gId", required = false) Long gId) {
        if (when != null) {
            return interviewService.findByDate(when);
        } else if (gId != null) {
            return interviewService.findByGraduated(gId);
        } else if (order != null) {
            return interviewService.findAllOrderByIdDesc();
        } else {
            return interviewService.findAll();
        }
        }
    }
