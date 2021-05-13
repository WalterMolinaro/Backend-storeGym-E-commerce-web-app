package com.ecommerce.storegym.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
E' una classe che fa il parserd del jwt legge le informazioni che ci servono e le possiamo poi utilizzare nella nostra
web applications */
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    //con @Value prende la stringa client name e gli assegna il valore keyclock.resource
    @Value("${keycloak.resource}")
    private String CLIENT_NAME;


    @Override
    @SuppressWarnings("unchecked")
    /** è il metodo convert del converter */
    public AbstractAuthenticationToken convert(final Jwt source) {
        Map<String, Object> resourceAccess = source.getClaim("resource_access");
        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(CLIENT_NAME);
        Collection<String> resourceRoles = (Collection<String>) resource.get("roles");
        Set<GrantedAuthority> authorities = resourceRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return new JwtAuthenticationToken(source, authorities);
    }


}