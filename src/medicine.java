import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class medicine extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtProductId;
    private JTextField txtProductName;
    private JTextField txtCompanyName;
    private JTextField txtCategory;
    private JTextField txtQuantity;
    private JTextField txtPricePerUnit;
    private JTextField txtSellDate;
    private JTextField txtSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    public medicine() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setBounds(100, 100, 1180, 731);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Medicine Info");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBounds(375, 11, 152, 61);
        contentPane.add(lblNewLabel_1);

        JButton btnInsertMedicine = new JButton("Insert Medicine");
        btnInsertMedicine.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnInsertMedicine.setBounds(399, 181, 152, 37);
        contentPane.add(btnInsertMedicine);

        JButton btnUpdateMedicine = new JButton("Update Medicine");
        btnUpdateMedicine.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnUpdateMedicine.setBounds(33, 373, 152, 37);
        contentPane.add(btnUpdateMedicine);

        JButton btnDeleteMedicine = new JButton("Delete Medicine");
        btnDeleteMedicine.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDeleteMedicine.setBounds(33, 467, 152, 37);
        contentPane.add(btnDeleteMedicine);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new home().setVisible(true); // Assuming Home is another JFrame class
                dispose();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnBack.setBounds(910, 646, 110, 37);
        contentPane.add(btnBack);

        txtSearch = new JTextField();
        txtSearch.setBounds(400, 305, 281, 25);
        contentPane.add(txtSearch);
        txtSearch.setColumns(10);

        JButton btnSearchMedicine = new JButton("Search");
        btnSearchMedicine.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSearchMedicine.setBounds(691, 304, 152, 24);
        contentPane.add(btnSearchMedicine);

        String[] columnNames = { "Product ID", "Product Name", "Company Name", "Category", "Quantity", "Price per Unit", "Sell Date" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(219, 356, 640, 279);
        contentPane.add(scrollPane);

        JLabel lblProductId = new JLabel("Product ID:");
        lblProductId.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblProductId.setBounds(33, 80, 100, 25);
        contentPane.add(lblProductId);
        txtProductId = new JTextField();
        txtProductId.setBounds(120, 80, 150, 25);
        contentPane.add(txtProductId);

        JLabel lblProductName = new JLabel("Product Name:");
        lblProductName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblProductName.setBounds(280, 80, 100, 25);
        contentPane.add(lblProductName);
        txtProductName = new JTextField();
        txtProductName.setBounds(380, 80, 150, 25);
        contentPane.add(txtProductName);

        JLabel lblCompanyName = new JLabel("Company Name:");
        lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCompanyName.setBounds(540, 80, 110, 25);
        contentPane.add(lblCompanyName);
        txtCompanyName = new JTextField();
        txtCompanyName.setBounds(650, 80, 150, 25);
        contentPane.add(txtCompanyName);

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCategory.setBounds(810, 80, 100, 25);
        contentPane.add(lblCategory);
        txtCategory = new JTextField();
        txtCategory.setBounds(880, 80, 150, 25);
        contentPane.add(txtCategory);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblQuantity.setBounds(33, 110, 100, 25);
        contentPane.add(lblQuantity);
        txtQuantity = new JTextField();
        txtQuantity.setBounds(120, 110, 150, 25);
        contentPane.add(txtQuantity);

        JLabel lblPricePerUnit = new JLabel("Price per Unit:");
        lblPricePerUnit.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPricePerUnit.setBounds(280, 110, 100, 25);
        contentPane.add(lblPricePerUnit);
        txtPricePerUnit = new JTextField();
        txtPricePerUnit.setBounds(380, 110, 150, 25);
        contentPane.add(txtPricePerUnit);

        JLabel lblSellDate = new JLabel("Sell Date (YYYY-MM-DD):");
        lblSellDate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSellDate.setBounds(540, 110, 150, 25);
        contentPane.add(lblSellDate);
        txtSellDate = new JTextField();
        txtSellDate.setBounds(690, 110, 110, 25);
        txtSellDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        contentPane.add(txtSellDate);

        btnInsertMedicine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productID = txtProductId.getText();
                String productName = txtProductName.getText();
                String companyName = txtCompanyName.getText();
                String category = txtCategory.getText();
                String quantity = txtQuantity.getText();
                String pricePerUnit = txtPricePerUnit.getText();
                String sellDate = txtSellDate.getText();

                if (!isValidDateFormat(sellDate)) {
                    JOptionPane.showMessageDialog(null, "Invalid Sell Date format! Use YYYY-MM-DD.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double price = Double.parseDouble(pricePerUnit);
                    if (price <= 0) {
                        JOptionPane.showMessageDialog(null, "Price per Unit must be greater than zero!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Price per Unit format! Must be a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (isProductIDExists(productID)) {
                    JOptionPane.showMessageDialog(null, "Product ID already exists!", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Object[] data = { productID, productName, companyName, category, quantity, pricePerUnit, sellDate };
                tableModel.addRow(data);

                clearInputFields();
            }
        });

        btnUpdateMedicine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.setValueAt(txtProductId.getText(), selectedRow, 0);
                    tableModel.setValueAt(txtProductName.getText(), selectedRow, 1);
                    tableModel.setValueAt(txtCompanyName.getText(), selectedRow, 2);
                    tableModel.setValueAt(txtCategory.getText(), selectedRow, 3);
                    tableModel.setValueAt(txtQuantity.getText(), selectedRow, 4);
                    tableModel.setValueAt(txtPricePerUnit.getText(), selectedRow, 5);
                    tableModel.setValueAt(txtSellDate.getText(), selectedRow, 6);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a row to update!", "Update Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDeleteMedicine.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Select a row to delete!", "Delete Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSearchMedicine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchQuery = txtSearch.getText().toLowerCase();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    boolean matchFound = false;
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        String cellValue = tableModel.getValueAt(i, j).toString().toLowerCase();
                        if (cellValue.contains(searchQuery)) {
                            matchFound = true;
                            break;
                        }
                    }
                    if (matchFound) {
                        table.setRowSelectionInterval(i, i);
                        break;
                    }
                }
            }
        });

        JButton btnSaveToFile = new JButton("Save to File");
        btnSaveToFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveDataToFile();
            }
        });
        btnSaveToFile.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSaveToFile.setBounds(460, 646, 152, 37);
        contentPane.add(btnSaveToFile);

        loadExampleData();
        setVisible(true);
    }

    private void loadExampleData() {
        Object[] data1 = { "001", "Paracetamol", "ABC Pharma", "Pain Relief", "100", "2.00", "2024-06-30" };
        Object[] data2 = { "002", "Ibuprofen", "XYZ Labs", "Anti-inflammatory", "50", "3.00", "2024-07-15" };
        tableModel.addRow(data1);
        tableModel.addRow(data2);
    }

    private void saveDataToFile() {
        try {
            FileWriter fw = new FileWriter(globalVariable.data1);
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
                    if (j == 4 || j == 5) {
                        double value = Double.parseDouble(tableModel.getValueAt(i, j).toString());
                        bw.write(String.format("%.2f", value));
                    } else {
                        bw.write(tableModel.getValueAt(i, j).toString());
                    }
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
    private boolean isProductIDExists(String productID) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(productID)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidDateFormat(String date) {
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void clearInputFields() {
        txtProductId.setText("");
        txtProductName.setText("");
        txtCompanyName.setText("");
        txtCategory.setText("");
        txtQuantity.setText("");
        txtPricePerUnit.setText("");
        txtSellDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new medicine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
