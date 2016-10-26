package ar.edu.undav.semillero.domain.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "interview")
public class Interview {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Graduated graduated;

	@ManyToOne
	private Company company;

	@Column(name = "Fecha")
	@Type(type = "date")
	private Date date;

	@Column(name = "Comentarios")
	private String comments;

	public Interview() {}

	public Interview(Graduated nameGraduated, Company company, Date date, String comments) {
		this.graduated = nameGraduated;
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


}
