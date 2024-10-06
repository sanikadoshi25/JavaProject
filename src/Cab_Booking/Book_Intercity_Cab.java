package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Random;

public class Book_Intercity_Cab extends JFrame implements ActionListener {
    Font f, f1;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8,tf9;
    JButton bt1, bt2;
    JPanel p1, p2, p3;
    Choice ch1, ch2, ch3, ch4;

    private static final int IMAGE_WIDTH = 1000;
    private static final int IMAGE_HEIGHT = 700;

    Book_Intercity_Cab() {
        super("Book Intercity Cab");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new Font("Arial", Font.BOLD, 35);
        f1 = new Font("Arial", Font.BOLD, 22);

        ch1 = new Choice();
        ch2 = new Choice();
        ch3 = new Choice();
        ch4 = new Choice();

        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "select  source from intercity_driver where status='free'";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch1.add(rest.getString("source"));
            }
            rest.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "select username from customer where username='"+Login.name+"'";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch3.add(rest.getString("username"));
            }
            rest.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        l1 = new JLabel("Book Intercity Cab");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l2 = new JLabel("Book ID");
        l3 = new JLabel("Source");
        l4 = new JLabel("Destination");
        l5 = new JLabel("Username");
        l6 = new JLabel("Name");
        l7 = new JLabel("Driver Name");
        l8 = new JLabel("Car");
        l9 = new JLabel("Destination from");
        l10 = new JLabel("Destination to");
        l11 = new JLabel("Price");
        l13 = new JLabel("Type");

        l12 = new JLabel(); 

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();
        tf9 = new JTextField();

        tf1.setEditable(false);
        tf3.setEditable(false);
        tf4.setEditable(false);
        tf5.setEditable(false);
        tf6.setEditable(false);
        tf7.setEditable(false);
        tf8.setEditable(false);
        tf9.setEditable(false);
        
        bt1 = new JButton("BookCab");
        bt1.addActionListener(this);
        bt1.setBackground(Color.YELLOW);
        bt1.setForeground(Color.BLACK);

        bt2 = new JButton("Back"); 
        bt2.addActionListener(this);
        bt2.setBackground(Color.RED);
        bt2.setForeground(Color.WHITE);

        Random rm = new Random();
        tf1.setText("" + Math.abs(rm.nextInt() % 100000));
        tf1.setForeground(Color.RED);

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
        ch1.setFont(f1);
        ch2.setFont(f1);
        ch3.setFont(f1);
        l13.setFont(f1);

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

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(12, 2, 10, 10));
        p2.add(l2);
        p2.add(tf1);
        p2.add(l3);
        p2.add(ch1);
        p2.add(l4);
        p2.add(ch2);
        p2.add(l5);
        p2.add(ch3);
        p2.add(l6);
        p2.add(tf3);
        p2.add(l7);
        p2.add(tf4);
        p2.add(l8);
        p2.add(tf5);
        p2.add(l9);
        p2.add(tf6);
        p2.add(l10);
        p2.add(tf7);
        p2.add(l11);
        p2.add(tf8);
        p2.add(l13);
        p2.add(tf9);
        p2.add(bt1);
        p2.add(bt2);

        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("8.jpg"));
        Image img = ic.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);
        ImageIcon ic1 = new ImageIcon(img);
        l12.setIcon(ic1);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1, 10, 10));
        p3.add(l12);

        setLayout(new BorderLayout(10, 10));
        add(p1, "North");
        add(p2, "Center");
        add(p3, "West");

        ch3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                try {
                    ConnectionClass obj = new ConnectionClass();
                    String username = ch3.getSelectedItem();
                    String q1 = "select name from customer where username='" + username + "'";
                    ResultSet rest1 = obj.stm.executeQuery(q1);
                    while (rest1.next()) {
                        tf3.setText(rest1.getString("name"));
                    }
                    rest1.close();
                } catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        });

        ch1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                ch2.removeAll();
                try {
                    ConnectionClass obj = new ConnectionClass();
                    String source = ch1.getSelectedItem();
                    String q1 = "select destination from intercity_driver where source='" + source + "' and status='free'";
                    ResultSet rest1 = obj.stm.executeQuery(q1);
                    while (rest1.next()) {
                        ch2.add(rest1.getString("destination"));
                    }
                    rest1.close();
                    String destination = ch2.getSelectedItem();
                    String q2 = "SELECT driver, car, price ,type FROM intercity_driver WHERE source='" + source + "' AND destination='" + destination + "'";
                   // String q2 = "select * from intercity_driver where source='" + source + "' and destination='" + destination + "'";
                    ResultSet rest2 = obj.stm.executeQuery(q2);
                    if(rest2.next()) {
                        tf4.setText(rest2.getString("driver"));
                        tf5.setText(rest2.getString("car"));
                        tf6.setText(ch1.getSelectedItem());
                        tf7.setText(ch2.getSelectedItem());
                        tf8.setText(rest2.getString("price"));
                        ch4.add(rest2.getString("type"));
                        tf9.setText(rest2.getString("type"));
                    }
                } catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        });

//        ch2.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent arg0) {
//                ch4.removeAll();
//                try {
//                    ConnectionClass obj = new ConnectionClass();
//                    String source = ch1.getSelectedItem();
////                    String destination = ch2.getSelectedItem();
////                    String q1 = "select * from intercity_driver where source='" + source + "' and destination='" + destination + "'";
////                    ResultSet rest1 = obj.stm.executeQuery(q1);
////                    while (rest1.next()) {
////                        tf4.setText(rest1.getString("driver"));
////                        tf5.setText(rest1.getString("car"));
////                        tf6.setText(rest1.getString("source"));
////                        tf7.setText(rest1.getString("destination"));
////                        tf8.setText(rest1.getString("price"));
////                        ch4.add(rest1.getString("type"));
////                    }
//                } catch (Exception exx) {
//                    exx.printStackTrace();
//                }
//            }
//        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String bid = tf1.getText();
            String username = ch3.getSelectedItem();
            String dname = tf4.getText();
            String car = tf5.getText();
            String source = tf6.getText();
            String destination = tf7.getText();
            String price = tf8.getText();
            String type = ch4.getSelectedItem();
            String name = tf3.getText();

            try {
                ConnectionClass obj3 = new ConnectionClass();
                String q1 = "INSERT INTO intercity_cab (book_id, username, drivername, car, source, destination, price, type) " +
                             "VALUES ('" + bid + "', '" + username + "', '" + dname + "','"+car+"','" + source + "', '" + destination + "', '" + price + "', '" + type + "')";
                int aa = obj3.stm.executeUpdate(q1);
               String query="update intercity_driver set status = 'booked' where source ='"+ch1.getSelectedItem()+"' AND destination = '"+ch2.getSelectedItem()+"'";
              obj3.stm.executeUpdate(query);
               
                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Cab Booked Successfully");
                    this.setVisible(false);
                    new Update_Customer1();
                } else {
                    JOptionPane.showMessageDialog(null, "Please!, Fill all details carefully");
                }
            } catch (Exception exx) {
                exx.printStackTrace();
            }
        }
        if (e.getSource() == bt2) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Book_Intercity_Cab().setVisible(true);
    }
}
