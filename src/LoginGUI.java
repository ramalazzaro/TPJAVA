import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.Scanner;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginListener listener;
    private String password;

    public LoginGUI() {
        loadPassword();
        createUI();
    }

    private void loadPassword() {
        try {
            File file = new File("password.txt");
            Scanner reader = new Scanner(file);
            password = reader.nextLine();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void savePassword(String newPassword) {
        try {
            FileWriter writer = new FileWriter("password.txt");
            writer.write(newPassword);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void createUI() {
        setTitle("Login");
        setSize(300, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton resetButton = new JButton("Reset Password");
        JCheckBox showPasswordCheckBox = new JCheckBox("Show password");

        c.gridx = 0;
        c.gridy = 0;
        formPanel.add(usernameLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        formPanel.add(usernameField, c);

        c.gridx = 0;
        c.gridy = 1;
        formPanel.add(passwordLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        formPanel.add(passwordField, c);

        c.gridx = 0;
        c.gridy = 2;
        formPanel.add(showPasswordCheckBox, c);

        c.gridx = 1;
        c.gridy = 2;
        formPanel.add(loginButton, c);

        c.gridx = 1;
        c.gridy = 3;
        formPanel.add(resetButton, c);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentUsername = JOptionPane.showInputDialog("Enter current username:");
                if (currentUsername.equals("admin")) {
                    String newPassword = JOptionPane.showInputDialog("Enter new password:");
                    password = newPassword;
                    savePassword(newPassword);
                    JOptionPane.showMessageDialog(null, "Password has been reset!");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username!");
                }
            }
        });

        showPasswordCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });

        getContentPane().add(formPanel, BorderLayout.CENTER);
    }

    private void authenticate() {
        String username = usernameField.getText();
        String inputPassword = new String(passwordField.getPassword());

        if (username.equals("admin") && inputPassword.equals(password)) {

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
