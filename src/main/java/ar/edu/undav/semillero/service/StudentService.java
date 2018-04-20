package ar.edu.undav.semillero.service;

import ar.edu.undav.semillero.domain.entity.Student;
import ar.edu.undav.semillero.domain.repository.StudentRepository;
import ar.edu.undav.semillero.domain.repository.NodeRepository;
import ar.edu.undav.semillero.request.CreateStudentRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final NodeRepository nodeRepository;

    public StudentService(StudentRepository studentRepository, NodeRepository nodeRepository) {
        this.studentRepository = studentRepository;
        this.nodeRepository = nodeRepository;
    }

    @Transactional
    public Student save(CreateStudentRequest request) {
        return nodeRepository.findById(request.getNode())
                .map(node -> studentRepository.save(new Student(request.getName(), node, request.getEmail(),request.getResumeUrl())))
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public boolean deleteById(long id) {
        return studentRepository.softDeleteById(id) == 1;
    }

    @Transactional(readOnly = true)
    public Collection<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Collection<Student> findByNode(long nodeId) {
        return nodeRepository.findById(nodeId)
                .map(studentRepository::findByNode)
                .orElseGet(Collections::emptyList);
    }

    @Transactional(readOnly = true)
    public Collection<Student> findByDate(LocalDate when) {
        return studentRepository.findByDate(when);
    }
}
