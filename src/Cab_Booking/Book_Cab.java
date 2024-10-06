package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Random;

public class Book_Cab extends JFrame implements ActionListener {
    Font f, f1;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
    JButton bt1, bt2;
    JPanel p1, p2;
    Choice ch1, ch2, ch3;
 
    Book_Cab() {
        super("Book Intracity Cab");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new Font("Arial", Font.BOLD, 35);
        f1 = new Font("Arial", Font.BOLD, 22);

        ch1 = new Choice(); // For source
        ch2 = new Choice(); // For destination
        ch3 = new Choice(); // For username

        // Populate source choices
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "SELECT source FROM intracity_driver where status = 'free'"; // Ensure unique sources
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch1.add(rest.getString("source"));
            }
            rest.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Populate username choices
     
 
        // Initialize labels
        l1 = new JLabel("Book Intracity Cab");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l2 = new JLabel("Book ID");
        l3 = new JLabel("Source");
        l4 = new JLabel("Destination");
        l5 = new JLabel("Username");
        l6 = new JLabel("Name");
        l7 = new JLabel("Driver Name");
        l8 = new JLabel("Car");
        l9 = new JLabel("Source from");
        l10 = new JLabel("Destination to");
        l11 = new JLabel("Price");
        l12 = new JLabel();

        // Initialize text fields
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        tf8 = new JTextField();

        // Set text fields as non-editable
        tf1.setEditable(false);
        tf3.setEditable(false);
        tf4.setEditable(false);
        tf5.setEditable(false);
        tf6.setEditable(false);
        tf7.setEditable(false);
        tf8.setEditable(false);

        // Initialize buttons
        bt1 = new JButton("Book Cab");
        bt1.addActionListener(this);
        bt1.setBackground(Color.YELLOW);
        bt1.setForeground(Color.BLACK);

        bt2 = new JButton("Back");
        bt2.addActionListener(this);
        bt2.setBackground(Color.RED);
        bt2.setForeground(Color.WHITE);

        // Generate random booking ID
        Random rm = new Random();
        tf1.setText("" + Math.abs(rm.nextInt() % 100000));
        tf1.setForeground(Color.RED);

        // Set fonts for labels and text fields
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

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);
        tf7.setFont(f1);
        tf8.setFont(f1);

        
        
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "SELECT * FROM customer where username='"+Login.name+"'";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch3.add(rest.getString("username"));
                tf3.setText(rest.getString("name"));
            }
            rest.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bt1.setFont(f1);
        bt2.setFont(f1);

        // Set up panels
        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(11, 2, 8, 8));

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
        p2.add(bt1);
        p2.add(bt2);

        // Set background image
        ImageIcon ic = new ImageIcon(ClassLoader.getSystemResource("7.jpeg"));
        Image img = ic.getImage().getScaledInstance(1000, 800, Image.SCALE_DEFAULT);
        ImageIcon ic1 = new ImageIcon(img);
        l12.setIcon(ic1);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(l12, BorderLayout.CENTER); 

        // Set layout for the frame
        setLayout(new BorderLayout(60, 20));
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.WEST);

        // Listener for username selection
        ch3.addItemListener(new ItemListener() {
            public void  itemStateChanged(ItemEvent arg0) {
                try {
                    ConnectionClass obj = new ConnectionClass();
                    
                    String username = ch3.getSelectedItem();
                    String q1 = "SELECT name FROM customer WHERE username='" +username+ "'";
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

        // Listener for source selection
        ch1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                ch2.removeAll(); // Clear previous destinations
                try {
                    ConnectionClass obj = new ConnectionClass();
                    String source = ch1.getSelectedItem();
                    System.out.println("Source selected: " + source);
                    String q1 = "SELECT DISTINCT destination FROM intracity_driver WHERE source='" + source + "'and status = 'free'";
                    ResultSet rest1 = obj.stm.executeQuery(q1);
                    while (rest1.next()) {
                        ch2.add(rest1.getString("destination")); // Populate destinations
                    }
                    String destination = ch2.getSelectedItem();
                    String q2 = "SELECT driver, car, price FROM intracity_driver WHERE source='" + source + "' AND destination='" + destination + "'";
                  System.out.println("Query: " + q2); // Debugging the query

                   rest1 = obj.stm.executeQuery(q2); 

                  if (rest1.next()) {
                      // Set the text fields with the fetched data
                      tf4.setText(rest1.getString("driver"));  // Driver Name
                      tf5.setText(rest1.getString("car"));     // Car
                      tf6.setText(source);                     // Source
                     tf7.setText(destination);                // Destinatio                   
                      tf8.setText(rest1.getString("price"));
                    rest1.close();
                } 
                }catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        });

        // Listener for both source and destination selection
//        ch2.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent arg0) {
//                if (ch1.getSelectedItem() != null && ch2.getSelectedItem() != null) {
//                    try {
//                        ConnectionClass obj = new ConnectionClass();
//                        String source = ch1.getSelectedItem();
//                        String destination = ch2.getSelectedItem();
//
//                        // Debugging
//                        System.out.println("Source: " + source + ", Destination: " + destination);
//
//                        // Fetch the driver, car, and price based on selected source and destination
//                        String q1 = "SELECT driver, car, price FROM intracity_driver WHERE source='" + source + "' AND destination='" + destination + "'";
//                        System.out.println("Query: " + q1); // Debugging the query
//
//                        ResultSet rest1 = obj.stm.executeQuery(q1);
//
//                        if (rest1.next()) {
//                            // Set the text fields with the fetched data
//                            tf4.setText(rest1.getString("driver"));  // Driver Name
//                            tf5.setText(rest1.getString("car"));     // Car
//                            tf6.setText(source);                     // Source
//                            tf7.setText(destination);                // Destination
//                            tf8.setText(rest1.getString("price"));   // Price
//                            System.out.println("Driver: " + rest1.getString("driver") + ", Car: " + rest1.getString("car") + ", Price: " + rest1.getString("price"));
//                        } else {
//                            // Clear text fields if no data found
//                            tf4.setText("");
//                            tf5.setText("");
//                            tf6.setText("");
//                            tf7.setText("");
//                            tf8.setText("");
//                            System.out.println("No data found for selected source and destination.");
//                            JOptionPane.showMessageDialog(Book_Cab.this, "No data found for selected source and destination.");
//                        }
//                    } catch (Exception exx) {
//                        exx.printStackTrace();
//                    }
//                }
//            }
//        });
   }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bt1) {
        	String bookID = tf1.getText();
            String source = ch1.getSelectedItem();
            String destination = ch2.getSelectedItem();
            String username = ch3.getSelectedItem();
            //String name = tf3.getText();
            String drivername = tf4.getText();
            String car = tf5.getText();
            String price = tf8.getText();

            if (source == null || destination == null || username == null || drivername.isEmpty() || car.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the details before booking.");
                return;
            }

            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "INSERT INTO intracity_cab(book_id, source, destination, username, drivername, car, price) " +
                        "VALUES('" + bookID + "', '" + source + "', '" + destination + "', '" + username + "','" + drivername + "', '" + car + "', '" + price + "')";
                
                int result = obj.stm.executeUpdate(query);
                query="update intracity_driver set status = 'booked' where source ='"+ch1.getSelectedItem()+"' AND destination = '"+ch2.getSelectedItem()+"'";
                obj.stm.executeUpdate(query);
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "Cab booked successfully with Booking ID: " + bookID);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to book cab. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == bt2) {
            this.setVisible(false);
        }
    }
    public static void main(String[] args) {
        new Book_Cab().setVisible(true);
    }
}
