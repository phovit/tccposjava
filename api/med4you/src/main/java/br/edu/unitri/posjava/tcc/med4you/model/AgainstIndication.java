package br.edu.unitri.posjava.tcc.med4you.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by edufratari on 31/07/18.
 */
@Entity
public class AgainstIndication {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String description;

	public AgainstIndication() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTittle() {
		return title;
	}

	public void setTittle(String tittle) {
		this.title = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
