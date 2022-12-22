package ua.levelup;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

@WebListener
public class ThymeleafConfiguration implements ServletContextListener {

    public static final String TEMPLATE_ENGINE_ATTR = "ua.levelup.Thymeleaf";
    private JakartaServletWebApplication webApplication;
    private ITemplateEngine templateEngine;



    @Override
    public void contextInitialized(ServletContextEvent sce) {
        webApplication = JakartaServletWebApplication.buildApplication(sce.getServletContext());
        templateEngine = templateEngine(webApplication);
        sce.getServletContext().setAttribute(TEMPLATE_ENGINE_ATTR, templateEngine);
    }

    private ITemplateEngine templateEngine(IWebApplication webApplication) {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver(webApplication));
        return templateEngine;
    }

    private ITemplateResolver templateResolver(IWebApplication webApplication) {
        WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(webApplication);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(true);
        return templateResolver;
    }
}
