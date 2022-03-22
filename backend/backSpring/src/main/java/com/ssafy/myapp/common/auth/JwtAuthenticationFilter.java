package com.ssafy.myapp.common.auth;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.common.util.JwtTokenUtil;
import com.ssafy.myapp.common.util.ResponseBodyWriteUtil;
import com.ssafy.myapp.db.entity.User;



public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	private UserService userService;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
		super(authenticationManager);
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Read the Authorization header, where the JWT Token should be
        String header = request.getHeader(JwtTokenUtil.HEADER_STRING);

        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        
        try {
            // If header is present, try grab user principal from database and perform authorization
            Authentication authentication = getAuthentication(request);
            System.out.println(authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            ResponseBodyWriteUtil.sendError(request, response, ex);
            return;
        }
        
        filterChain.doFilter(request, response);
	}
	
	@Transactional(readOnly = true)
    public Authentication getAuthentication(HttpServletRequest request) throws Exception {
        String token = request.getHeader(JwtTokenUtil.HEADER_STRING);

        if (token != null) {
            // parse the token and validate it (decode)
            JWTVerifier verifier = JwtTokenUtil.getVerifier();
            JwtTokenUtil.handleError(token);
            DecodedJWT decodedJWT = verifier.verify(token.replace(JwtTokenUtil.TOKEN_PREFIX, ""));
            String email = decodedJWT.getSubject();
            
            // Search in the DB if we find the user by token subject (username)
            // If so, then grab user details and create spring auth token using username, pass, authorities/roles
            if (email != null) {

            		User user = userService.getUserByEmail(email);
                    System.out.println("jwtAuth :"+ user);
                if(user != null) {
                		SsafyUserDetails userDetails = new SsafyUserDetails(user);
                    System.out.println("jwtAuth :"+ userDetails.getAuthorities());
                		UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(email,
                				null, userDetails.getAuthorities());

                        System.out.println(jwtAuthentication);
                		jwtAuthentication.setDetails(userDetails);
                		return jwtAuthentication;
                }
            }
            return null;
        }
        return null;
    }
}
