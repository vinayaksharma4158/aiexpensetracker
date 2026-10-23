package com.damon.ai.expense.trackerr.aiexpensetracker.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.damon.ai.expense.trackerr.aiexpensetracker.model.CustomUserTokenSet;
import com.damon.ai.expense.trackerr.aiexpensetracker.model.FinanceUser;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.CustomTokenRepository;
import com.damon.ai.expense.trackerr.aiexpensetracker.repository.FinanceUserRepository;
import com.damon.ai.expense.trackerr.aiexpensetracker.util.JwtUtil;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private FinanceUserRepository userRepository;

	@Autowired
	private CustomTokenRepository repository;
	
	@Autowired
	private JwtUtil jwtUtil;

	private static final Logger logger = LoggerFactory.getLogger(OtpService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		FinanceUser user = userRepository.findByEmail(username) // or findByUsername
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
	}
 
	public String generateTokenAndStroing(UserDetails userDetails) {

		final String jwt = jwtUtil.generateToken(userDetails);

		logger.info("userDetails : {}", userDetails);

		logger.debug("userDetails", userDetails);

		CustomUserTokenSet customUserTokenSet = new CustomUserTokenSet();
		customUserTokenSet.setEmail(userDetails.getUsername());
		customUserTokenSet.setToken(jwt);
		customUserTokenSet.setTimestamp(java.time.LocalDateTime.now());
		
		repository.save(customUserTokenSet);
		
		return jwt;
	}
}
