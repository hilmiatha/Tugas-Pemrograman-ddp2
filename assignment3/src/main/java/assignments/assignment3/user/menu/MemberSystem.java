package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;

import java.util.Arrays;

import static assignments.assignment3.nota.NotaManager.*;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        displaySpecificMenu();
        if (choice == 1){
            showPaket();
            String paketLaundry = in.nextLine();
            System.out.println("Masukan berat cucian anda [Kg]: ");
            int beratCucian = Integer.parseInt(in.nextLine());
            if (beratCucian < 2){ //validasi apabila berat cucian kurang dari 2 kg
                beratCucian = 2;
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            }
            String tanggalMasuk = fmt.format(cal.getTime());
            Nota nota = new Nota(loginMember,beratCucian,paketLaundry,tanggalMasuk);
            addNota(nota); //menambah nota di list semua nota
            loginMember.addNota(nota); //menambah nota di list nota member tersebut

            //inisiasi objek setrikaService jika diinginkan
            System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\nHanya tambah 1000 / kg :0");
            System.out.print("[Ketik x untuk tidak mau]: ");
            String setrikaChoice = in.nextLine();
            if (!setrikaChoice.equalsIgnoreCase("x")){
                SetrikaService setrika = new SetrikaService();
                nota.addService(setrika);
            }

            //inisiasi objek antarService jika diinginkan
            System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\nCuma 2000 / 4kg, kemudian 500 / kg");
            System.out.print("[Ketik x untuk tidak mau]: ");
            String antarChoice = in.nextLine();
            if (!antarChoice.equalsIgnoreCase("x")){
                AntarService antar = new AntarService();
                nota.addService(antar);
            }
            System.out.println("Nota berhasil dibuat!");


        } else if (choice == 2) {
            //iterate semua nota yang dimiliki member kemudian memanggil toString dari class Nota
            for (Nota nota : loginMember.getNotaList()){
                System.out.println(nota);
            }
        } else if (choice == 3) {
            //mengubah boolean logout agar user bisa keluar dari menu Member
            logout = true;
        }else{
            System.out.println("Perintah tidak tepat!");
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        // memanjangkan memberList kemudian assign member baru di array tersebut
        Member[] lstDummy = Arrays.copyOf(memberList, memberList.length+1);
        lstDummy[lstDummy.length-1] = member;
        memberList = lstDummy;
    }
    public static void showPaket() {
        System.out.println("Masukan paket laundry: ");
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }
}