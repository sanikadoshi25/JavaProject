package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePage extends JFrame implements ActionListener {

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(imagePath));
            this.backgroundImage = icon.getImage();
        }
 
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            String slogan = "Your Journey, Our Services";
            g.setFont(new Font("Serif", Font.BOLD, 40));
            g.setColor(Color.WHITE);

            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(slogan);
            int x = (getWidth() - textWidth) / 2;
            int y = getHeight() - 50;

            g.drawString(slogan, x, y);
        }
    }

    Font f, f1, f2;

    HomePage() {
        super("Cab Booking Home Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new Font("Lucida Fax", Font.BOLD, 20);
        f1 = new Font("MS UI Gothic", Font.BOLD, 24);
        f2 = new Font("Gadugi", Font.BOLD, 40);

        BackgroundPanel backgroundPanel = new BackgroundPanel("0.jpg");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        JMenuBar m1 = new JMenuBar();
        m1.setPreferredSize(new Dimension(170, 50));

        JMenu men1 = new JMenu("Customer Profile");
        men1.setFont(f);
        JMenuItem ment1 = new JMenuItem("Add Customer Profile");
        ment1.setFont(f);
        // Removed View Customer Profile
        men1.add(ment1);

        JMenu men2 = new JMenu("Manage Customer");
        men2.setFont(f);
        JMenuItem ment3 = new JMenuItem("Update Customer Details");
        ment3.setFont(f);
        men2.add(ment3);

        JMenu men3 = new JMenu("Book Intracity Cab");
        men3.setFont(f);
        JMenuItem ment5 = new JMenuItem("Book Cab");
        ment5.setFont(f);
        // Removed View Booked Cab
        men3.add(ment5);

        JMenu men4 = new JMenu("Book Intercity Cab");
        men4.setFont(f);
        JMenuItem ment7 = new JMenuItem("Book Intercity Cab");
        ment7.setFont(f);
        // Removed View Intercity Booked Cab
        men4.add(ment7);

        JMenu men5 = new JMenu("Bill");
        men5.setFont(f);
        JMenuItem ment11 = new JMenuItem("Check Bill");
        ment11.setFont(f);
        men5.add(ment11);

        JMenu men6 = new JMenu("Delete");
        men6.setFont(f);
        JMenuItem ment12 = new JMenuItem("Delete Customer");
        ment12.setFont(f);
        men6.add(ment12);

        JMenu men7 = new JMenu("Exit");
        men7.setFont(f);
        JMenuItem ment14 = new JMenuItem("Exit");
        ment14.setFont(f);
        men7.add(ment14);

        m1.add(men1);
        m1.add(men2);
        m1.add(men3);
        m1.add(men4);
        m1.add(men5);
        m1.add(men6);
        m1.add(men7);

        m1.setBackground(Color.BLACK);

        men1.setForeground(Color.GRAY);
        men2.setForeground(Color.GRAY);
        men3.setForeground(Color.GRAY);
        men4.setForeground(Color.GRAY);
        men5.setForeground(Color.GRAY);
        men6.setForeground(Color.GRAY);
        men7.setForeground(Color.RED);

        ment1.setBackground(Color.BLACK);
        ment1.setForeground(Color.YELLOW);
        ment3.setBackground(Color.BLACK);
        ment3.setForeground(Color.YELLOW);
        ment5.setBackground(Color.BLACK);
        ment5.setForeground(Color.YELLOW);
        ment7.setBackground(Color.BLACK);
        ment7.setForeground(Color.YELLOW);
        ment11.setBackground(Color.BLACK);
        ment11.setForeground(Color.YELLOW);
        ment12.setBackground(Color.BLACK);
        ment12.setForeground(Color.YELLOW);
        ment14.setBackground(Color.BLACK);
        ment14.setForeground(Color.RED);

        ment1.addActionListener(this);
        ment3.addActionListener(this);
        ment5.addActionListener(this);
        ment7.addActionListener(this);
        ment11.addActionListener(this);
        ment12.addActionListener(this);
        ment14.addActionListener(this);

        setJMenuBar(m1);
    }

    public void actionPerformed(ActionEvent e) {
        String comnd = e.getActionCommand();
        System.out.println("Action Command: " + comnd); // Debug statement

        if (comnd.equals("Add Customer Profile")) {
            new Add_Customer();
        } else if (comnd.equals("Update Customer Details")) {
            System.out.println("Opening Update Customer Details"); // Debug statement
            new Update_Customer1().setVisible(true); // Open Update_Customer window
        } else if (comnd.equals("Book Cab")) {
            new Book_Cab().setVisible(true);
        } else if (comnd.equals("Book Intercity Cab")) {
            new Book_Intercity_Cab().setVisible(true);
        } else if (comnd.equals("Check Bill")) {
            new Check_Bill().setVisible(true);
        } else if (comnd.equals("Delete Customer")) {
            new Delete_Customer().setVisible(true);
        } else if (comnd.equals("Exit")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage home = new HomePage();
            home.setVisible(true);
        });
    }
}
