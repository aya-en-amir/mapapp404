package interfaceadapter.login;

import interfaceadapter.ViewModel;

public class LoginViewModel extends ViewModel<LoginState> {

    public LoginViewModel() {
        super("Log In");
        setState(new LoginState());
    }

}

