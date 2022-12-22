package ua.levelup.lesson50;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = {"/hello-servlet"})
public class HelloFilter implements Filter {

    int counter;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        counter = 1;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Logging for /hello-servlet: " + counter++);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        counter = 0;
    }
}
