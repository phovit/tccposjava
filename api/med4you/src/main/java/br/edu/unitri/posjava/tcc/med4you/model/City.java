package br.edu.unitri.posjava.tcc.med4you.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Created by edufratari on 18/07/18.
 */
@Entity
public class City {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
    
    @OneToMany(mappedBy = "city")
    private List<Address> addresses = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
