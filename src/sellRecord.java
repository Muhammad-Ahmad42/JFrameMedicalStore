import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class sellRecord extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTotalSale;
    private JTextField textFieldMedicine;
    private JTextField textFieldDate;
    private JTextField textFieldCompany;
    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JTable table;
    private DefaultTableModel tableModel;

    // Sample data structure to hold sales records
    private List<SellRecordData> records = new ArrayList<>();
    private static final String data1 = "medicines.txt";
    private static final String data2 = "companies.txt";

    public sellRecord() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setBounds(100, 100, 917, 733);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel txtSellRecord = new JLabel("View Sell Record");
        txtSellRecord.setFont(new Font("Tahoma", Font.BOLD, 24));
        txtSellRecord.setBounds(280, 23, 215, 37);
        contentPane.add(txtSellRecord);

        JButton btnSearch1 = new JButton("Search");
        btnSearch1.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSearch1.setBounds(428, 98, 89, 23);
        contentPane.add(btnSearch1);

        btnSearch1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchByMedicine();
            }
        });

        JButton btnSearch2 = new JButton("Search");
        btnSearch2.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSearch2.setBounds(428, 153, 89, 23);
        contentPane.add(btnSearch2);

        btnSearch2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchByDate();
            }
        });

        JButton btnSearch3 = new JButton("Search");
        btnSearch3.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSearch3.setBounds(428, 212, 89, 23);
        contentPane.add(btnSearch3);

        btnSearch3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchByCompany();
            }
        });

        JButton btnSearch4 = new JButton("Search");
        btnSearch4.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSearch4.setBounds(428, 317, 89, 23);
        contentPane.add(btnSearch4);

        btnSearch4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchByDateRange();
            }
        });

        JLabel lblTotalSale = new JLabel("Total Sale:");
        lblTotalSale.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotalSale.setBounds(144, 553, 129, 14);
        contentPane.add(lblTotalSale);

        txtTotalSale = new JTextField();
        txtTotalSale.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtTotalSale.setBounds(339, 552, 249, 20);
        contentPane.add(txtTotalSale);
        txtTotalSale.setColumns(10);

        JLabel txtSearchMedicine = new JLabel("Search by Medicine");
        txtSearchMedicine.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtSearchMedicine.setBounds(107, 102, 142, 14);
        contentPane.add(txtSearchMedicine);

        JLabel txtSearchDate = new JLabel("Search by Date");
        txtSearchDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtSearchDate.setBounds(107, 157, 121, 14);
        contentPane.add(txtSearchDate);

        JLabel txtSearchCompany = new JLabel("Search by Company");
        txtSearchCompany.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtSearchCompany.setBounds(107, 216, 142, 14);
        contentPane.add(txtSearchCompany);

        textFieldMedicine = new JTextField();
        textFieldMedicine.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchByMedicine();
            }
        });
        textFieldMedicine.setBounds(263, 101, 96, 20);
        contentPane.add(textFieldMedicine);
        textFieldMedicine.setColumns(10);

        textFieldDate = new JTextField();
        textFieldDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchByDate();
            }
        });
        textFieldDate.setColumns(10);
        textFieldDate.setBounds(263, 156, 96, 20);
        contentPane.add(textFieldDate);

        textFieldCompany = new JTextField();
        textFieldCompany.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchByCompany();
            }
        });
        textFieldCompany.setColumns(10);
        textFieldCompany.setBounds(263, 210, 96, 20);
        contentPane.add(textFieldCompany);

        JLabel lblFrom = new JLabel("From");
        lblFrom.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFrom.setBounds(107, 279, 49, 14);
        contentPane.add(lblFrom);

        JLabel lblTo = new JLabel("To");
        lblTo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTo.setBounds(263, 279, 49, 14);
        contentPane.add(lblTo);

        textFieldFrom = new JTextField();
        textFieldFrom.setBounds(107, 320, 96, 20);
        contentPane.add(textFieldFrom);
        textFieldFrom.setColumns(10);

        textFieldTo = new JTextField();
        textFieldTo.setColumns(10);
        textFieldTo.setBounds(263, 320, 96, 20);
        contentPane.add(textFieldTo);

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
        scrollPane.setBounds(108, 370, 508, 156);
        contentPane.add(scrollPane);

        JButton btnDeleteRecord = new JButton("Delete");
        btnDeleteRecord.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDeleteRecord.setBounds(675, 372, 89, 23);
        contentPane.add(btnDeleteRecord);

        btnDeleteRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                    calculateTotalSale();
                }
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBack.setBounds(675, 503, 89, 23);
        contentPane.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new home().setVisible(true);
                dispose();
            }
        });

        loadData(); // Load data from files or other sources
    }

    private void loadData() {
        try {
                      BufferedReader reader1 = new BufferedReader(new FileReader(data1));
            String line;
            while ((line = reader1.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // assuming format: productName, dateOfSale, quantity, total, company
                    try {
                        String productName = parts[0].trim();
                        String dateOfSale = parts[1].trim();
                        int quantity = Integer.parseInt(parts[2].trim());
                        double total = Double.parseDouble(parts[3].trim());
                        String company = parts[4].trim();
                        records.add(new SellRecordData(productName, dateOfSale, quantity, total, company));
                    } catch (NumberFormatException e) {
                        // Handle parsing errors (quantity, total)
                        System.err.println("Error parsing quantity or total for line: " + line);
                        // Optionally, display a message to the user or log the error
                    }
                } else {
                    // Handle incorrect format of a line in medicines.txt
                    System.err.println("Incorrect format in line: " + line);
                    // Optionally, display a message to the user or log the error
                }
            }
            reader1.close();

            // Load data from companies.txt
            BufferedReader reader2 = new BufferedReader(new FileReader(data2));
            while ((line = reader2.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) { // assuming format: companyName, companyAddress
                    // Here you can process the company data if needed
                } else {
                    // Handle incorrect format of a line in companies.txt
                    System.err.println("Incorrect format in line: " + line);
                    // Optionally, display a message to the user or log the error
                }
            }
            reader2.close();

            // Display initial data in the table
            for (SellRecordData record : records) {
                tableModel.addRow(new Object[]{
                    record.getProductName(), record.getDateOfSale(), record.getQuantity(), record.getTotal(), record.getCompany()
                });
            }
            calculateTotalSale();
        } catch (IOException e) {
            // Handle file reading errors (e.g., file not found, IO exception)
            System.err.println("Error reading file: " + e.getMessage());
            // Optionally, display a message to the user or log the error
        }
    }

    private void searchByMedicine() {
        String medicine = textFieldMedicine.getText().trim();
        filterRecords(record -> record.getProductName().equalsIgnoreCase(medicine));
    }

    private void searchByDate() {
        String date = textFieldDate.getText().trim();
        filterRecords(record -> record.getDateOfSale().equals(date));
    }

    private void searchByCompany() {
        String company = textFieldCompany.getText().trim();
        filterRecords(record -> record.getCompany().equalsIgnoreCase(company));
    }

    private void searchByDateRange() {
        String fromDateStr = textFieldFrom.getText().trim();
        String toDateStr = textFieldTo.getText().trim();
        try {
            Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDateStr);
            final Date[] toDate = {new SimpleDateFormat("yyyy-MM-dd").parse(toDateStr)}; // Using a final array to hold toDate

            // Using Calendar to add one day to toDate
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate[0]);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            toDate[0] = cal.getTime();

            filterRecords(record -> {
                try {
                    Date saleDate = new SimpleDateFormat("yyyy-MM-dd").parse(record.getDateOfSale());
                    return !saleDate.before(fromDate) && !saleDate.after(toDate[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return false;
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
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
        txtTotalSale.setText(String.valueOf(total));
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

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setDateOfSale(String dateOfSale) {
            this.dateOfSale = dateOfSale;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public void setCompany(String company) {
            this.company = company;
        }
    }
}