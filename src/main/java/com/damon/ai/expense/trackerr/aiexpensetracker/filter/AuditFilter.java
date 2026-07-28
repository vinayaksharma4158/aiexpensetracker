package com.damon.ai.expense.trackerr.aiexpensetracker.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.damon.ai.expense.trackerr.aiexpensetracker.service.AuditService;

@Component
public class AuditFilter implements Filter {

    private final AuditService auditService;

    public AuditFilter(AuditService auditService) {
        this.auditService = auditService;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        chain.doFilter(request, response);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Authentication = " + authentication);

        String email = "ANONYMOUS";

        if (authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {

            email = authentication.getName();
        }

        auditService.saveAudit(
                email,
                "API_ACCESS",
                req.getRequestURI(),
                req.getMethod(),
                res.getStatus());
    }
}