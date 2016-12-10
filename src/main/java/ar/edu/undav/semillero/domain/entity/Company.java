package ar.edu.undav.semillero.domain.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import ar.edu.undav.semillero.view.View;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue
	@JsonView(View.Summary.class)
	private Long id;
	@JsonView(View.Summary.class)
	private String name;
	@JsonView(View.Summary.class)
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

	public List<Interview> getInterviews() {
		return Collections.unmodifiableList(interviews);
	}

	@Override
	public String toString() {
		return "Company{" + "id=" + id + ", name='" + name + '\'' + ", contact='" + contact + '\'' + '}';
	}
}
