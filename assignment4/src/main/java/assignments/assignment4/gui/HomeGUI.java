package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.*;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.NORTH);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        //pengaturan awal GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 20, 0);
        //Inisiasi dan konfigurasi tempat label title
        titleLabel = new JLabel();
        titleLabel.setText("Selamat Datang di CuciCuci System!");
        Font font = new Font("Arial", Font.BOLD, 24);
        titleLabel.setFont(font);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        //Inisiasi dan konfigurasi tempat button login
        loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.addActionListener(e -> handleToLogin());
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(loginButton,gbc);

        //Inisiasi dan konfigurasi tempat button register
        registerButton = new JButton();
        registerButton.setText("Register");
        registerButton.addActionListener(e -> handleToRegister());
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(registerButton,gbc);

        //Inisiasi dan konfigurasi tempat button toNextDay
        toNextDayButton = new JButton();
        toNextDayButton.setText("Next Day");
        toNextDayButton.addActionListener(e -> handleNextDay());
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(toNextDayButton,gbc);

        //Inisiasi dan konfigurasi tempat label date
        dateLabel = new JLabel();
        dateLabel.setText("Hari ini : " + fmt.format(cal.getTime()));
        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(dateLabel,gbc);


    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame main = MainFrame.getInstance();
        main.navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame main = MainFrame.getInstance();
        main.navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        NotaManager.toNextDay();
        dateLabel.setText("Hari ini : " + fmt.format(cal.getTime()));
        JLabel label = new JLabel("Kamu tidur hari ini ...zzz...");
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(mainPanel, label, "Perhatian!", JOptionPane.INFORMATION_MESSAGE);
    }
}
