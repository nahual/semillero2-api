package ar.edu.undav.semillero.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Juan Lagostena on 12/10/16
 * jlagostena@bitsense.com.ar
 * .
 */
@Entity
@Table(name="company")
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String contact;

    @OneToMany(mappedBy = "company")
    private List<Interview> interviews = new ArrayList<>();

    public Company() {
    }

    public Company(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }


    public void addInterview(Interview interview) {
        this.interviews.add(interview);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
