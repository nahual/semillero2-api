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

	public Graduated() {
	}

	public Graduated(String name, Node node) {
		this.name = name;
		this.node = node;
		date = LocalDate.now();
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean getDeleted() {
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
}
