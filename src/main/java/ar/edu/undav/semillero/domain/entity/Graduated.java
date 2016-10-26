package ar.edu.undav.semillero.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Juan Lagostena on 12/10/16
 * jlagostena@bitsense.com.ar
 * .
 */
@Entity
@Table(name="graduated")
public class Graduated {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "graduated")
    private List<Interview> interviews = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn
    private Node node;

    public Graduated() {
    }

    public Graduated(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addInterview(Interview interview) {
        this.interviews.add(interview);
    }

    public List<Interview> getInterviews() {
        return Collections.unmodifiableList(interviews);
    }

    @Override
    public String toString() {
        return "Graduated{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

	public void setNode(Node node) {
		this.node = node;
	}

}