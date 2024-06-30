import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class customer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCustomerId;
    private JTextField txtCustomerName;
    private JTextField txtCustomerContact;
    private JTable table;
    private DefaultTableModel tableModel;

    public customer() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCustomerManagement = new JLabel("Customer Management");
        lblCustomerManagement.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCustomerManagement.setBounds(330, 11, 250, 30);
        contentPane.add(lblCustomerManagement);

        JLabel lblCustomerId = new JLabel("Customer ID:");
        lblCustomerId.setBounds(30, 70, 100, 20);
        contentPane.add(lblCustomerId);

        txtCustomerId = new JTextField();
        txtCustomerId.setBounds(140, 70, 150, 20);
        contentPane.add(txtCustomerId);

        JLabel lblCustomerName = new JLabel("Customer Name:");
        lblCustomerName.setBounds(30, 110, 100, 20);
        contentPane.add(lblCustomerName);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(140, 110, 150, 20);
        contentPane.add(txtCustomerName);

        JLabel lblCustomerContact = new JLabel("Customer Contact:");
        lblCustomerContact.setBounds(30, 150, 100, 20);
        contentPane.add(lblCustomerContact);

        txtCustomerContact = new JTextField();
        txtCustomerContact.setBounds(140, 150, 150, 20);
        contentPane.add(txtCustomerContact);

        JButton btnAddCustomer = new JButton("Add Customer");
        btnAddCustomer.setBounds(140, 190, 150, 30);
        contentPane.add(btnAddCustomer);

        JButton btnUpdateCustomer = new JButton("Update Customer");
        btnUpdateCustomer.setBounds(140, 230, 150, 30);
        contentPane.add(btnUpdateCustomer);

        JButton btnDeleteCustomer = new JButton("Delete Customer");
        btnDeleteCustomer.setBounds(140, 270, 150, 30);
        contentPane.add(btnDeleteCustomer);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new home().setVisible(true);
                dispose();
            }
        });
        btnBack.setBounds(140, 310, 150, 30);
        contentPane.add(btnBack);

        String[] columnNames = { "Customer ID", "Customer Name", "Customer Contact" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 70, 550, 400);
        contentPane.add(scrollPane);

        btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerId = txtCustomerId.getText();
                String customerName = txtCustomerName.getText();
                String customerContact = txtCustomerContact.getText();

                Object[] data = { customerId, customerName, customerContact };
                tableModel.addRow(data);

                clearInputFields();
            }
        });

        btnUpdateCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.setValueAt(txtCustomerId.getText(), selectedRow, 0);
                    tableModel.setValueAt(txtCustomerName.getText(), selectedRow, 1);
                    tableModel.setValueAt(txtCustomerContact.getText(), selectedRow, 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a row to update!", "Update Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDeleteCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a row to delete!", "Delete Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnSaveToFile = new JButton("Save to File");
        btnSaveToFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveDataToFile();
            }
        });
        btnSaveToFile.setBounds(140, 350, 150, 30);
        contentPane.add(btnSaveToFile);

        setVisible(true);
    }

    private void clearInputFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerContact.setText("");
    }

    private void saveDataToFile() {
        try {
            FileWriter fw = new FileWriter(globalVariable.data2);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                bw.write(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) {
                    bw.write(",");
                }
            }
            bw.newLine();

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    bw.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }

            bw.close();
            fw.close();

            JOptionPane.showMessageDialog(null, "Data saved to file successfully!", "Save to File", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving data to file: " + ex.getMessage(), "Save to File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}
