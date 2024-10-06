package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Delete_Customer extends JFrame implements ActionListener {
    Font f, f1;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
    JButton bt1, bt2;
    JPanel titlePanel, imagePanel, formPanel; 
    Choice ch;

    Delete_Customer() {
        super("Delete Customer");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new Font("Arial", Font.BOLD, 35);
        f1 = new Font("Arial", Font.BOLD, 22);

        ch = new Choice();
 

        l1 = new JLabel("Delete Customer");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l2 = new JLabel("Username");
        l3 = new JLabel("Name");
        l4 = new JLabel("Age");
        l5 = new JLabel("Date of Birth");
        l6 = new JLabel("Address");
        l7 = new JLabel("Phone");
        l8 = new JLabel("Email");
        l9 = new JLabel("Country");
        l10 = new JLabel("Gender");
        l11 = new JLabel("Aadhar");

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();
        tf9 = new JTextField();

        Dimension textFieldSize = new Dimension(300, 30);
        tf1.setPreferredSize(textFieldSize);
        tf2.setPreferredSize(textFieldSize);
        tf3.setPreferredSize(textFieldSize);
        tf4.setPreferredSize(textFieldSize);
        tf5.setPreferredSize(textFieldSize);
        tf6.setPreferredSize(textFieldSize);
        tf7.setPreferredSize(textFieldSize);
        tf8.setPreferredSize(textFieldSize);
        tf9.setPreferredSize(textFieldSize);

        bt1 = new JButton("Delete Customer");
        bt1.addActionListener(this);
        bt2 = new JButton("Back");
        bt2.addActionListener(this);

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1);
        l8.setFont(f1);
        l9.setFont(f1);
        l10.setFont(f1);
        l11.setFont(f1);
        l12 = new JLabel();

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);
        tf8.setFont(f1);
        tf9.setFont(f1);
        bt1.setFont(f1);
        bt2.setFont(f1);
        ch.setFont(f1);
        
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "select * from customer where username='"+Login.name+"'";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch.add(rest.getString("username"));
                tf1.setText(rest.getString("name"));
                tf2.setText(rest.getString("age"));
                tf3.setText(rest.getString("dob"));
                tf4.setText(rest.getString("address"));
                tf5.setText(rest.getString("phone"));
                tf6.setText(rest.getString("email"));
                tf7.setText(rest.getString("country"));
                tf8.setText(rest.getString("gender"));
                tf9.setText(rest.getString("aadhar"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        bt1.setBackground(Color.BLACK);
        bt2.setBackground(Color.RED);
        bt1.setForeground(Color.WHITE);
        bt2.setForeground(Color.WHITE);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("12.png"));
        Image img1 = img.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.6), 
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.9),
                Image.SCALE_SMOOTH);
        ImageIcon ic1 = new ImageIcon(img1);
        l12.setIcon(ic1);
        l12.setHorizontalAlignment(JLabel.CENTER);

        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1, 1, 10, 10));
        titlePanel.add(l1);

        imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(l12, BorderLayout.CENTER);

        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10));
        formPanel.add(l2);
        formPanel.add(ch);
        formPanel.add(l3);
        formPanel.add(tf1);
        formPanel.add(l4);
        formPanel.add(tf2);
        formPanel.add(l5);
        formPanel.add(tf3);
        formPanel.add(l6);
        formPanel.add(tf4);
        formPanel.add(l7);
        formPanel.add(tf5);
        formPanel.add(l8);
        formPanel.add(tf6);
        formPanel.add(l9);
        formPanel.add(tf7);
        formPanel.add(l10);
        formPanel.add(tf8);
        formPanel.add(l11);
        formPanel.add(tf9);
        formPanel.add(bt1);
        formPanel.add(bt2);

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());

        mainContainer.add(imagePanel, BorderLayout.WEST);
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BorderLayout());
        formContainer.add(formPanel, BorderLayout.CENTER);
        formContainer.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        mainContainer.add(formContainer, BorderLayout.CENTER);
        mainContainer.add(titlePanel, BorderLayout.NORTH);

        add(mainContainer);

//        ch.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent arg0) {
//                try {
//                    ConnectionClass obj2 = new ConnectionClass();
//                    String username = ch.getSelectedItem();
//                    String q1 = "SELECT * FROM customer WHERE username='" + username + "'";
//                    System.out.println("Executing Query: " + q1); // Debug print
//                    ResultSet rest1 = obj2.stm.executeQuery(q1);
//
//                    if (rest1.next()) {
//                        tf1.setText(rest1.getString("name"));
//                        tf2.setText(rest1.getString("age"));
//                        tf3.setText(rest1.getString("dob"));
//                        tf4.setText(rest1.getString("address"));
//                        tf5.setText(rest1.getString("phone"));
//                        tf6.setText(rest1.getString("email"));
//                        tf7.setText(rest1.getString("country"));
//                        tf8.setText(rest1.getString("gender"));
//                        tf9.setText(rest1.getString("aadhar"));
//                    } else {
//                        JOptionPane.showMessageDialog(null, "No record found for the selected username.");
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String username = ch.getSelectedItem();

            try {
                ConnectionClass obj3 = new ConnectionClass();
                String q = "DELETE FROM customer WHERE username='" + username + "'";
                String q1 = "DELETE FROM intercitycab WHERE username='" + username + "'";
                String q2 = "DELETE FROM intracitycab WHERE username='" + username + "'";
                int aa = obj3.stm.executeUpdate(q);

                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Your Record Successfully Deleted");
                    obj3.stm.executeUpdate(q1);
                    obj3.stm.executeUpdate(q2);
                    this.setVisible(false);
                    new View_Customer().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please!, Check all details carefully");
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        if (e.getSource() == bt2) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Delete_Customer().setVisible(true);
    }
}
