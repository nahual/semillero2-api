package ar.edu.undav.semillero.controller;

import ar.edu.undav.semillero.domain.entity.Student;
import ar.edu.undav.semillero.request.CreateStudentRequest;
import ar.edu.undav.semillero.service.StudentService;
import ar.edu.undav.semillero.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Guardar un graduado
    @PostMapping
    public Student saveStudent(@Valid @RequestBody CreateStudentRequest request) {
        return studentService.save(request);
    }

    // Obtener graduados por ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        return WebUtils.emptyToNotFound(studentService.findById(id));
    }

    // Eliminar un graduado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        return studentService.deleteById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Obtener todos los graduados
    @JsonView(View.Summary.class)
    @GetMapping
    public Collection<Student> getStudent(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "when", required = false) LocalDate when, @RequestParam(value = "node", required = false) Long nodeId) {
        if (nodeId != null) {
            return studentService.findByNode(nodeId);
        } else if (when != null) {
            return studentService.findByDate(when);
        } else {
            return studentService.findAll();
        }
    }
}
