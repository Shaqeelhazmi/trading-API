package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;

    private final ViewManagerModel viewManagerModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);

    private final JTextField starting_balance = new JTextField(15);
    private final SignupController signupController;

    private final JButton signUp;
    private final JButton quit;

    private final JButton login;


    public SignupView(SignupController controller, SignupViewModel signupViewModel, ViewManagerModel viewManagerModel) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.setBackground(new Color(199, 0, 57));
        signupViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Times New Roman", Font.BOLD, 100));

        JLabel username = new JLabel(SignupViewModel.USERNAME_LABEL);
        username.setFont(new Font("Cambria", Font.ITALIC, 50));
        LabelTextPanel usernameInfo = new LabelTextPanel(
                username, usernameInputField);
        usernameInfo.setBackground(new Color(199, 0, 57));
        usernameInputField.setFont(new Font("Helvetica", Font.ITALIC, 50));


        JLabel password = new JLabel(SignupViewModel.PASSWORD_LABEL);
        password.setFont(new Font("Cambria", Font.ITALIC, 50));
        LabelTextPanel passwordInfo = new LabelTextPanel(
                password, passwordInputField);
        passwordInfo.setBackground(new Color(199, 0, 57));
        passwordInputField.setFont(new Font("Helvetica", Font.ITALIC, 50));

        JLabel repeatpassword = new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL);
        repeatpassword.setFont(new Font("Cambria", Font.ITALIC, 50));
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                repeatpassword, repeatPasswordInputField);
        repeatPasswordInfo.setBackground(new Color(199, 0, 57));
        repeatPasswordInputField.setFont(new Font("Helvetica", Font.ITALIC, 50));

        JLabel balance = new JLabel(SignupViewModel.STARTING_AMOUNT);
        balance.setFont(new Font("Cambria", Font.ITALIC, 50));
        LabelTextPanel startingBalanceInfo = new LabelTextPanel(
                balance, starting_balance);
        startingBalanceInfo.setBackground(new Color(199, 0, 57));
        starting_balance.setFont(new Font("Helvetica", Font.ITALIC, 50));


        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(218, 247, 166  ));
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        signUp.setFont(new Font("Helvetica", Font.ITALIC, 30));
        quit = new JButton(SignupViewModel.QUIT_BUTTON_LABEL);
        buttons.add(quit);
        quit.setFont(new Font("Helvetica", Font.ITALIC, 30));
        login = new JButton(SignupViewModel.LOGIN_LABEL);
        buttons.add(login);
        login.setFont(new Font("Helvetica", Font.ITALIC, 30));


        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            SignupState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword(),
                                    currentState.getBalance()
                            );
                        }
                    }
                }
        );

        quit.addActionListener(this);

        login.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(login)) {
                            switch_to_login();
                        }
                    }
                }
        );

        quit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(quit)){
                            System.exit(0);
                        }
                    }
                }
        );

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState); // Hmm, is this necessary?
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        starting_balance.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c)){
                            e.consume();
                        } else {
                            double balance = Integer.parseInt(starting_balance.getText() + e.getKeyChar());
                            currentState.setBalance(balance);
                            signupViewModel.setState(currentState);
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(startingBalanceInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {System.out.println("Click" + evt.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource().equals(signupViewModel)){
            SignupState state = (SignupState) evt.getNewValue();
            if (state.getUsernameError() != null) {
                JOptionPane.showMessageDialog(this, state.getUsernameError()); }
            }
        }

    private void switch_to_login() {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
