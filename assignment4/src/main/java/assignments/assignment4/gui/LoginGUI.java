package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // Konfigurasi awal GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 20, 0);

        //Inisiasi dan konfigurasi tempat label id
        idLabel = new JLabel();
        idLabel.setText("Masukkan ID anda:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(idLabel, gbc);

        //Inisiasi dan konfigurasi tempat textField ID
        idTextField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.5;
        mainPanel.add(idTextField, gbc);

        //Inisiasi dan konfigurasi tempat label password
        passwordLabel = new JLabel();
        passwordLabel.setText("Masukkan password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passwordLabel,gbc);

        //Inisiasi dan konfigurasi tempat field password
        passwordField = new JPasswordField();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.5;
        mainPanel.add(passwordField, gbc);

        //Inisiasi dan konfigurasi tempat button login
        loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.addActionListener(e -> handleLogin());
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        mainPanel.add(loginButton,gbc);

        //Inisiasi dan konfigurasi tempat button back
        backButton = new JButton();
        backButton.setText("Kembali");
        backButton.addActionListener(e -> handleBack());
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        mainPanel.add(backButton,gbc);

    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        idTextField.setText("");
        passwordField.setText("");
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        // TODO
        String idUser = idTextField.getText();
        String password = passwordField.getText();
        idTextField.setText("");
        passwordField.setText("");
        if(!MainFrame.getInstance().login(idUser,password)){
            JLabel label = new JLabel("ID or pasword invalid!");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(mainPanel, label, "Perhatian!", JOptionPane.ERROR_MESSAGE);

        }
    }
}
