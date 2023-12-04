package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.portfolio.PortfolioController;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final ViewManagerModel viewManagerModel;
    private  final SignupViewModel signupViewModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton cancel;
    private final LoginController loginController;

    private final PortfolioController portfolioController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller,
                     ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, PortfolioController portfolioController) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.portfolioController = portfolioController;
        this.loginViewModel.addPropertyChangeListener(this);
        this.setBackground(new Color(199, 0, 57));


        JLabel title = new JLabel("The Wolf of Bay Street");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBackground(new Color(199, 0, 57));
        title.setFont(new Font("Times New Roman", Font.BOLD, 100));

        JLabel username = new JLabel("Username");
        LabelTextPanel usernameInfo = new LabelTextPanel(
                username, usernameInputField);
        username.setFont(new Font("Helvetica", Font.ITALIC, 50));
        usernameInfo.setBackground(new Color(199, 0, 57));
        usernameInputField.setFont(new Font("Helvetica", Font.ITALIC, 50));

        JLabel password = new JLabel("Password");
        LabelTextPanel passwordInfo = new LabelTextPanel(
                password, passwordInputField);
        password.setFont(new Font("Helvetica", Font.ITALIC, 50));
        passwordInfo.setBackground(new Color(199, 0, 57));
        passwordInputField.setFont(new Font("Helvetica", Font.ITALIC, 50));


        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        logIn.setFont(new Font("Helvetica", Font.ITALIC, 50));
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        buttons.setBackground(new Color(218, 247, 166));
        cancel.setFont(new Font("Helvetica", Font.ITALIC, 50));


        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                            portfolioController.execute(currentState.getUsername());

                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)){
                            switch_to_signup();
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

    private void switch_to_signup(){
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}