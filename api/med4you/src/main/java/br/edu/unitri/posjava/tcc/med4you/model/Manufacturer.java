package br.edu.unitri.posjava.tcc.med4you.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by edufratari on 31/07/18.
 */
@Entity
public class Manufacturer {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String cnpj;

	@OneToOne(cascade=CascadeType.ALL)
	private Address address;

	public Manufacturer() {
		super();
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
