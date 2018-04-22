package ar.edu.undav.semillero.service;

import ar.edu.undav.semillero.domain.entity.Student;
import ar.edu.undav.semillero.domain.repository.NodeRepository;
import ar.edu.undav.semillero.domain.repository.StudentRepository;
import ar.edu.undav.semillero.dto.StudentDTO;
import ar.edu.undav.semillero.request.CreateStudentRequest;
import ar.edu.undav.semillero.request.FilterStudentsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final NodeRepository nodeRepository;
    private final QueryRunner queryRunner;

    public StudentService(StudentRepository studentRepository, NodeRepository nodeRepository, QueryRunner queryRunner) {
        this.studentRepository = studentRepository;
        this.nodeRepository = nodeRepository;
        this.queryRunner = queryRunner;
    }

    @Transactional
    public Student save(CreateStudentRequest request) {
        return nodeRepository.findById(request.getNode())
                .map(node -> studentRepository.save(
                        new Student(request.getName(),
                            request.getLastName(),
                            request.getCourseDate(),
                            request.getGraduationDate(),
                            node,
                            request.getEmail(),
                            request.getPhone(),
                            request.getResumeUrl(),
                            request.isLookingForWork(),
                            request.isWorking(),
                            request.getFeedback())))
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
    public Page<StudentDTO> list(FilterStudentsRequest filters, Pageable pageable) {
        QueryBuilder<StudentDTO> queryBuilder = QueryBuilder.of(StudentDTO.class)
                    .select("new ar.edu.undav.semillero.dto.StudentDTO(s.id, s.name, s.lastName, s.node, s.deleted, " +
                        "s.courseDate, s.graduationDate, s.email, s.phone, s.resumeUrl, s.lookingForWork, s.working, s.feedback, " +
                         " (select count(i) from ar.edu.undav.semillero.domain.entity.Interview i where i.student = s))")
                    .from(Student.class, "s")
                    .gte("graduationDate", filters.getMinGraduationDate())
                    .lte("graduationDate", filters.getMaxGraduationDate())
                    .eq("node.id", filters.getNode())
                    .notNull(filters.isGraduated(), "graduationDate")
                    .condition(filters.isWorking(), "working = true")
                    .condition(filters.isLookingForWork(), "lookingForWork = true");
        return queryRunner.run(StudentDTO.class, queryBuilder, pageable);
    }
}
