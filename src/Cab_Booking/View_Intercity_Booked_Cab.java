package Cab_Booking;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class View_Intercity_Booked_Cab extends JFrame {
    Font f, headerFont;
    JTable t1;
    String[] columnNames = {"Book ID", "Username", "Name", "Driver Name", "Source", "Destination", "Type", "Car", "Price"};
    String[][] rowData = new String[20][9];
    int i = 0;

    View_Intercity_Booked_Cab() {
        super("All Booked Cab Records");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        f = new Font("MS UI Gothic", Font.BOLD, 15);
        headerFont = new Font("Arial", Font.BOLD, 18);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM intercity_cab";
            ResultSet resultSet = obj.stm.executeQuery(query);

            while (resultSet.next() && i < rowData.length) {
                rowData[i][0] = resultSet.getString("book_id");
                rowData[i][1] = resultSet.getString("username");
                rowData[i][2] = resultSet.getString("name");
                rowData[i][3] = resultSet.getString("drivername");
                rowData[i][4] = resultSet.getString("source");
                rowData[i][5] = resultSet.getString("destination");
                rowData[i][6] = resultSet.getString("type");
                rowData[i][7] = resultSet.getString("car");
                rowData[i][8] = resultSet.getString("price");
                i++;
            }

            if (i == 0) {
                JOptionPane.showMessageDialog(this, "No booked cab records found.", "Information", JOptionPane.INFORMATION_MESSAGE);
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

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new View_Intercity_Booked_Cab().setVisible(true);
    }
}
