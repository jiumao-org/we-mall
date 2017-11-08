package org.jiumao.mall.OAuth2;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;


@Component
public class TokenLoginFilter extends GenericFilterBean implements ApplicationEventPublisherAware {

    @Autowired
    @Qualifier("userAuthenticationManager")
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    AuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();

    private ApplicationEventPublisher eventPublisher;


    @SuppressWarnings("unchecked")
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String grantType = request.getParameter("grant_type");
        if (!"password".equals(grantType)) {
            chain.doFilter(request, response);
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (null == username)
            username = "";
        if (null == password)
            password = "";
        chain.doFilter(request, response);
        UsernamePasswordAuthenticationToken loginToken =
                new UsernamePasswordAuthenticationToken(username.trim(), password.trim(), null);

        loginToken.setDetails(authenticationDetailsSource.buildDetails(request));
        Authentication auth = authenticationManager.authenticate(loginToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        onSuccessfulAuthentication((HttpServletRequest) request, (HttpServletResponse) response, auth);

        if (eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(auth, this.getClass()));
        }
        chain.doFilter(request, response);
    }


    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            Authentication authResult) {
    }


    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) {
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
}