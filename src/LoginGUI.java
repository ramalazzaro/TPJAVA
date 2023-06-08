import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernameField; // TextField for username input
    private JPasswordField passwordField; // PasswordField for password input
    private LoginListener listener;

    public LoginGUI() {
        createUI();
    }

    private void createUI() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(); // Initialize username field
        passwordField = new JPasswordField(); // Initialize password field
        JButton loginButton = new JButton("Login");

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(new JLabel()); // Just a placeholder to position the button correctly
        formPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });

        getContentPane().add(formPanel, BorderLayout.CENTER);
    }

    private void authenticate() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword()); // getPassword() returns char[]

        if (username.equals("admin") && password.equals("password")) {
            // If there is a listener assigned, notify it about the successful login
            if (listener != null) {
                listener.onLoginSuccessful();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    public void setListener(LoginListener listener) {
        this.listener = listener;
    }
}
