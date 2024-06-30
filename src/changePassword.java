import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class changePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldEmail;
    private JTextField textFieldNewPassword;
    private JTextField textFieldConfirmPassword;
    public static final String data = "password.txt";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    changePassword frame = new changePassword();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public changePassword() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 524);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Change Password");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel.setBounds(148, 21, 197, 33);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Email");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(32, 92, 82, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("New Password");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(32, 136, 104, 14);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Confirm Password");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1_1.setBounds(32, 179, 128, 14);
        contentPane.add(lblNewLabel_1_1_1);

        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        textFieldEmail.setBounds(198, 91, 228, 20);
        contentPane.add(textFieldEmail);
        textFieldEmail.setColumns(10);

        textFieldNewPassword = new JTextField();
        textFieldNewPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        textFieldNewPassword.setColumns(10);
        textFieldNewPassword.setBounds(198, 133, 228, 20);
        contentPane.add(textFieldNewPassword);

        textFieldConfirmPassword = new JTextField();
        textFieldConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        textFieldConfirmPassword.setColumns(10);
        textFieldConfirmPassword.setBounds(198, 178, 228, 20);
        contentPane.add(textFieldConfirmPassword);

        JButton btnchangepassword = new JButton("Change Password");
        btnchangepassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnchangepassword.setBounds(232, 247, 177, 23);
        contentPane.add(btnchangepassword);

        btnchangepassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new home().setVisible(true);
                dispose();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBack.setBounds(232, 298, 177, 23);
        contentPane.add(btnBack);
    }

    private void changePassword() {
        String email = textFieldEmail.getText().trim();
        String newPassword = textFieldNewPassword.getText().trim();
        String confirmPassword = textFieldConfirmPassword.getText().trim();

        if (email.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        List<String> lines = new ArrayList<>();
        boolean emailFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(globalVariable.data))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].trim().equalsIgnoreCase(email)) {
                    lines.add(email + "," + newPassword);
                    emailFound = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!emailFound) {
            System.out.println("Email not found.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(data))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Password changed successfully for " + email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}