package com.mmt.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mmt.Entity.UserEntity;
import com.mmt.Entity.UserRoleEntity;
import com.mmt.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUserName(username);

		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoleEntity());
		return buildUserForAuthentication(user, authorities);
	}

	private org.springframework.security.core.userdetails.User buildUserForAuthentication(UserEntity user,
			List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRoleEntity> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRoleEntity userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}
