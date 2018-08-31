package br.edu.unitri.posjava.tcc.med4you.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by edufratari on 18/07/18.
 */
@Entity
public class State {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "state")
	private List<City> cities = new ArrayList<>();

	public State() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
}
