package ar.edu.undav.semillero.domain.entity;

import ar.edu.undav.semillero.view.View;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "graduated")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Graduated {

    @Id
    @GeneratedValue
    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    private LocalDate date;

    @JsonView(View.Summary.class)
    private boolean deleted = false;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "graduated")
    private List<Interview> interviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    @JsonView(View.Summary.class)
    private Node node;

    @JsonView(View.Summary.class)
    private String contact;

    @JsonView(View.Summary.class)
    @Column(name="resume_url")
    private String resumeUrl;

    @JsonView(View.Summary.class)
    @Column(name="looking_for_work")
    private boolean lookingForWork = true;

    //Feedback provided by professors
    @JsonView(View.Summary.class)
    private String feedback;

    public Graduated() {
    }

    public Graduated(String name, Node node) {
        this.name = name;
        this.node = node;
        date = LocalDate.now();
    }

    public Graduated(String name, Node node, String contact, String resumeUrl) {
        this(name,node);
        this.contact = contact;
        this.resumeUrl = resumeUrl;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Long getId() {
        return id;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public boolean isLookingForWork() {
        return lookingForWork;
    }

    public void setLookingForWork(boolean lookingForWork) {
        this.lookingForWork = lookingForWork;
    }
}
