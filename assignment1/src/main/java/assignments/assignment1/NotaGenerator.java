package assignments.assignment1;

import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        boolean state = true;
        while (state){
            printMenu();
            System.out.print("Pilihan : ");
            int pilihanUser;
            try {
                pilihanUser = Integer.parseInt(input.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                System.out.println();
                continue;
            }
            if (pilihanUser==0){
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                state = false;
                input.close();
            } else if (pilihanUser==1) {
                System.out.println("Masukkan nama Anda:");
                String namaUser = input.next();
                input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHp;
                while (true) {
                    nomorHp = input.nextLine();
                    if (nomorHp.chars().allMatch(Character::isDigit)){
                        break;
                    }else {
                        System.out.println("Nomor hp hanya menerima digit");
                    }
                }
                System.out.println("ID Anda : " + generateId(namaUser,nomorHp));
                System.out.println();

            } else if (pilihanUser==2) {
                System.out.println("Masukkan nama Anda:");
                String namaUser = input.next();
                input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHp;
                while (true) {
                    nomorHp = input.nextLine();
                    if (nomorHp.chars().allMatch(Character::isDigit)) {
                        break;
                    } else {
                        System.out.println("Nomor hp hanya menerima digit");
                    }
                }
                System.out.println("Masukkan tanggal terima :");
                String tanggalTerima = input.nextLine();
                System.out.println("Masukkan paket laundry :");
                String paketLaundry = input.nextLine();
                while (true) {
                    if (paketLaundry.equalsIgnoreCase("express")| paketLaundry.equalsIgnoreCase("fast")| paketLaundry.equalsIgnoreCase("reguler")){
                        System.out.println();
                        break;
                    } else if (paketLaundry.equals("?")) {
                        showPaket();
                        System.out.println("Masukkan paket laundry :");
                        paketLaundry = input.nextLine();
                        System.out.println();
                    }


                }
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
        // TODO: Implement generate ID sesuai soal.
        String upperCaseName = nama.toUpperCase();
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
        // TODO: Implement generate nota sesuai soal.
        return null;
    }
}
