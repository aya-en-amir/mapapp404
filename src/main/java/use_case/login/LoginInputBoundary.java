package use_case.login;

/*
LoginInputBoundary --> defines how the outer layers can interact with the login use-case
 */
public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);
}
