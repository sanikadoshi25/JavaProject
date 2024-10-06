package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminLogin extends JFrame implements ActionListener {
    JLabel backgroundLabel, titleLabel, usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    private static final String FIXED_USERNAME = "admin";
    private static final String FIXED_PASSWORD = "admin123";

    AdminLogin() { 
        setTitle("Login Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new GridBagLayout());

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("2.jpg"));
        Image backgroundImage = img.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_SMOOTH);
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 20, 30, 20);

        titleLabel = new JLabel("Login Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 45));
        titleLabel.setForeground(Color.PINK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 2;
        backgroundLabel.add(titleLabel, gbc);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        usernameLabel.setForeground(Color.ORANGE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        backgroundLabel.add(usernameLabel, gbc);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 22));
        gbc.gridx = 1;
        backgroundLabel.add(usernameField, gbc);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 25));
        passwordLabel.setForeground(Color.ORANGE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        backgroundLabel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 22));
        gbc.gridx = 1;
        backgroundLabel.add(passwordField, gbc);

        loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        backgroundLabel.add(loginButton, gbc);

        loginButton.addActionListener(this);

        add(backgroundLabel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == loginButton) {
            String name = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (name.equals(FIXED_USERNAME) && pass.equals(FIXED_PASSWORD)) {
                new AdminSection().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "You have entered wrong Username or Password!");
            }
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}
