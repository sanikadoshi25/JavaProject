package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel backgroundLabel, titleLabel, usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, signUpButton;
    static String name ; 

    Login() {
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
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        backgroundLabel.add(loginButton, gbc);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.RED);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 1;
        backgroundLabel.add(signUpButton, gbc);

        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);

        add(backgroundLabel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == loginButton) {
            try {
                ConnectionClass obj = new ConnectionClass();
                name = usernameField.getText();
                String pass = new String(passwordField.getPassword());

                String q = "SELECT * FROM signup WHERE username='" + name + "' AND password='" + pass + "'";
                ResultSet rs = obj.stm.executeQuery(q);

                if (rs.next()) {
                    new HomePage().setVisible(true);  
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "You have entered wrong Username or Password!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ee.getSource() == signUpButton) {
            this.dispose();
            new SignUp(); 
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
