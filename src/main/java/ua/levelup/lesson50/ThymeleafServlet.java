package ua.levelup.lesson50;

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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static ua.levelup.ThymeleafConfiguration.TEMPLATE_ENGINE_ATTR;

@WebServlet(name = "thymeleafServlet", value = "/thymeleaf")
public class ThymeleafServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(request, response);

        Map<Long, String> map = new HashMap<>();

        for (long l = Long.MAX_VALUE; l > Long.MAX_VALUE/100; l--) {
            map.put(l, "" + l);
        }

        map.put(-2147450879L, "-2147450879");
        map.put(-2147450879L, "-2147450879");

        Locale locale = webExchange.getLocale();
        WebContext webContext = new WebContext(webExchange, locale);


        webContext.setVariable("user", new User("Friedrich Nietzsche"));
        webContext.setVariable("needToBeAdmin", true);
        webContext.setVariable("fieldName", "name");

        templateEngine.process("home", webContext, response.getWriter());
    }
}
