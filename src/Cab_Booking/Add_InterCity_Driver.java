package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Add_InterCity_Driver extends JFrame implements ActionListener {
    Font f, f1;
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6;
    JButton bt1, bt2;
    JPanel mainPanel;

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            java.net.URL imgURL = getClass().getResource("/3.jpg");
            if (imgURL != null) {
                backgroundImage = new ImageIcon(imgURL).getImage();
            } else {
                System.err.println("Could not find the image.");
            }
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    Add_InterCity_Driver() {
        super("Add InterCity Driver");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f = new Font("Arial", Font.BOLD, 35);
        f1 = new Font("Arial", Font.BOLD, 22);

        l1 = new JLabel("Add Intercity Driver");
        l2 = new JLabel("Driver Name");
        l3 = new JLabel("Source");
        l4 = new JLabel("Destination");
        l5 = new JLabel("Car");
        l6 = new JLabel("Price");
        l7 = new JLabel("Type");

        tf1 = new JTextField(15);
        tf2 = new JTextField(15);
        tf3 = new JTextField(15);
        tf4 = new JTextField(15);
        tf5 = new JTextField(15);
        tf6 = new JTextField(15);

        bt1 = new JButton("Add Intercity Driver");
        bt1.addActionListener(this);
        bt2 = new JButton("Back");
        bt2.addActionListener(this);

        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);
        l4.setFont(f1);
        l5.setFont(f1);
        l6.setFont(f1);
        l7.setFont(f1); 

        tf1.setFont(f1);
        tf2.setFont(f1);
        tf3.setFont(f1);
        tf4.setFont(f1);
        tf5.setFont(f1);
        tf6.setFont(f1);

        bt1.setFont(f1);
        bt2.setFont(f1);
        bt1.setPreferredSize(new Dimension(250, 50));
        bt2.setPreferredSize(new Dimension(250, 50));
        
        mainPanel = new BackgroundPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(l1, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; 
        gbc.gridy = 1;
        mainPanel.add(l2, gbc);
        gbc.gridx = 1;
        mainPanel.add(tf1, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 2;
        mainPanel.add(l3, gbc);
        gbc.gridx = 1;
        mainPanel.add(tf2, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 3;
        mainPanel.add(l4, gbc);
        gbc.gridx = 1;
        mainPanel.add(tf3, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 4;
        mainPanel.add(l5, gbc);
        gbc.gridx = 1;
        mainPanel.add(tf4, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 5;
        mainPanel.add(l6, gbc);
        gbc.gridx = 1;
        mainPanel.add(tf5, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 6;
        mainPanel.add(l7, gbc);
        gbc.gridx = 1;
        mainPanel.add(tf6, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(bt1, gbc);
        
        gbc.gridx = 1;
        mainPanel.add(bt2, gbc);
        
        setContentPane(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String name = tf1.getText();
            String source = tf2.getText();
            String destination = tf3.getText();
            String car = tf4.getText();
            String price = tf5.getText();
            String type = tf6.getText();

            try {
                ConnectionClass obj = new ConnectionClass();
                String q = "INSERT INTO intercity_driver(driver, source, destination, car, price, type) " +
                        "VALUES('" + name + "', '" + source + "', '" + destination + "', '" + car + "', '" + price + "', '" + type + "')";
                int aa = obj.stm.executeUpdate(q);
                if (aa == 1) {
                    JOptionPane.showMessageDialog(null, "Your Data Successfully Inserted");
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Please!, Fill all details carefully");
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        if (e.getSource() == bt2) {
            this.setVisible(false);
            new AdminSection().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Add_InterCity_Driver().setVisible(true);
    }
}
