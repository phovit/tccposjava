package br.edu.unitri.posjava.tcc.med4you.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unitri.posjava.tcc.med4you.model.AdverseReactions;
import br.edu.unitri.posjava.tcc.med4you.service.AdverseReactionsService;

/**
 * Created by edufratari on 31/07/18.
 */
@RestController
@RequestMapping("adversereactions")
public class AdverseReactionsController {

	@Autowired
	private AdverseReactionsService service;

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody AdverseReactions adverseReaction) {
		service.save(adverseReaction);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody AdverseReactions adverseReaction) {
		service.update(adverseReaction);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<AdverseReactions> findAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public AdverseReactions findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(Long id) {
		service.delete(id);
	}

}
