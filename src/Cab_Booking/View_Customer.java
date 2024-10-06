package Cab_Booking;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class View_Customer extends JFrame {
    Font f, headerFont;
    JTable t1;
    String[] columnNames = {"Username", "Name", "Age", "Date Of Birth", "Address", "Phone No", "Email", "Country", "Gender", "Aadhar No"};
    String[][] rowData = new String[20][10];
    int i = 0;

    View_Customer() {
        super("All Customer Details");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        f = new Font("MS UI Gothic", Font.BOLD, 15);
        headerFont = new Font("Arial", Font.BOLD, 18);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM customer";
            ResultSet resultSet = obj.stm.executeQuery(query);
            
            while (resultSet.next() && i < rowData.length) {
                rowData[i][0] = resultSet.getString("username");
                rowData[i][1] = resultSet.getString("name");
                rowData[i][2] = resultSet.getString("age");
                rowData[i][3] = resultSet.getString("dob");
                rowData[i][4] = resultSet.getString("address");
                rowData[i][5] = resultSet.getString("phone");
                rowData[i][6] = resultSet.getString("email");
                rowData[i][7] = resultSet.getString("country");
                rowData[i][8] = resultSet.getString("gender");
                rowData[i][9] = resultSet.getString("aadhar");
                i++;
            }

            if (i == 0) {
                JOptionPane.showMessageDialog(this, "No customer details found.", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            t1 = new JTable(rowData, columnNames);
            t1.setFont(f);
            t1.setBackground(Color.WHITE);
            t1.setForeground(Color.BLACK);
            t1.setRowHeight(25);
            t1.getTableHeader().setFont(headerFont);
            t1.getTableHeader().setBackground(Color.LIGHT_GRAY);
            t1.getTableHeader().setForeground(Color.BLACK);

            JScrollPane js = new JScrollPane(t1);
            panel.add(js, BorderLayout.CENTER);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new View_Customer();
    }
}
