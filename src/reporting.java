import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class reporting extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;

    public reporting() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblReportManagement = new JLabel("Report Management");
        lblReportManagement.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblReportManagement.setBounds(330, 11, 250, 30);
        contentPane.add(lblReportManagement);

        JButton btnSalesReport = new JButton("Sales Report");
        btnSalesReport.setBounds(140, 70, 150, 30);
        contentPane.add(btnSalesReport);

        JButton btnInventoryReport = new JButton("Inventory Report");
        btnInventoryReport.setBounds(140, 110, 150, 30);
        contentPane.add(btnInventoryReport);

        JButton btnFinancialReport = new JButton("Financial Report");
        btnFinancialReport.setBounds(140, 150, 150, 30);
        contentPane.add(btnFinancialReport);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new home().setVisible(true);
                dispose();
            }
        });
        btnBack.setBounds(160, 522, 110, 30);
        contentPane.add(btnBack);

        // Table setup
        String[] columnNames = { "Report Type", "Generated Date", "Details" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 70, 550, 400);
        contentPane.add(scrollPane);

        btnSalesReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] data = { "Sales Report", new Date().toString(), "Details of Sales Report" };
                tableModel.addRow(data);
            }
        });

        btnInventoryReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] data = { "Inventory Report", new Date().toString(), "Details of Inventory Report" };
                tableModel.addRow(data);
            }
        });

        btnFinancialReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] data = { "Financial Report", new Date().toString(), "Details of Financial Report" };
                tableModel.addRow(data);
            }
        });
    }

    // Method to save report data to file
    private void saveReportsToFile() {
    	String fileName="report.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // Write column headers
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                bw.write(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) {
                    bw.write(",");
                }
            }
            bw.newLine();

            // Write data rows
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    bw.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }

            JOptionPane.showMessageDialog(null, "Reports saved to file: " + fileName, "Save to File", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving reports: " + e.getMessage(), "Save to File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    reporting frame = new reporting();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
