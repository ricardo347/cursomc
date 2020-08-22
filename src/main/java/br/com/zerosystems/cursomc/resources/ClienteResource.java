package br.com.zerosystems.cursomc.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.zerosystems.cursomc.domain.Cliente;
import br.com.zerosystems.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService serivce;

	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {		
		Cliente obj = serivce.buscar(id);		
		return ResponseEntity.ok().body(obj);		
	}
}