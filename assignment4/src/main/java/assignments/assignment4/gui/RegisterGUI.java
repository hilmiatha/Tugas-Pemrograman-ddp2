package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
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
        // TODO
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);

        nameLabel = new JLabel();
        nameLabel.setText("Masukkan nama anda:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(nameLabel, gbc);

        nameTextField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.5;
        mainPanel.add(nameTextField, gbc);

        phoneLabel = new JLabel();
        phoneLabel.setText("Masukkan nomor handphone anda:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(phoneLabel,gbc);

        phoneTextField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.5;
        mainPanel.add(phoneTextField, gbc);

        passwordLabel = new JLabel();
        passwordLabel.setText("Masukkan password anda:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(passwordLabel,gbc);

        passwordField = new JPasswordField();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0.5;
        mainPanel.add(passwordField, gbc);

        registerButton = new JButton();
        registerButton.setText("Register");
        registerButton.addActionListener(e -> handleRegister());
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        mainPanel.add(registerButton,gbc);

        backButton = new JButton();
        backButton.setText("Kembali");
        backButton.addActionListener(e -> handleBack());
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        mainPanel.add(backButton,gbc);

    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        phoneTextField.setText("");
        nameTextField.setText("");
        passwordField.setText("");
        MainFrame main = MainFrame.getInstance();
        main.navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        if (nameTextField.getText().trim().equals("")|phoneTextField.getText().trim().equals("")|passwordField.getPassword().length == 0){
            JLabel label = new JLabel("Semua field di atas wajib diisi!");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(mainPanel, label, "Perhatian!", JOptionPane.ERROR_MESSAGE);
            return;

        }
        if(!validasiHandphone(phoneTextField.getText())){
            JLabel label = new JLabel("Nomor handphone wajib angka!");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(mainPanel, label, "Perhatian!", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
            return;
        }

        Member member = loginManager.register(nameTextField.getText().trim(),phoneTextField.getText().trim(), passwordField.getText());
        if (member == null){
            JLabel label = new JLabel("User dengan nama " + nameTextField.getText() + " dan nomor hp " + phoneTextField.getText() + " sudah ada!");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(mainPanel, label, "Perhatian!", JOptionPane.ERROR_MESSAGE);
            handleBack();
            return;
        }
        JLabel label = new JLabel("Berhasil membuat user dengan ID " + member.getId());
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(mainPanel, label, "Perhatian!", JOptionPane.INFORMATION_MESSAGE);
        handleBack();

    }

    /*
    method untuk validasi nomor handphone. return true apabila valid dan false apabila tidak valid
     */
    private boolean validasiHandphone(String noHp) {
        //setiap char dari string akan dicek apakah digit atau bukan
        return noHp.trim().length() > 0 && noHp.trim().chars().allMatch(Character::isDigit);
    }

}
