package Cab_Booking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminSection extends JFrame implements ActionListener {
    Font f, f1;
    JLabel l1, imageLabel;
    JButton bt1, bt2, customerDetailsBtn, cabDetailsBtn;
    JPanel contentPanel;
    JPopupMenu cabDetailsMenu;

    AdminSection() {
        super("Admin Section");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new Font("Arial", Font.BOLD, 40);
        f1 = new Font("Arial", Font.BOLD, 25);

        l1 = new JLabel("Admin Section");
        l1.setFont(f);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setForeground(Color.BLACK);

        // Buttons
        bt1 = new JButton("Intercity Driver");
        bt2 = new JButton("Intracity Driver");
        customerDetailsBtn = new JButton("Customer Details");
        cabDetailsBtn = new JButton("Cab Details");

        bt1.setFont(f1);
        bt2.setFont(f1);
        customerDetailsBtn.setFont(f1);
        cabDetailsBtn.setFont(f1);

        // Set button sizes
        Dimension buttonSize = new Dimension(400, 100);
        bt1.setPreferredSize(buttonSize);
        bt2.setPreferredSize(buttonSize);
        customerDetailsBtn.setPreferredSize(buttonSize);
        cabDetailsBtn.setPreferredSize(buttonSize);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        customerDetailsBtn.addActionListener(this);
        cabDetailsBtn.addActionListener(this);

        // Create dropdown (popup) for Cab Details button
        cabDetailsMenu = new JPopupMenu();
        JMenuItem intracityCabMenuItem = new JMenuItem("View Intracity Cab");
        JMenuItem intercityCabMenuItem = new JMenuItem("View Intercity Cab");
        
        intracityCabMenuItem.setFont(f1);
        intercityCabMenuItem.setFont(f1);
        
        cabDetailsMenu.add(intracityCabMenuItem);
        cabDetailsMenu.add(intercityCabMenuItem);

        // Add action listeners for dropdown items
        intracityCabMenuItem.addActionListener(e -> openIntraCityCab());
        intercityCabMenuItem.addActionListener(e -> openInterCityCab());

        // Set up content panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(l1);
        contentPanel.add(Box.createVerticalStrut(50));
        contentPanel.add(bt1);
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(bt2);
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(customerDetailsBtn);
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(cabDetailsBtn);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("6.jpg"));

        if (img.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.err.println("Image not found!");
        }

        Image scaledImage = img.getImage().getScaledInstance(1100, 800, Image.SCALE_SMOOTH);
        imageLabel = new JLabel(new ImageIcon(scaledImage));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(imageLabel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);

        rightPanel.add(Box.createRigidArea(new Dimension(300, 0)));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(contentPanel);
        rightPanel.add(Box.createVerticalGlue());

        rightPanel.setPreferredSize(new Dimension(1500, 800));
        splitPane.setRightComponent(rightPanel);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(1200);

        add(splitPane);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            new Add_InterCity_Driver().setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == bt2) {
            new Add_IntraCity_Driver().setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == customerDetailsBtn) {
            new View_Customer().setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == cabDetailsBtn) {
            // Show the dropdown menu for Cab Details
            cabDetailsMenu.show(cabDetailsBtn, cabDetailsBtn.getWidth() / 2, cabDetailsBtn.getHeight());
        }
    }

    private void openIntraCityCab() {
        new View_Booked_Cab().setVisible(true);
        this.setVisible(false);
    }

    private void openInterCityCab() {
        new View_Intercity_Booked_Cab().setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new AdminSection().setVisible(true);
    }
}
