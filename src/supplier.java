import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class supplier extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtSupplierId;
    private JTextField txtSupplierName;
    private JTextField txtSupplierContact;
    private JTable table;
    private DefaultTableModel tableModel;

    public supplier() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSupplierManagement = new JLabel("Supplier Management");
        lblSupplierManagement.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblSupplierManagement.setBounds(330, 11, 250, 30);
        contentPane.add(lblSupplierManagement);

        JButton btnAddSupplier = new JButton("Add Supplier");
        btnAddSupplier.setBounds(30, 70, 150, 30);
        contentPane.add(btnAddSupplier);

        JButton btnUpdateSupplier = new JButton("Update Supplier");
        btnUpdateSupplier.setBounds(30, 110, 150, 30);
        contentPane.add(btnUpdateSupplier);

        JButton btnDeleteSupplier = new JButton("Delete Supplier");
        btnDeleteSupplier.setBounds(30, 150, 150, 30);
        contentPane.add(btnDeleteSupplier);

        JButton btnViewSuppliers = new JButton("View Suppliers");
        btnViewSuppliers.setBounds(30, 190, 150, 30);
        contentPane.add(btnViewSuppliers);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(30, 230, 150, 30);
        contentPane.add(btnBack);

        String[] columnNames = {"Supplier ID", "Supplier Name", "Contact"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 16));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(268, 70, 582, 400);
        contentPane.add(scrollPane);

        JLabel lblSupplierId = new JLabel("Supplier ID:");
        lblSupplierId.setBounds(30, 300, 100, 25);
        contentPane.add(lblSupplierId);

        txtSupplierId = new JTextField();
        txtSupplierId.setBounds(131, 300, 127, 25);
        contentPane.add(txtSupplierId);
        txtSupplierId.setColumns(10);

        JLabel lblSupplierName = new JLabel("Supplier Name:");
        lblSupplierName.setBounds(30, 340, 100, 25);
        contentPane.add(lblSupplierName);

        txtSupplierName = new JTextField();
        txtSupplierName.setBounds(131, 340, 127, 25);
        contentPane.add(txtSupplierName);
        txtSupplierName.setColumns(10);

        JLabel lblSupplierContact = new JLabel("Contact:");
        lblSupplierContact.setBounds(30, 380, 100, 25);
        contentPane.add(lblSupplierContact);

        txtSupplierContact = new JTextField();
        txtSupplierContact.setBounds(131, 376, 127, 25);
        contentPane.add(txtSupplierContact);
        txtSupplierContact.setColumns(10);

        btnAddSupplier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String supplierID = txtSupplierId.getText();
                String supplierName = txtSupplierName.getText();
                String supplierContact = txtSupplierContact.getText();

                if (isSupplierIDExists(supplierID)) {
                    JOptionPane.showMessageDialog(null, "Supplier ID already exists!", "Duplicate Supplier ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tableModel.addRow(new Object[]{supplierID, supplierName, supplierContact});
                writeToFile(supplierID, supplierName, supplierContact);
                clearInputFields();
            }
        });

        btnUpdateSupplier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String supplierID = txtSupplierId.getText();
                    String supplierName = txtSupplierName.getText();
                    String supplierContact = txtSupplierContact.getText();

                    tableModel.setValueAt(supplierID, selectedRow, 0);
                    tableModel.setValueAt(supplierName, selectedRow, 1);
                    tableModel.setValueAt(supplierContact, selectedRow, 2);

                    updateFile();
                } else {
                    JOptionPane.showMessageDialog(null, "Select a row to update!", "Update Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDeleteSupplier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                    updateFile();
                } else {
                    JOptionPane.showMessageDialog(null, "Select a row to delete!", "Delete Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnViewSuppliers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadDataFromFile();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new home().setVisible(true);
                dispose();
            }
        });

        loadDataFromFile();
    }

    private boolean isSupplierIDExists(String supplierID) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (supplierID.equals(tableModel.getValueAt(i, 0))) {
                return true;
            }
        }
        return false;
    }

    private void writeToFile(String supplierID, String supplierName, String supplierContact) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(globalVariable.data5, true))) {
            writer.write(supplierID + "," + supplierName + "," + supplierContact);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(globalVariable.data5))) {
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

    private void clearInputFields() {
        txtSupplierId.setText("");
        txtSupplierName.setText("");
        txtSupplierContact.setText("");
    }

    private void loadDataFromFile() {
        tableModel.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(globalVariable.data5))) {
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
