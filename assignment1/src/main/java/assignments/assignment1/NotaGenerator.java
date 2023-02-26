package assignments.assignment1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        boolean state = true;
        while (state){
            printMenu();
            System.out.print("Pilihan : ");
            int pilihanUser;
            try {
                pilihanUser = Integer.parseInt(input.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                continue;
            }
            if (pilihanUser==0){
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                state = false;
                input.close();
            } else if (pilihanUser==1) {
                System.out.println("Masukkan nama Anda:");
                String namaUser = input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHp;
                while (true) {
                    nomorHp = input.nextLine();
                    if (nomorHp.trim().chars().allMatch(Character::isDigit)){
                        break;
                    }else {
                        System.out.println("Nomor hp hanya menerima digit");
                    }
                }
                System.out.println("ID Anda : " + generateId(namaUser,nomorHp.trim()));

            } else if (pilihanUser==2) {
                System.out.println("Masukkan nama Anda:");
                String namaUser = input.next();
                input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHp;
                while (true) {
                    nomorHp = input.nextLine();
                    if (nomorHp.trim().chars().allMatch(Character::isDigit)) {
                        break;
                    } else {
                        System.out.println("Nomor hp hanya menerima digit");
                    }
                }
                System.out.println("Masukkan tanggal terima :");
                String tanggalTerima = input.nextLine();

                String paketLaundry;
                while (true) {
                    System.out.println("Masukkan paket laundry :");
                    paketLaundry = input.nextLine();
                    if (paketLaundry.trim().equalsIgnoreCase("express")| paketLaundry.trim().equalsIgnoreCase("fast")| paketLaundry.trim().equalsIgnoreCase("reguler")){
                        break;
                    } else if (paketLaundry.trim().equals("?")) {
                        showPaket();
                    }else{
                        System.out.println("Paket " + paketLaundry.trim() + " tidak diketahui");
                        System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                    }
                }
                System.out.println("Masukkan berat cucian Anda [Kg]:");
                int beratCucian;
                while(true){
                    try{
                        beratCucian = input.nextInt();
                        input.nextLine();
                        if (beratCucian == 1){
                            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                            beratCucian = 2;
                            break;
                        } else if (beratCucian <= 0) {
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        }else{break;}
                    }catch (InputMismatchException e){
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        input.nextLine();
                    }
                }
                System.out.println("Nota Laundry");
                System.out.println(generateNota(generateId(namaUser,nomorHp.trim()),paketLaundry.trim(),beratCucian,tanggalTerima.trim()));

            }else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                System.out.println();
            }
        }
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        String namaUser = nama.trim().toUpperCase();
        String upperCaseName = namaUser;
        if (namaUser.contains(" ")){upperCaseName = namaUser.split(" ")[0];}
        String namaDanNomor = upperCaseName + "-" + nomorHP;
        int angkaChecksum = 0; //starting point untuk menghitung angka checksum
        for (int i = 0; i<namaDanNomor.length();i++){
            char c = namaDanNomor.charAt(i);
            if (Character.isLetter(c)){
                angkaChecksum += c - 'A' + 1;
            }else if(Character.isDigit(c)){
                int digit = Character.digit(c, 10);
                angkaChecksum += digit;
            }else{
                angkaChecksum += 7;
            }
        }
        String output;
        String checksumStr;
        if (angkaChecksum < 10){
            checksumStr = "0" + angkaChecksum;
            output = namaDanNomor + "-" + checksumStr;
        }else if(angkaChecksum > 100){
            checksumStr = Integer.toString(angkaChecksum).substring(Integer.toString(angkaChecksum).length()-2);//conditionals apabila checksum lebih dari 2 digit
            output = namaDanNomor + "-" + checksumStr;
        }else{
            output = namaDanNomor + "-" + angkaChecksum;
        }
        return output;

        }
//



    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){

        int hargaPerPaket = 0;
        int lamaLaundry = 0;
        if (paket.equalsIgnoreCase("express")){hargaPerPaket = 12000; lamaLaundry = 1;}
        else if (paket.equalsIgnoreCase("fast")) {hargaPerPaket = 10000;lamaLaundry = 2;}
        else if (paket.equalsIgnoreCase("reguler")) {hargaPerPaket = 7000;lamaLaundry = 3;}
        int harga = hargaPerPaket * berat;

        LocalDate tanggalAwal = LocalDate.parse(tanggalTerima, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate tanggalAkhir = tanggalAwal.plusDays(lamaLaundry);
        String  tanggalSelesai = tanggalAkhir.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String output = String.format("ID    : %s\n"
                + "Paket : %s\n"
                + "Harga :\n"
                + "%d kg x %d = %d\n"
                + "Tanggal Terima  : %s\n"
                + "Tanggal Selesai : %s",id,paket,berat,hargaPerPaket,harga,tanggalTerima,tanggalSelesai);

        return output;
    }
}
