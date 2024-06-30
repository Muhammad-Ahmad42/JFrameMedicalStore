import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class sales extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTotalSale;
    private JTextField textFieldMedicine;
    private JTextField textFieldDate;
    private JTextField textFieldCompany;
    private JTextField textFieldQuantity;
    private JTextField textFieldPrice;
    private JTable table;
    private DefaultTableModel tableModel;

    private List<SellRecordData> records = new ArrayList<>();


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    sales frame = new sales();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public sales() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 917, 733);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSalesRecord = new JLabel("Sales Management");
        lblSalesRecord.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblSalesRecord.setBounds(280, 23, 279, 37);
        contentPane.add(lblSalesRecord);

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSearch.setBounds(85, 486, 89, 23);
        contentPane.add(btnSearch);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchByMedicine();
            }
        });

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAdd.setBounds(468, 314, 89, 23);
        contentPane.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSale();
            }
        });

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnUpdate.setBounds(85, 390, 89, 23);
        contentPane.add(btnUpdate);

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateSale();
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDelete.setBounds(85, 437, 89, 23);
        contentPane.add(btnDelete);

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSale();
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBack.setBounds(45, 662, 89, 23);
        contentPane.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new home().setVisible(true);
                dispose();
            }
        });

        JLabel lblTotalSale = new JLabel("Total Sale:");
        lblTotalSale.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotalSale.setBounds(177, 622, 129, 14);
        contentPane.add(lblTotalSale);

        txtTotalSale = new JTextField();
        txtTotalSale.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtTotalSale.setBounds(367, 619, 249, 20);
        contentPane.add(txtTotalSale);
        txtTotalSale.setColumns(10);

        JLabel lblSearchMedicine = new JLabel("Medicine Name");
        lblSearchMedicine.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSearchMedicine.setBounds(107, 102, 142, 14);
        contentPane.add(lblSearchMedicine);

        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDate.setBounds(107, 153, 121, 14);
        contentPane.add(lblDate);

        JLabel lblCompany = new JLabel("Company");
        lblCompany.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCompany.setBounds(107, 188, 121, 14);
        contentPane.add(lblCompany);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblQuantity.setBounds(107, 238, 121, 14);
        contentPane.add(lblQuantity);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPrice.setBounds(107, 291, 121, 14);
        contentPane.add(lblPrice);

        textFieldMedicine = new JTextField();
        textFieldMedicine.setBounds(259, 84, 204, 37);
        contentPane.add(textFieldMedicine);
        textFieldMedicine.setColumns(10);

        textFieldDate = new JTextField();
        textFieldDate.setEditable(false);
        textFieldDate.setBounds(259, 125, 204, 37);
        contentPane.add(textFieldDate);
        textFieldDate.setColumns(10);
        textFieldDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        textFieldCompany = new JTextField();
        textFieldCompany.setBounds(259, 168, 204, 37);
        contentPane.add(textFieldCompany);
        textFieldCompany.setColumns(10);

        textFieldQuantity = new JTextField();
        textFieldQuantity.setBounds(259, 216, 204, 37);
        contentPane.add(textFieldQuantity);
        textFieldQuantity.setColumns(10);

        textFieldPrice = new JTextField();
        textFieldPrice.setBounds(259, 274, 204, 31);
        contentPane.add(textFieldPrice);
        textFieldPrice.setColumns(10);

        String[] columnNames = {
            "Product Name", "Date of Sale", "Quantity", "Total", "Company"
        };

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 16));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(227, 355, 508, 242);
        contentPane.add(scrollPane);

        loadData(); // Load data from files or other sources
    }

    private void loadData() {
        try {
            // Load sales data from sales.txt
            BufferedReader reader = new BufferedReader(new FileReader(globalVariable.data3));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String productName = parts[0].trim();
                    String dateOfSale = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    double total = Double.parseDouble(parts[3].trim());
                    String company = parts[4].trim();
                    records.add(new SellRecordData(productName, dateOfSale, quantity, total, company));
                }
            }
            reader.close();

            // Display initial data in the table
            for (SellRecordData record : records) {
                             tableModel.addRow(new Object[]{
                        record.getProductName(), record.getDateOfSale(), record.getQuantity(), record.getTotal(), record.getCompany()
                    });
                }
                calculateTotalSale();
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }

        private void searchByMedicine() {
            String medicine = textFieldMedicine.getText().trim();
            filterRecords(record -> record.getProductName().equalsIgnoreCase(medicine));
        }

        private void addSale() {
            String productName = textFieldMedicine.getText().trim();
            String dateOfSale = textFieldDate.getText().trim();
            int quantity = Integer.parseInt(textFieldQuantity.getText().trim());
            double price = Double.parseDouble(textFieldPrice.getText().trim());
            String company = textFieldCompany.getText().trim();

            double total = price * quantity * 1.1; // 10% more than actual price

            SellRecordData newRecord = new SellRecordData(productName, dateOfSale, quantity, total, company);
            records.add(newRecord);
            tableModel.addRow(new Object[]{newRecord.getProductName(), newRecord.getDateOfSale(), newRecord.getQuantity(), newRecord.getTotal(), newRecord.getCompany()});

            saveSale(newRecord);
            calculateTotalSale();
        }

        private void updateSale() {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String productName = textFieldMedicine.getText().trim();
                String dateOfSale = textFieldDate.getText().trim();
                int quantity = Integer.parseInt(textFieldQuantity.getText().trim());
                double price = Double.parseDouble(textFieldPrice.getText().trim());
                String company = textFieldCompany.getText().trim();

                double total = price * quantity * 1.1; // 10% more than actual price

                SellRecordData updatedRecord = new SellRecordData(productName, dateOfSale, quantity, total, company);
                records.set(selectedRow, updatedRecord);
                tableModel.setValueAt(updatedRecord.getProductName(), selectedRow, 0);
                tableModel.setValueAt(updatedRecord.getDateOfSale(), selectedRow, 1);
                tableModel.setValueAt(updatedRecord.getQuantity(), selectedRow, 2);
                tableModel.setValueAt(updatedRecord.getTotal(), selectedRow, 3);
                tableModel.setValueAt(updatedRecord.getCompany(), selectedRow, 4);

                updateSaleFile(); // Update sales.txt
                calculateTotalSale();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void deleteSale() {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String productName = (String) tableModel.getValueAt(selectedRow, 0);
                String dateOfSale = (String) tableModel.getValueAt(selectedRow, 1);
                int quantity = (int) tableModel.getValueAt(selectedRow, 2);
                double total = (double) tableModel.getValueAt(selectedRow, 3);
                String company = (String) tableModel.getValueAt(selectedRow, 4);

                SellRecordData deletedRecord = new SellRecordData(productName, dateOfSale, quantity, total, company);
                records.remove(deletedRecord);
                tableModel.removeRow(selectedRow);

                deleteFromSaleFile(deletedRecord); // Delete from sales.txt
                calculateTotalSale();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void saveSale(SellRecordData record) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(globalVariable.data3, true))) {
                writer.write(String.format("%s,%s,%d,%.2f,%s\n", record.getProductName(), record.getDateOfSale(), record.getQuantity(), record.getTotal(), record.getCompany()));
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }

        private void updateSaleFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(globalVariable.data3))) {
                for (SellRecordData record : records) {
                    writer.write(String.format("%s,%s,%d,%.2f,%s\n", record.getProductName(), record.getDateOfSale(), record.getQuantity(), record.getTotal(), record.getCompany()));
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }

        private void deleteFromSaleFile(SellRecordData record) {
            List<SellRecordData> updatedRecords = new ArrayList<>(records);
            updatedRecords.remove(record);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(globalVariable.data3))) {
                for (SellRecordData updatedRecord : updatedRecords) {
                    writer.write(String.format("%s,%s,%d,%.2f,%s\n", updatedRecord.getProductName(), updatedRecord.getDateOfSale(), updatedRecord.getQuantity(), updatedRecord.getTotal(), updatedRecord.getCompany()));
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }

        private void filterRecords(java.util.function.Predicate<SellRecordData> predicate) {
            tableModel.setRowCount(0);
            for (SellRecordData record : records) {
                if (predicate.test(record)) {
                    tableModel.addRow(new Object[]{
                        record.getProductName(), record.getDateOfSale(), record.getQuantity(), record.getTotal(), record.getCompany()
                    });
                }
            }
            calculateTotalSale();
        }

        private void calculateTotalSale() {
            double total = 0;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                total += (double) tableModel.getValueAt(i, 3);
            }
            txtTotalSale.setText(String.format("%.2f", total));
        }

        class SellRecordData {
            private String productName;
            private String dateOfSale;
            private int quantity;
            private double total;
            private String company;

            public SellRecordData(String productName, String dateOfSale, int quantity, double total, String company) {
                this.productName = productName;
                this.dateOfSale = dateOfSale;
                this.quantity = quantity;
                this.total = total;
                this.company = company;
            }

            public String getProductName() {
                return productName;
            }

            public String getDateOfSale() {
                return dateOfSale;
            }

            public int getQuantity() {
                return quantity;
            }

            public double getTotal() {
                return total;
            }

            public String getCompany() {
                return company;
            }
        }
    }