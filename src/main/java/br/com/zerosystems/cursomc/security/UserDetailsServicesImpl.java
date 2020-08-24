package br.com.zerosystems.cursomc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zerosystems.cursomc.domain.Cliente;
import br.com.zerosystems.cursomc.repositories.ClienteRepository;

@Service
public class UserDetailsServicesImpl implements UserDetailsService{

	@Autowired
	private ClienteRepository repo;
	
	public UserDetailsServicesImpl() {
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = repo.findByEmail(email);
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}
