package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.*;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<>(); //ArrayList berisi objek Nota
    private static ArrayList<Member> memberList = new ArrayList<>(); //ArrayList berisi objek Member

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command) {
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() { //User memilih menu 1
        //Meminta input nama user dan nomor handphone user
        System.out.println("Masukkan nama anda :");
        String nama = input.nextLine();
        System.out.println("Masukan nomor handphone Anda:");
        String nomorHp;
        while (true) { // validasi apabila nomorHp selain digit 0-9
            nomorHp = input.nextLine().trim();
            if (nomorHp.trim().length() > 0 && nomorHp.trim().chars().allMatch(Character::isDigit)) {
                break;
            } else {
                System.out.println("Field nomor hp hanya menerima digit");
            }
        }
        String id = generateId(nama, nomorHp); // Memanggil method generateId dari notaGenerator
        for (Member member : memberList) { // Validasi apabila id yang sama sudah pernah dibuat
            if (member.getId().equals(id)) {
                System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!%n", member.getNama(), member.getNoHp());
                return;
            }
        }
        Member user = new Member(nama, nomorHp, id); // Membuat objek Member baru sesuai data yang diinput user
        memberList.add(user); //Menambah objek user ke list berisi member
        System.out.printf("Berhasil membuat member dengan ID %s!%n", id);

    }

    private static void handleGenerateNota() { // User memilih menu nomor 2
        //Meminta input ID user
        System.out.println("Masukan ID member:");
        String id = input.nextLine().trim();
        Member member = cekMember(id); // memanggil Method cekMember untuk return member sesuai dengan IDnya
        if (member != null) { //conditionals apabila Member ada sesuai IDnya
            String paketLaundry;
            int hariKerja;
            while (true) { //Validasi input paket laundry jika tidak sesuai dengan ketiga paketnya
                System.out.println("Masukkan paket laundry :");
                paketLaundry = input.nextLine();
                if (paketLaundry.trim().equalsIgnoreCase("express")) {
                    hariKerja = 1;
                    break;
                } else if (paketLaundry.trim().equalsIgnoreCase("fast")) {
                    hariKerja = 2;
                    break;
                } else if (paketLaundry.trim().equalsIgnoreCase("reguler")) {
                    hariKerja = 3;
                    break;
                } else if (paketLaundry.trim().equals("?")) {
                    showPaket();
                } else {
                    System.out.println("Paket " + paketLaundry.trim() + " tidak diketahui"); // input user bukan termasuk salah satu dari ketiga paket
                    System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                }
            }

            System.out.println("Masukkan berat cucian Anda [Kg]:");
            long beratCucian;
            while (true) { //Validasi input berat cucian
                try { // validasi apabila input bukan integer
                    beratCucian = input.nextLong();
                    input.nextLine();
                    if (beratCucian == 1) { //bila user menginput 1kg maka akan diubah menjadi 2kg
                        System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                        beratCucian = 2;
                        break;
                    } else if (beratCucian <= 0) { //bila input user bukan merupakan bilangan asli
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    input.nextLine();
                }
            }
            Nota nota = new Nota(member, paketLaundry.trim(), beratCucian, fmt.format(cal.getTime()).trim(), hariKerja); // Membuat objek nota dengan data yang diinput user
            notaList.add(nota); //menambah objek nota yang baru dibuat ke list nota
            System.out.println("Berhasil menambahkan nota!");
            System.out.println(nota.outputNota()); //print nota dengan memanggil method class nota yaitu outputNota()


        } else { //kondisi apabila id tidak terdaftar di sistem
            System.out.printf("Member dengan ID %s tidak ditemukan!%n", id);
        }

    }

    private static void handleListNota() { //User memilih menu nomor 3
        if (notaList.size() == 0) { //Handle apabila belum ada nota yang terdaftar di sistem
            System.out.println("Terdaftar 0 nota dalam sistem.");
        } else { //Jika nota terdaftar dalam sistem
            System.out.printf("Terdaftar %d nota dalam sistem.%n", notaList.size());
            for (Nota nota : notaList) {
                System.out.printf("- [%d] Status      : %s%n", nota.getIdNota(), nota.getStatusReady());
            }
        }
    }

    private static void handleListUser() { // User memilih menu nomor 4
        if (memberList.size() == 0) { // Handle apabila belum ada user yang terdaftar
            System.out.println("Terdaftar 0 member dalam sistem.");
        } else { //Jika sudah ada user yang terdaftar
            System.out.printf("Terdaftar %d member dalam sistem.%n", memberList.size());
            for (Member member : memberList) {
                System.out.printf("- %s : %s%n", member.getId(), member.getNama());
            }
        }
    }

    private static void handleAmbilCucian() { //User memilih menu nomor 5
        // Meminta input ID Nota User
        System.out.println("Masukan ID nota yang akan diambil:");
        String idNotaString;
        int idNota;
        while(true){ // validasi ID Nota berbentuk angka saja
            idNotaString = input.nextLine().trim();
            if (idNotaString.trim().length() > 0 && idNotaString.trim().chars().allMatch(Character::isDigit)){
                idNota = Integer.parseInt(idNotaString);
                break;
            }else{
                System.out.println("ID nota berbentuk angka!");
            }
        }
        //Validasi apabila ID Nota terdaftar di sistem  atau tidak
        if (getNotaIdList().contains(idNota)){
            for (Nota nota : getListSelesai()){
                if (nota.getIdNota() == idNota){
                    notaList.remove(nota);
                    System.out.printf("Nota dengan ID %s berhasil diambil!%n",idNotaString); //output apabila cucian berhasil diambil
                    return;
                }
            }
            System.out.printf("Nota dengan ID %s gagal diambil!%n", idNotaString); //output apabila cucian belum siap diambil
        }else{
            System.out.println("Nota dengan ID " + idNotaString + " tidak ditemukan!"); //output apabila ID Nota tidak terdaftar
        }
    }

    private static void handleNextDay() { //user memilih menu nomor 6

        System.out.println("Dek Depe tidur hari ini... zzz...");
        cal.add(Calendar.DAY_OF_MONTH, 1); //Mengubah tanggal
        for (Nota nota : notaList) {
            nota.setSisaHariPengerjaan(nota.getSisaHariPengerjaan() - 1); //sisa hari pekerjaan setiap nota cucian dikurangi 1
        }
        if (getListSelesai().size() == 0){ // mengecek apabila sudah ada cucian yang siap diambil atau tidak
            System.out.println("Selamat pagi dunia!\n" +
                    "Dek Depe: It's CuciCuci Time.");
        }else {
            for (Nota nota : getListSelesai()){ // iterasi setiap nota cucian yang sudah siap diambil
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!%n",nota.getIdNota());
            }
            System.out.println("Selamat pagi dunia!\n" +
                    "Dek Depe: It's CuciCuci Time.");
        }
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

    /*
    Method untuk mereturn member sesuai dengan ID
     */
    private static Member cekMember(String id) {
        for (Member member : memberList) {
            if (id.equals(member.getId())) {
                return member;
            }
        }
        return null;
    }

    /*
    Method untuk mereturn ArrayList berisi nota yang cuciannya sudah siap diambil
     */
    private static ArrayList<Nota> getListSelesai() {
        ArrayList<Nota> listSelesai = new ArrayList<>(1);
        for (Nota nota : notaList) {
            if (nota.getReady()) {
                listSelesai.add(nota);
            }
        }
        return listSelesai;
    }
    /*
    Method untuk mereturn ArrayList yang berisi ID Nota
     */
    private static ArrayList<Integer> getNotaIdList() {
        ArrayList<Integer> listIdNota = new ArrayList<>();
        for (Nota nota : notaList) {
            listIdNota.add(nota.getIdNota());
        }
        return listIdNota;
    }
}


