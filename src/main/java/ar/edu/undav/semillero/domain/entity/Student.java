package ar.edu.undav.semillero.domain.entity;

import ar.edu.undav.semillero.view.View;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "student")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student extends AbstractPersistable<Long> {

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    @Column(name="last_name")
    private String lastName;

    @JsonView(View.Summary.class)
    @Column(name="course_date")
    private LocalDate courseDate;

    @JsonView(View.Summary.class)
    @Column(name="graduation_date")
    private LocalDate graduationDate;

    @JsonView(View.Summary.class)
    private boolean deleted = false;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "student")
    private List<Interview> interviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    @JsonView(View.Summary.class)
    private Node node;

    @JsonView(View.Summary.class)
    private String email;

    @JsonView(View.Summary.class)
    private String phone;

    @JsonView(View.Summary.class)
    @Column(name="resume_url")
    private String resumeUrl;

    @JsonView(View.Summary.class)
    @Column(name="looking_for_work")
    private Boolean lookingForWork;

    @JsonView(View.Summary.class)
    @Column(name="working")
    private Boolean working;

    //Feedback provided by professors
    @JsonView(View.Summary.class)
    private String feedback;

    public Student() {
    }

    public Student(String name, Node node) {
        this(name, null, null, null, node, null, null, null, null, null, null);
    }

    public Student(String name, String lastName, LocalDate courseDate, LocalDate graduationDate, Node node, String email, String phone, String resumeUrl, Boolean lookingForWork, Boolean working, String feedback) {
        this.name = name;
        this.lastName = lastName;
        this.courseDate = courseDate;
        this.graduationDate = graduationDate;
        this.node = node;
        this.email = email;
        this.phone = phone;
        this.resumeUrl = resumeUrl;
        this.lookingForWork = lookingForWork;
        this.working = working;
        this.feedback = feedback;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getName() {
        return name;
    }

    public void addInterview(Interview interview) {
        interviews.add(interview);
    }

    public List<Interview> getInterviews() {
        return Collections.unmodifiableList(interviews);
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public void setLookingForWork(Boolean lookingForWork) {
        this.lookingForWork = lookingForWork;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getFeedback() {
        return feedback;
    }
}
