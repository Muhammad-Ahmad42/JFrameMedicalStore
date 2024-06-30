import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class signUp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfName;
    private JTextField tfEmail;
    private JPasswordField tfPassword;
    private JPasswordField tfConfirmPassword;
    private JButton btnShowPassword;
    private JButton btnShowConfirmPassword;
    private ImageIcon eyeIcon;
    private ImageIcon eyeSlashIcon;
    public static final String data = "password.txt"; // Name of the data file

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    signUp frame = new signUp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public signUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setBounds(100, 100, 935, 609);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBackground(new Color(47, 47, 47));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JTextPane txtpnSignUp = new JTextPane();
        txtpnSignUp.setForeground(new Color(255, 255, 255));
        txtpnSignUp.setBackground(new Color(47, 47, 47));
        txtpnSignUp.setFont(new Font("Tahoma", Font.BOLD, 25));
        txtpnSignUp.setText("Sign up");
        txtpnSignUp.setEditable(false);
        txtpnSignUp.setBounds(290, 11, 170, 39);
        contentPane.add(txtpnSignUp);
        
        JTextPane txtpnCreateYourAccount = new JTextPane();
        txtpnCreateYourAccount.setForeground(new Color(255, 255, 255));
        txtpnCreateYourAccount.setBackground(new Color(47, 47, 47));
        txtpnCreateYourAccount.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtpnCreateYourAccount.setEditable(false);
        txtpnCreateYourAccount.setText("create your account");
        txtpnCreateYourAccount.setBounds(243, 61, 199, 25);
        contentPane.add(txtpnCreateYourAccount);
        
        JTextPane txtpnName = new JTextPane();
        txtpnName.setForeground(new Color(255, 255, 255));
        txtpnName.setBackground(new Color(47, 47, 47));
        txtpnName.setEditable(false);
        txtpnName.setText("Name");
        txtpnName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtpnName.setBounds(195, 93, 61, 25);
        contentPane.add(txtpnName);
        
        tfName = new JTextField();
        tfName.setBounds(195, 129, 310, 39);
        contentPane.add(tfName);
        tfName.setColumns(10);
        
        JTextPane txtpnEmail = new JTextPane();
        txtpnEmail.setForeground(new Color(255, 255, 255));
        txtpnEmail.setBackground(new Color(47, 47, 47));
        txtpnEmail.setEditable(false);
        txtpnEmail.setText("email");
        txtpnEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtpnEmail.setBounds(195, 179, 61, 25);
        contentPane.add(txtpnEmail);
        
        tfEmail = new JTextField();
        tfEmail.setBounds(195, 215, 310, 39);
        contentPane.add(tfEmail);
        tfEmail.setColumns(10);

        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBounds(195, 301, 310, 39);
        contentPane.add(passwordPanel);

        tfPassword = new JPasswordField();
        passwordPanel.add(tfPassword, BorderLayout.CENTER);
        tfPassword.setColumns(10);

        JPanel confirmPasswordPanel = new JPanel(new BorderLayout());
        confirmPasswordPanel.setBounds(195, 387, 310, 39);
        contentPane.add(confirmPasswordPanel);

        tfConfirmPassword = new JPasswordField();
        confirmPasswordPanel.add(tfConfirmPassword, BorderLayout.CENTER);
        tfConfirmPassword.setColumns(10);
        
        JTextPane txtpnPassword = new JTextPane();
        txtpnPassword.setForeground(new Color(255, 255, 255));
        txtpnPassword.setBackground(new Color(47, 47, 47));
        txtpnPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtpnPassword.setText("Password");
        txtpnPassword.setEditable(false);
        txtpnPassword.setBounds(195, 265, 97, 25);
        contentPane.add(txtpnPassword);
        
        JTextPane txtpnConfirmPassword = new JTextPane();
        txtpnConfirmPassword.setForeground(new Color(255, 255, 255));
        txtpnConfirmPassword.setBackground(new Color(47, 47, 47));
        txtpnConfirmPassword.setEditable(false);
        txtpnConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtpnConfirmPassword.setText("Confirm Password");
        txtpnConfirmPassword.setBounds(195, 351, 157, 34);
        contentPane.add(txtpnConfirmPassword);

        btnShowPassword = new JButton("");
        btnShowPassword.setIcon(new ImageIcon("C:\\Users\\meahm\\eclipse-workspace\\JavaProject1\\icons8-eye-48.png"));
        btnShowPassword.setPreferredSize(new Dimension(35, 35));
        btnShowPassword.setFocusPainted(false);
        btnShowPassword.setContentAreaFilled(false);
        btnShowPassword.setBorderPainted(false);
        btnShowPassword.setOpaque(false);

        btnShowPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfPassword.getEchoChar() != '\u0000') {
                    tfPassword.setEchoChar('\u0000');
                } else {
                    tfPassword.setEchoChar('*');
                }
                tfPassword.requestFocusInWindow(); // Ensure password field regains focus
            }
        });


        passwordPanel.add(btnShowPassword, BorderLayout.EAST);

        btnShowConfirmPassword = new JButton(new ImageIcon("C:\\Users\\meahm\\eclipse-workspace\\JavaProject1\\icons8-eye-48.png"));
        btnShowConfirmPassword.setPreferredSize(new Dimension(35, 35));
        btnShowConfirmPassword.setFocusPainted(false);
        btnShowConfirmPassword.setContentAreaFilled(false);
        btnShowConfirmPassword.setBorderPainted(false);
        btnShowConfirmPassword.setOpaque(false);

        btnShowConfirmPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfConfirmPassword.getEchoChar() != '\u0000') {
                    tfConfirmPassword.setEchoChar('\u0000');
                } else {
                    tfConfirmPassword.setEchoChar('*');
                }
                tfConfirmPassword.requestFocusInWindow(); // Ensure confirm password field regains focus
            }
        });

        confirmPasswordPanel.add(btnShowConfirmPassword, BorderLayout.EAST);
        
        JButton btnSubmitSignUp = new JButton("Sign Up");
        btnSubmitSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = tfName.getText();
                String email = tfEmail.getText();
                String password = new String(tfPassword.getPassword());
                String confirmPassword = new String(tfConfirmPassword.getPassword());
                
                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                    return;
                }
                
                if (password.length() < 8) {
                    JOptionPane.showMessageDialog(null, "Password length should not be less than 8.");
                    return;
                }
                
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match.");
                    return;
                }
                
                // If all checks pass, write data to file
                FileWriter fw = null;
                BufferedWriter bw = null;
                try {
                    fw = new FileWriter(data, true); // Append mode
                    bw = new BufferedWriter(fw);
                    bw.write(name + "," + email + "," + password); // Write data to file
                    bw.newLine(); // Write new line
                    
                    JOptionPane.showMessageDialog(null, "Data added successfully.");
                    
                    // Clear fields after successful sign up
                    tfName.setText("");
                    tfEmail.setText("");
                    tfPassword.setText("");
                    tfConfirmPassword.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    // Close resources in finally block
                    try {
                        if (bw != null) {
                            bw.close();
                        }
                        if (fw != null) {
                            fw.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnSubmitSignUp.setBackground(new Color(230, 230, 230));
        btnSubmitSignUp.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnSubmitSignUp.setBounds(255, 437, 147, 34);
        contentPane.add(btnSubmitSignUp);
        
        JTextPane txtpnOr = new JTextPane();
        txtpnOr.setForeground(new Color(255, 255, 255));
        txtpnOr.setBackground(new Color(47, 47, 47));
        txtpnOr.setEditable(false);
        txtpnOr.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtpnOr.setText("Or");
        txtpnOr.setBounds(290, 482, 54, 20);
        contentPane.add(txtpnOr);
        
        JTextPane txtpnAlreadyHaveA = new JTextPane();
        txtpnAlreadyHaveA.setForeground(new Color(255, 255, 255));
        txtpnAlreadyHaveA.setBackground(new Color(47, 47, 47));
        txtpnAlreadyHaveA.setEditable(false);
        txtpnAlreadyHaveA.setFont(new Font("Tahoma", Font.PLAIN, 17));
        txtpnAlreadyHaveA.setText("Already have an account?");
        txtpnAlreadyHaveA.setBounds(148, 513, 213, 34);
        contentPane.add(txtpnAlreadyHaveA);
        
        JButton btnMoveToLogin = new JButton("Login");
        btnMoveToLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        login login = new login();
                        login.setVisible(true);
                    }
                });
                dispose();
            }
        });
        btnMoveToLogin.setBackground(new Color(255, 255, 255));
        btnMoveToLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnMoveToLogin.setBounds(351, 513, 109, 34);
        contentPane.add(btnMoveToLogin);
    }
}