package ua.levelup.lesson50;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;

@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.html",
                errorPage = "/login-error.html"))
@ApplicationScoped
public class ApplicationConfig {

    public ApplicationConfig() {
        System.out.println();
    }
}
