package org.regitiny.minhshop.security.jwt;

import java.util.ArrayList;
import java.util.Collection;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    @Autowired
    private TokenProvider tokenProvider;

    public String jwtAdminFullRole() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.USER));
        authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN));
        authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.MANAGEMENT));
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            "admin",
            "admin",
            authorities
        );

        securityContext.setAuthentication(usernamePasswordAuthenticationToken);
        SecurityContextHolder.setContext(securityContext);
        return tokenProvider.createToken(usernamePasswordAuthenticationToken, true);
    }
}
