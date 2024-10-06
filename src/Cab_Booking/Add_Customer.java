package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Add_Customer extends JFrame implements ActionListener {
    JFrame f;
    JLabel backgroundLabel, titleLabel;
    JTextField usernameField, nameField, ageField, dobField, addressField, phoneField, emailField, countryField, aadharField;
    JButton submitButton, cancelButton;
    JComboBox<String> genderComboBox;

    public Add_Customer() {
        f = new JFrame("Add Customer Details");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setLayout(null);

        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("4.jpg"));
        Image img = icon.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(img));

        titleLabel = new JLabel("New Customer Details");
        titleLabel.setBounds(800, 50, 500, 60);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.black);
        backgroundLabel.add(titleLabel);

        String[] labels = {"Username", "Name", "Age", "Date Of Birth", "Address", "Phone No", "Email Id", "Country", "Gender", "Aadhar No"};
        JTextField[] textFields = new JTextField[8];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(800, 150 + (i * 50), 150, 30);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            backgroundLabel.add(label);

            if (i < 8) {
                textFields[i] = new JTextField();
                textFields[i].setBounds(1000, 150 + (i * 50), 250, 30);
                textFields[i].setFont(new Font("Arial", Font.PLAIN, 18));
                backgroundLabel.add(textFields[i]);
            }
        }

        usernameField = textFields[0];
        nameField = textFields[1];
        ageField = textFields[2];
        dobField = textFields[3];
        addressField = textFields[4];
        phoneField = textFields[5];
        emailField = textFields[6];
        countryField = textFields[7];

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(800, 550, 150, 30);
        genderLabel.setFont(new Font("Arial", Font.BOLD, 20));
        backgroundLabel.add(genderLabel);

        String[] genderOptions = {"Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genderOptions);
        genderComboBox.setBounds(1000, 550, 250, 30);
        genderComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        backgroundLabel.add(genderComboBox);

        JLabel aadharLabel = new JLabel("Aadhar No");
        aadharLabel.setBounds(800, 600, 150, 30);
        aadharLabel.setFont(new Font("Arial", Font.BOLD, 20));
        backgroundLabel.add(aadharLabel);

        aadharField = new JTextField();
        aadharField.setBounds(1000, 600, 250, 30);
        aadharField.setFont(new Font("Arial", Font.PLAIN, 18));
        backgroundLabel.add(aadharField);

        submitButton = new JButton("Submit");
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBounds(800, 700, 150, 40);
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        backgroundLabel.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(1000, 700, 150, 40);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 20));
        backgroundLabel.add(cancelButton);

        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);

        f.add(backgroundLabel);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String username = usernameField.getText();
            String name = nameField.getText();
            String age = ageField.getText();
            String dob = dobField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String country = countryField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            String aadhar = aadharField.getText();

            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "Phone number must be exactly 10 digits long.");
                return;
            }

            if (!aadhar.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(null, "Aadhar number must be exactly 12 digits long.");
                return;
            }

            try {
                ConnectionClass obj = new ConnectionClass();
                String q = "INSERT INTO customer (username, name, age, dob, address, phone, email, country, gender, aadhar) "
                        + "VALUES ('" + username + "', '" + name + "', '" + age + "', '" + dob + "', '" + address + "', '" + phone + "', '" + email + "', '" + country + "', '" + gender + "', '" + aadhar + "')";

                obj.stm.executeUpdate(q);

                JOptionPane.showMessageDialog(null, "Details Successfully Inserted");
                f.setVisible(false);
                new HomePage();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }

        if (e.getSource() == cancelButton) {
            f.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Add_Customer();
    }
}
