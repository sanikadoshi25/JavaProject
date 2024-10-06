package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignUp extends JFrame implements ActionListener {

    JLabel titleLabel, l1, l2, l3, l4, l5;
    JTextField t1, t2, t3;
    JPasswordField p1;
    JButton b1, b2, b3;

    SignUp() {
        super("Create New Account");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        getContentPane().setBackground(new Color(130, 240, 250));

        Font labelFont = new Font("Arial", Font.BOLD, 28);
        Font buttonFont = new Font("Arial", Font.PLAIN, 22);
        Font titleFont = new Font("Arial", Font.BOLD, 35);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        titleLabel = new JLabel("Create New Account", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 10, 10, 10);
        add(new JLabel(), gbc);

        l1 = new JLabel("Username:");
        l1.setFont(labelFont);
        gbc.gridy = 2; 
        gbc.gridwidth = 1;
        add(l1, gbc);

        t1 = new JTextField(20);
        gbc.gridx = 1;
        add(t1, gbc);

        l2 = new JLabel("Name:");
        l2.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(l2, gbc);

        t2 = new JTextField(20);
        gbc.gridx = 1;
        add(t2, gbc);
        
        l3 = new JLabel("Password:");
        l3.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(l3, gbc);

        p1 = new JPasswordField(20);
        gbc.gridx = 1;
        add(p1, gbc);

        l4 = new JLabel("Phone No:");
        l4.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(l4, gbc);

        t3 = new JTextField(20);
        gbc.gridx = 1;
        add(t3, gbc);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("1.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        l5 = new JLabel(i3);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(l5, gbc);

        b1 = new JButton("SignUp");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(buttonFont);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(b1, gbc);
        b1.addActionListener(this);

        b2 = new JButton("User Login");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(buttonFont);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(b2, gbc);
        b2.addActionListener(this);

        b3 = new JButton("Admin Login");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setFont(buttonFont);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(b3, gbc);
        b3.addActionListener(this);

        setVisible(true); 
    }

    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == b1) {
            String userName = t1.getText();
            String name = t2.getText();
            String password = new String(p1.getPassword());
            String phone = t3.getText();

            if (!isValidPhoneNumber(phone)) {
                JOptionPane.showMessageDialog(null, "Invalid Phone Number. It should be 10 digits.");
                return;
            }

            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Invalid Password. It must be at least 8 characters long, contain at least one digit and one uppercase letter.");
                return;
            }

            try {
                ConnectionClass obj = new ConnectionClass();
                String q = "insert into SignUp values('" + userName + "', '" + name + "', '" + password + "', '" + phone + "')";
                int aa = obj.stm.executeUpdate(q);
                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Account Created Successfully");
                    this.setVisible(false);
                    new Login();
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill all details carefully");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ee.getSource() == b2) {
            this.setVisible(false);
            new Login();
        } else if (ee.getSource() == b3) {
            this.setVisible(false);
            new AdminLogin();
        } 
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*");
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
