import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class companies extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCompanyID;
    private JTextField txtCompanyName;
    private JTextField txtEmail;
    private JTextField txtContactNo;
    private JTextField txtAddress;
    private JTable table;
    private DefaultTableModel tableModel;

    public companies() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setBounds(100, 100, 1000, 700);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Company Info");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBounds(407, 11, 152, 61);
        contentPane.add(lblNewLabel_1);

        // Insert Company Button
        JButton btnInsertCompany = new JButton("Insert Company");
        btnInsertCompany.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnInsertCompany.setBounds(33, 246, 152, 37);
        contentPane.add(btnInsertCompany);

        // Update Company Button
        JButton btnUpdateCompany = new JButton("Update Company");
        btnUpdateCompany.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnUpdateCompany.setBounds(33, 316, 152, 37);
        contentPane.add(btnUpdateCompany);

        // Delete Company Button
        JButton btnDeleteCompany = new JButton("Delete Company");
        btnDeleteCompany.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDeleteCompany.setBounds(33, 399, 152, 37);
        contentPane.add(btnDeleteCompany);

        // Back Button
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new home().setVisible(true);
                dispose();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnBack.setBounds(33, 481, 152, 37);
        contentPane.add(btnBack);

        // Search Company TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(206, 174, 391, 25);
        contentPane.add(txtSearch);
        txtSearch.setColumns(10);

        // Search Company Button
        JButton btnSearchCompany = new JButton("Search");
        btnSearchCompany.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSearchCompany.setBounds(638, 173, 152, 24);
        contentPane.add(btnSearchCompany);

        // Table setup
        String[] columnNames = {"Company ID", "Name", "Email", "Contact No", "Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 16));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(206, 246, 584, 386);
        contentPane.add(scrollPane);

        // Company ID TextField
        JLabel lblCompanyID = new JLabel("Company ID:");
        lblCompanyID.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCompanyID.setBounds(33, 80, 100, 25);
        contentPane.add(lblCompanyID);
        txtCompanyID = new JTextField();
        txtCompanyID.setBounds(120, 80, 150, 25);
        contentPane.add(txtCompanyID);

        // Company Name TextField
        JLabel lblCompanyName = new JLabel("Company Name:");
        lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCompanyName.setBounds(280, 80, 100, 25);
        contentPane.add(lblCompanyName);
        txtCompanyName = new JTextField();
        txtCompanyName.setBounds(380, 80, 150, 25);
        contentPane.add(txtCompanyName);

        // Email TextField
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblEmail.setBounds(540, 80, 100, 25);
        contentPane.add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(600, 80, 150, 25);
        contentPane.add(txtEmail);

        // Contact No TextField
        JLabel lblContactNo = new JLabel("Contact No:");
        lblContactNo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblContactNo.setBounds(760, 80, 100, 25);
        contentPane.add(lblContactNo);
        txtContactNo = new JTextField();
        txtContactNo.setBounds(850, 80, 150, 25);
        contentPane.add(txtContactNo);

        // Address TextField
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAddress.setBounds(33, 126, 100, 25);
        contentPane.add(lblAddress);
        txtAddress = new JTextField();
        txtAddress.setBounds(120, 127, 150, 25);
        contentPane.add(txtAddress);

        // ActionListener for Insert Company Button
        btnInsertCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String companyID = txtCompanyID.getText();
                String companyName = txtCompanyName.getText();
                String email = txtEmail.getText();
                String contactNo = txtContactNo.getText();
                String address = txtAddress.getText();

                // Check if Company ID already exists
                if (isCompanyIDExists(companyID)) {
                    JOptionPane.showMessageDialog(null, "Company ID already exists!", "Duplicate Company ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Add the record to the table
                tableModel.addRow(new Object[]{companyID, companyName, email, contactNo, address});

                // Write the record to the file
                writeToFile(companyID, companyName, email, contactNo, address);

                // Clear the input fields
                clearInputFields();
            }
        });

        // ActionListener for Update Company Button
        btnUpdateCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String companyID = txtCompanyID.getText();
                    String companyName = txtCompanyName.getText();
                    String email = txtEmail.getText();
                    String contactNo = txtContactNo.getText();
                    String address = txtAddress.getText();

                    // Update the record in the table
                    tableModel.setValueAt(companyID, selectedRow, 0);
                    tableModel.setValueAt(companyName, selectedRow, 1);
                    tableModel.setValueAt(email, selectedRow, 2);
                    tableModel.setValueAt(contactNo, selectedRow, 3);
                    tableModel.setValueAt(address, selectedRow, 4);

                    // Update the record in the file
                    updateFile();
                }
            }
        });

        // ActionListener for Delete Company Button
        btnDeleteCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                    updateFile();
                }
            }
        });

        // ActionListener for Search Company Button
        btnSearchCompany.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = txtSearch.getText().trim().toLowerCase();
                tableModel.setRowCount(0); // Clear the table before search

                try (BufferedReader reader = new BufferedReader(new FileReader(globalVariable.data2))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(",");
                        // Check if the first column (Company ID) matches the search text
                        if (data[0].toLowerCase().contains(searchText)) {
                            tableModel.addRow(data);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Load data from file on startup
        loadDataFromFile();
    }

    // Method to check if Company ID already exists
    private boolean isCompanyIDExists(String companyID) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (companyID.equals(tableModel.getValueAt(i, 0))) {
                return true;
            }
        }
        return false;
    }

    // Method to write data to file
    private void writeToFile(String companyID, String companyName, String email, String contactNo, String address) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(globalVariable.data2, true))) {
            writer.write(companyID + "," + companyName + "," + email + "," + contactNo + "," + address);
            writer.newLine();
        } catch (IOException ex) {                ex.printStackTrace();
        }
    }

    // Method to update data in the file
    private void updateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(globalVariable.data2))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Method to clear input fields
    private void clearInputFields() {
        txtCompanyID.setText("");
        txtCompanyName.setText("");
        txtEmail.setText("");
        txtContactNo.setText("");
        txtAddress.setText("");
    }

    // Method to load data from file on startup
    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(globalVariable.data2))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

