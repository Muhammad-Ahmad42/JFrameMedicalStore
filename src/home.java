import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class home extends JFrame {
    public home() {
        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setBounds(100, 100, 450, 339);

        JButton btnMedicineManagement = new JButton("Medicine Management");
        btnMedicineManagement.setBounds(201, 39, 200, 40);
        btnMedicineManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new medicine().setVisible(true);
                dispose();
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(btnMedicineManagement);

        JButton btnSalesManagement = new JButton("Sales Management");
        btnSalesManagement.setBounds(201, 90, 200, 40);
        btnSalesManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new sales().setVisible(true);
                dispose();
            }
        });
        getContentPane().add(btnSalesManagement);

        JButton btnCustomerManagement = new JButton("Customer Management");
        btnCustomerManagement.setBounds(201, 139, 200, 40);
        btnCustomerManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new customer().setVisible(true);
                dispose();
            }
        });
        getContentPane().add(btnCustomerManagement);

        JButton btnSupplierManagement = new JButton("Supplier Management");
        btnSupplierManagement.setBounds(201, 190, 200, 40);
        btnSupplierManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new supplier().setVisible(true);
                dispose();
            }
        });
        getContentPane().add(btnSupplierManagement);

        JButton btnReporting = new JButton("Reporting");
        btnReporting.setBounds(201, 239, 200, 40);
        btnReporting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new reporting().setVisible(true);
                dispose();
            }
        });
        getContentPane().add(btnReporting);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    home frame = new home();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
