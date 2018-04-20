package ar.edu.undav.semillero.domain.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "company")
public class Company extends AbstractPersistable<Long> {

    private String name;
    private String contact;
    private String email;
    private String comments;

    @OneToMany(mappedBy = "company")
    private List<Interview> interviews = new ArrayList<>();

    public Company() {
    }

    public Company(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public void addInterview(Interview interview) {
        interviews.add(interview);
    }

    public List<Interview> getInterviews() {
        return Collections.unmodifiableList(interviews);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    public String getEmail() {
        return email;
    }

    public String getComments() {
        return comments;
    }

}
