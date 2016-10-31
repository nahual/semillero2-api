package ar.edu.undav.semillero.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="node")
public class Node {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "node")
	private List<Graduated> graduateds = new ArrayList<>();

   
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
    
    public void addGraduated(Graduated graduated) {
        this.graduateds.add(graduated);
    }

	public List<Graduated> getGraduateds() {
		return graduateds;
	}

}