package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        JButton[] arrButton = new JButton[2];
        JButton nyuciButton = new JButton("It's nyuci time");
        JButton displayButton = new JButton("Display list nota");
        arrButton[0] = nyuciButton;
        arrButton[1] = displayButton;
        return arrButton;
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        String output = "";
        if (NotaManager.notaList.length == 0){
            JLabel label = new JLabel("Belum ada nota");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(this, label, "Display Nota", JOptionPane.ERROR_MESSAGE);
        }else{
            for (Nota nota : NotaManager.notaList){
                output += nota.getNotaStatus() +"<br>";

            }
            JLabel label = new JLabel("<html>" + output + "</html>");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(this, label, "Display Nota", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        JLabel label = new JLabel("Stand back!! " + loggedInMember.getNama() + " beginning to nyuci!");
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "cuciCuci Time", JOptionPane.INFORMATION_MESSAGE);

        String output = "";
        if (NotaManager.notaList.length == 0){
            JLabel lbl = new JLabel("Nothing to cuci here");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(this, lbl, "cuciCuci results", JOptionPane.ERROR_MESSAGE);
        }else{
            for (Nota nota : NotaManager.notaList){
                output += nota.kerjakan() + "<br>";
            }
            JLabel lbl = new JLabel("<html>" + output + "</html>");
            label.setFont(new Font("monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(this, lbl, "cuciCuci results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
