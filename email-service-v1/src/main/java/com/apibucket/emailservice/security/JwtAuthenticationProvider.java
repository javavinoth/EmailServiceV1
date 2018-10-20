package com.apibucket.emailservice.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.apibucket.emailservice.exception.JwtTokenMalformedException;
import com.apibucket.emailservice.security.model.AuthenticatedUser;
import com.apibucket.emailservice.security.model.JwtAuthenticationToken;
import com.apibucket.emailservice.security.pojo.JwtUserDto;
import com.apibucket.emailservice.utils.JwtTokenValidator;


/**
 * @author vinoth
 *
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtTokenValidator jwtTokenValidator;

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
		String token = jwtAuthenticationToken.getToken();

		JwtUserDto parsedUser;
		try {
			parsedUser = jwtTokenValidator.parseToken(token);
		} catch (Exception e) {
//			e.printStackTrace();
			throw new JwtTokenMalformedException("JWT token is not valid");
		}

		if (parsedUser == null) {
			throw new JwtTokenMalformedException("JWT token is not valid");
		}

		List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.getRole());

		return new AuthenticatedUser(parsedUser.getId(), parsedUser.getUsername(), token, authorityList);
	}

}
