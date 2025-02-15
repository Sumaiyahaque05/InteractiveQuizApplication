package com.quiz_app.config.ipvalidation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomIPFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {
//        System.out.println("custom docker ip :" + request.getRemoteAddr());
        if (matches(request, "127.0.0.1")
                || matches(request, "0:0:0:0:0:0:0:1")
                || matches(request, "172.20.0.1/8")
        ) {
            filterChain.doFilter(request, response);
        }
    }

    private boolean matches(HttpServletRequest request, String subnet) {
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(subnet);
        return ipAddressMatcher.matches(request);
    }
}
