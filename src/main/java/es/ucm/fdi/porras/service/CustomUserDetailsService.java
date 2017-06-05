package es.ucm.fdi.porras.service;

import es.ucm.fdi.porras.model.Role;
import es.ucm.fdi.porras.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import es.ucm.fdi.porras.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private static Logger log = Logger.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	public CustomUserDetailsService() {
		super();
	}

	@Override
    public UserDetails loadUserByUsername(final String username){
		final User user = userRepository.findOneByLogin(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		final List<GrantedAuthority> roles = new ArrayList<>();
		for (final Role role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
    }
}