package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;
    private JPanel mainpanel;
    private final String[] paket = new String[] {"Express", "Fast", "Reguler"};

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        super(new BorderLayout());
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        mainpanel = new JPanel(new GridBagLayout());
        mainpanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(mainpanel,BorderLayout.CENTER);
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        //Konfigurasi awal GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        //Inisiasi dan konfigurasi tempat label paket
        paketLabel = new JLabel();
        paketLabel.setText("Paket Laundry:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainpanel.add(paketLabel,gbc);

        //Inisiasi dan konfigurasi tempat combobox paket
        paketComboBox = new JComboBox<>(paket);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainpanel.add(paketComboBox,gbc);

        //Inisiasi dan konfigurasi tempat button showpaket
        showPaketButton = new JButton();
        showPaketButton.setText("Show paket");
        showPaketButton.addActionListener(e -> showPaket());
        gbc.gridx = 2;
        gbc.gridy = 0;
        mainpanel.add(showPaketButton,gbc);

        //Inisiasi dan konfigurasi tempat label berat
        beratLabel = new JLabel();
        beratLabel.setText("Berat cucian (kg):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainpanel.add(beratLabel,gbc);

        //Inisiasi dan konfigurasi tempat text field berat
        beratTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainpanel.add(beratTextField,gbc);

        //Inisiasi dan konfigurasi tempat checkbox setrika service
        setrikaCheckBox = new JCheckBox();
        setrikaCheckBox.setText("Tambah setrika service (1000/kg)");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainpanel.add(setrikaCheckBox, gbc);

        //Inisiasi dan konfigurasi tempat checkbox antar service
        antarCheckBox = new JCheckBox();
        antarCheckBox.setText("Tambah antar service (2000/4kg pertama, kemudian 500/kg)");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainpanel.add(antarCheckBox, gbc);

        //Inisiasi dan konfigurasi tempat button create nota
        createNotaButton = new JButton();
        createNotaButton.setText("Buat Nota");
        createNotaButton.addActionListener(e -> createNota());
        gbc.gridy = 4;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainpanel.add(createNotaButton, gbc);

        //Inisiasi dan konfigurasi tempat button back
        backButton = new JButton();
        backButton.setText("Kembali");
        backButton.addActionListener(e -> handleBack());
        gbc.gridy = 5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainpanel.add(backButton, gbc);
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        if(beratTextField.getText().trim().equals("")){
            JLabel label = new JLabel("field berat wajib diisi!");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(mainpanel, label, "Perhatian!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!validasiBerat(beratTextField.getText())){
            JLabel label = new JLabel("Berat wajib berisi angka!");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(mainpanel, label, "Perhatian!", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
            return;
        }
        int berat = Integer.parseInt(beratTextField.getText());
        if (berat == 0){
            JLabel label = new JLabel("Berat wajib positif!");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(mainpanel, label, "Perhatian!", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
            return;
        }
        if (berat < 2){
            JLabel label = new JLabel("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(mainpanel, label, "Perhatian!", JOptionPane.INFORMATION_MESSAGE);
            berat = 2;
        }
        String paketCucian = paket[paketComboBox.getSelectedIndex()];
        String tanggalMasuk = fmt.format(cal.getTime());
        Nota nota = new Nota(memberSystemGUI.getLoggedInMember(),berat,paketCucian,tanggalMasuk);
        memberSystemGUI.getLoggedInMember().addNota(nota);
        NotaManager.addNota(nota);

        if (setrikaCheckBox.isSelected()){
            SetrikaService setrika = new SetrikaService();
            nota.addService(setrika);
        }

        if (antarCheckBox.isSelected()){
            AntarService antar = new AntarService();
            nota.addService(antar);
        }

        JLabel label = new JLabel("Nota berhasil dibuat!");
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(mainpanel, label, "Perhatian!", JOptionPane.INFORMATION_MESSAGE);
        clearGUI();


    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        clearGUI();
        MainFrame.getInstance().navigateTo(memberSystemGUI.getPageName());
    }

    /**
     * Method untuk clear semua perubahan di komponen GUI
     */
    private void clearGUI(){
        beratTextField.setText("");
        paketComboBox.setSelectedItem(paket[0]);
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }
    /*
    method untuk validasi berat. return true apabila valid dan false apabila tidak valid
     */
    private boolean validasiBerat(String berat) {
        //setiap char dari string akan dicek apakah digit atau bukan
        return berat.length() > 0 && berat.chars().allMatch(Character::isDigit);
    }
}
