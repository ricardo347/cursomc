package br.com.zerosystems.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.zerosystems.cursomc.domain.Cidade;
import br.com.zerosystems.cursomc.domain.Cliente;
import br.com.zerosystems.cursomc.domain.Endereco;
import br.com.zerosystems.cursomc.domain.enums.TipoCliente;
import br.com.zerosystems.cursomc.dto.ClienteDTO;
import br.com.zerosystems.cursomc.dto.ClienteNewDTO;
import br.com.zerosystems.cursomc.repositories.CidadeRepository;
import br.com.zerosystems.cursomc.repositories.ClienteRepository;
import br.com.zerosystems.cursomc.repositories.EnderecoRepository;
import br.com.zerosystems.cursomc.services.exceptions.DataIntegrityException;
import br.com.zerosystems.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
	
		return repo.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("não é possivel excluir uma Cliente");
		}
		repo.deleteById(id);
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, org.springframework.data.domain.Sort.Direction.valueOf(direction),orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null
				, objDTO.getNome()
				,objDTO.getEmail()
				,objDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objDTO.getTipo()),
				pe.encode(objDTO.getSenha())				
				);
		Endereco end = new Endereco(null
				, objDTO.getLogradouro()
				, objDTO.getNumero()
				, objDTO.getComplemento()
				, objDTO.getBairro()
				, objDTO.getCep()
				, cli, new Cidade(objDTO.getCidadeId(), null, null));
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
