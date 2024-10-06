package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Check_Bill extends JFrame implements ActionListener {
    Font f, f1;
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JTextField tf1, tf2, tf3, tf4;
    JButton bt1, bt2, bt3, bt4;
    JPanel p1, p2, p3;
    Choice ch1;
    public int Price = 0;

    Check_Bill() {
        super("Check Total Cab Bill");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Check Total Cab Bill");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l2 = new JLabel("Username");
        l3 = new JLabel("Name");
        l4 = new JLabel("Email");
        l5 = new JLabel("Address");
        l6 = new JLabel("Price");
        l7 = new JLabel();

        ch1 = new Choice();

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();

        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);
        tf4.setEditable(false);

        Dimension textFieldSize = new Dimension(400, 30);  
        tf1.setPreferredSize(textFieldSize);
        tf2.setPreferredSize(textFieldSize);
        tf3.setPreferredSize(textFieldSize);
        tf4.setPreferredSize(textFieldSize);
        
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "SELECT * FROM customer where username='" + Login.name + "'";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch1.add(rest.getString("username"));
                tf1.setText(rest.getString("name"));
                tf2.setText(rest.getString("email"));
                tf3.setText(rest.getString("address"));
            }
            rest.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

     
        bt1 = new JButton("Intercity Cab");
        bt1.addActionListener(this);
        bt1.setBackground(Color.YELLOW);
        bt1.setForeground(Color.BLACK);
        bt1.setPreferredSize(new Dimension(300, 50));  

        bt2 = new JButton("Intracity Cab");
        bt2.addActionListener(this);
        bt2.setBackground(Color.YELLOW);
        bt2.setForeground(Color.BLACK);
        bt2.setPreferredSize(new Dimension(300, 50));  

        bt3 = new JButton("Back");
        bt3.addActionListener(this);
        bt3.setBackground(Color.BLACK);
        bt3.setForeground(Color.WHITE);
        bt3.setPreferredSize(new Dimension(300, 50));  
        
        bt4 = new JButton("Payment");
        bt4.addActionListener(this);
        bt4.setBackground(Color.BLACK);
        bt4.setForeground(Color.WHITE);
        bt4.setPreferredSize(new Dimension(300, 50));  

        f = new Font("Arial", Font.BOLD, 35);
        f1 = new Font("Arial", Font.BOLD, 25);

        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        ch1.setFont(f1);

        bt1.setFont(f1);
        bt2.setFont(f1);
        bt3.setFont(f1);
        bt4.setFont(f1);    
        
        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        p2.setPreferredSize(new Dimension(900, 600));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 30, 30, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        p2.add(l2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        p2.add(ch1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        p2.add(l3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        p2.add(tf1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        p2.add(l4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        p2.add(tf2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        p2.add(l5, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        p2.add(tf3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        p2.add(l6, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        p2.add(tf4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        p2.add(bt1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        p2.add(bt4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        p2.add(bt2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        //gbc.anchor = GridBagConstraints.CENTER;
        p2.add(bt3, gbc);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1, 10, 10));
        p3.add(l7);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("9.jpg"));

        Image img = ic.getImage().getScaledInstance((int) (screenSize.width * 0.6), (int) (screenSize.height * 0.9), Image.SCALE_SMOOTH);
        ImageIcon ic1 = new ImageIcon(img);
        l7.setIcon(ic1);

        setLayout(new BorderLayout(10, 10));
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.WEST);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            Price = 0;
            String username = ch1.getSelectedItem();

            try {
                ConnectionClass obj2 = new ConnectionClass();
                String q2 = "SELECT price FROM intercity_cab WHERE username=?";
                PreparedStatement pst2 = obj2.con.prepareStatement(q2);
                pst2.setString(1, username);
                ResultSet rest1 = pst2.executeQuery();
                while (rest1.next()) {
                    Price += Integer.parseInt(rest1.getString("price"));
                }
                tf4.setText(String.valueOf(Price));
                rest1.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == bt2) {
            Price = 0;
            String username = ch1.getSelectedItem();

            try {
                ConnectionClass obj3 = new ConnectionClass();
                String q3 = "SELECT price FROM intracity_cab WHERE username=?";
                PreparedStatement pst2 = obj3.con.prepareStatement(q3);
                pst2.setString(1, username);
                ResultSet rest1 = pst2.executeQuery();
                while (rest1.next()) {
                    Price += Integer.parseInt(rest1.getString("price"));
                }
                tf4.setText(String.valueOf(Price));
                rest1.close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        if (e.getSource() == bt3) {
            this.setVisible(false);
        }
        if(e.getSource()==bt4) {
        	try {
        		if(bt1.isSelected()) {
        		JOptionPane.showMessageDialog(null, "Payement done successfully");
        	 ConnectionClass obj = new ConnectionClass();
        	 String q="select drivername from intercity_cab where username='"+ch1.getSelectedItem()+"'";
        	 ResultSet rs=obj.stm.executeQuery(q);
        	 if(rs.next()) {
        		 String name;
        	   	 String query="update intracity_driver set status = 'free' where driver='"+rs.getString(1)+"'and status='booked' ";
                 obj.stm.executeUpdate(query);
//        	 String query="update intercity_driver set status = 'free' where driver='"+rs.getString(1)+"' and status='booked' ";
//             obj.stm.executeUpdate(query);
        	 }else {
        		 JOptionPane.showMessageDialog(null, "Something went wrong");
        	 }
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Payement done successfully");
               	 ConnectionClass obj = new ConnectionClass();
               	 String q="select drivername from intracity_cab where username='"+ch1.getSelectedItem()+"'";
               	 ResultSet rs=obj.stm.executeQuery(q);
               	 if(rs.next()) {
               		 String name;
               	 String query="update intracity_driver set status = 'free' where driver='"+rs.getString(1)+"'and status='booked' ";
                    obj.stm.executeUpdate(query);
               	 }else {
               		 JOptionPane.showMessageDialog(null, "Something went wrong");
               	 }
        		}
        	 
        	}catch(Exception E) {
        		E.printStackTrace();
        	}
        }
    }

    public static void main(String[] args) {
        new Check_Bill().setVisible(true);
    }
}

