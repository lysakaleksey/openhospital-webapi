package org.isf.component;

import org.isf.menu.model.User;
import org.isf.menu.service.UserIoOperationRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class OhWebUserDetailsService implements UserDetailsService {
	private final UserIoOperationRepository repository;

	public OhWebUserDetailsService(UserIoOperationRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUserName(username);
		if (user == null)
			throw new UsernameNotFoundException("The user does not exist");

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUserGroupName().getCode().toUpperCase());
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPasswd(), user.getActive() == 1,
			true, true, true, Collections.singletonList(authority));
	}
}