package ua.levelup.lesson50;

import com.password4j.Hash;
import com.password4j.Password;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Locale;

import static ua.levelup.ThymeleafConfiguration.TEMPLATE_ENGINE_ATTR;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine templateEngine = (TemplateEngine)getServletContext().getAttribute(TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(request, response);

        Locale locale = webExchange.getLocale();
        WebContext webContext = new WebContext(webExchange, locale);

        String originUrl = request.getParameter("goto");
        webContext.setVariable("goto", originUrl);
        webContext.setVariable("path", "/login");
        webContext.setVariable("loginMethod", "POST");

        templateEngine.process("login", webContext, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String originUrl = request.getParameter("goto");

        System.out.println(username);
        System.out.println(password);
        String salt = "$2b$10$5/Ee7mM5b/NpPeTQGFyI1e";
        Hash hash = Password.hash(password).addSalt(salt).withBcrypt();
        boolean passwordEquals = Password.check(password, hash.getResult()).addSalt(salt).withBcrypt();
        request.login(username, hash.getResult());
//        request.getSession().setAttribute("user", new User(username));
        response.sendRedirect(originUrl);
    }
}
