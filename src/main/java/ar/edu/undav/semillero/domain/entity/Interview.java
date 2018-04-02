package ar.edu.undav.semillero.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import ar.edu.undav.semillero.view.View;

@Entity
@Table(name = "interview")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Interview {

	@Id
	@GeneratedValue
	@JsonView(View.Summary.class)
	private Long id;

	@ManyToOne
	@JsonView(View.Summary.class)
	private Graduated graduated;

	@ManyToOne
	@JsonView(View.Summary.class)
	private Company company;

	@Column(name = "Fecha")
	@Type(type = "date")
	@JsonView(View.Summary.class)
	private Date date;

	@Column(name = "Comentarios")
	@JsonView(View.Summary.class)
	private String comments;

	public Interview() {
	}

	public Interview(Graduated graduated, Company company, Date date, String comments) {
		this.graduated = graduated;
		this.company = company;
		this.date = date;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getComments() {
		return comments;
	}

	public Graduated getGraduated() {
		return graduated;
	}

	public Company getCompany() {
		return company;
	}

}
