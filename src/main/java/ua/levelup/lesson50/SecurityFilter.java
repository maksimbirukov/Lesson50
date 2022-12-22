package ua.levelup.lesson50;

import jakarta.servlet.*;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Principal;

@WebFilter(urlPatterns = {"/hello-servlet"})
public class SecurityFilter extends HttpFilter {


    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

//        User user = (User) request.getSession().getAttribute("user");
        Principal userPrincipal = request.getUserPrincipal();
        if(userPrincipal == null) {
            response.sendRedirect("/login?goto=" + request.getServletPath() + "&" + "val=2");
            return;
        }

//        if(!request.isUserInRole("admin")) {
//            response.sendError(403);
//        }

        chain.doFilter(request, response);
    }

}
