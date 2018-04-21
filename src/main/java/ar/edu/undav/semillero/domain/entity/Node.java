package ar.edu.undav.semillero.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import ar.edu.undav.semillero.view.View;

@Entity
@Table(name = "node")
public class Node {

	@Id
	@GeneratedValue
	@JsonView(View.Summary.class)
	private Long id;

	@JsonView(View.Summary.class)
	private String name;

	@JsonView(View.Summary.class)
	private String address;

	@OneToMany(mappedBy = "node")
	private List<Student> students = new ArrayList<>();

	public Node() {
	}

	public Node(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public List<Student> getStudents() {
		return students;
	}

}
