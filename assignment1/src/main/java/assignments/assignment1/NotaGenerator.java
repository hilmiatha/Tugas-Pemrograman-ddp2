package assignments.assignment1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main,.
     */
    public static void main(String[] args) {
        boolean state = true;
        while (state){
            printMenu();
            System.out.print("Pilihan : ");
            int pilihanUser; //inisialisasi variabel pilihanUser
            try { // validasi jika pilihanUser bukanlah integer
                pilihanUser = Integer.parseInt(input.nextLine().trim());
            }catch(NumberFormatException e){
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                continue;
            }
            if (pilihanUser==0){ //conditionals jika user ingin keluar dari program
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                state = false;
                input.close();
            } else if (pilihanUser==1) { //conditionals apabila user memilih generate nota
                System.out.println("Masukkan nama Anda:");
                String namaUser = input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHp;
                while (true) { // validasi apabila nomorHp selain digit 0-9
                    nomorHp = input.nextLine();
                    if (nomorHp.trim().length() > 0 && nomorHp.trim().chars().allMatch(Character::isDigit)){ //setiap char dari string akan dicek apakah digit atau bukan
                        break;
                    }else {
                        System.out.println("Nomor hp hanya menerima digit");
                    }
                }
                System.out.println("ID Anda : " + generateId(namaUser,nomorHp)); // memanggil method generateId untuk membuat ID dan menampilkannya

            } else if (pilihanUser==2) { //conditionals apabila user memilih generate nota
                System.out.println("Masukkan nama Anda:");
                String namaUser = input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHp;
                while (true) { // validasi apabila nomorHp selain digit 0-9
                    nomorHp = input.nextLine().trim();
                    if (nomorHp.trim().length() > 0 && nomorHp.trim().chars().allMatch(Character::isDigit)) {
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
                        break; //user memilih salah satu dari 3 paket laundry
                    } else if (paketLaundry.trim().equals("?")) {
                        showPaket(); // menampilan daftar paket
                    }else{
                        System.out.println("Paket " + paketLaundry.trim() + " tidak diketahui"); // input user bukan termasuk salah satu dari ketiga paket
                        System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                    }
                }
                System.out.println("Masukkan berat cucian Anda [Kg]:");
                int beratCucian;
                while(true){
                    try{ // validasi apabila input bukan integer
                        beratCucian = input.nextInt();
                        input.nextLine();
                        if (beratCucian == 1){ //bila user menginput 1kg maka akan diubah menjadi 2kg
                            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                            beratCucian = 2;
                            break;
                        } else if (beratCucian <= 0) { //bila input user bukan merupakan bilangan asli
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        }else{break;}
                    }catch (InputMismatchException e){
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        input.nextLine();
                    }
                }
                System.out.println("Nota Laundry");
                System.out.println(generateNota(generateId(namaUser,nomorHp),paketLaundry,beratCucian,tanggalTerima));
                //memanggil method generateNota dan menampilkannya
            }else{ //conditionals apabila user menginput integer selain 0,1,2
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
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        String namaUser = nama.trim().toUpperCase();
        String nomorHpTrimmed = nomorHP.trim();
        String upperCaseName = namaUser;
        if (namaUser.contains(" ")){upperCaseName = namaUser.split(" ")[0];}
        String namaDanNomor = upperCaseName + "-" + nomorHpTrimmed;
        int angkaChecksum = 0; //starting point untuk menghitung angka checksum
        for (int i = 0; i<namaDanNomor.length();i++){ //for loop untuk menjumlahkan checksum berdasarkan jenis character
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
        String checksumStr; //inisialisasi variabel output dan checksumStr
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
        String paketTrimmed = paket.trim();
        String tanggalTerimaTrimmed = tanggalTerima.trim();

        int hargaPerPaket = 0;
        int lamaLaundry = 0;
        //conditionals paket laundry
        if (paketTrimmed.equalsIgnoreCase("express")){hargaPerPaket = 12000; lamaLaundry = 1;}
        else if (paketTrimmed.equalsIgnoreCase("fast")) {hargaPerPaket = 10000;lamaLaundry = 2;}
        else if (paketTrimmed.equalsIgnoreCase("reguler")) {hargaPerPaket = 7000;lamaLaundry = 3;}
        int harga = hargaPerPaket * berat;

        // Membuat objek LocalDate dari sebuah string yang merepresentasikan tanggal
        LocalDate tanggalAwal = LocalDate.parse(tanggalTerimaTrimmed, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // Menambahkan jumlah hari sesuai lama paket laundry ke tanggal awal untuk mendapatkan tanggal akhir
        LocalDate tanggalAkhir = tanggalAwal.plusDays(lamaLaundry);
        // Mengonversi objek LocalDate menjadi string dengan pola "dd/MM/yyyy" dan menyimpannya di variabel tanggalSelesai
        String  tanggalSelesai = tanggalAkhir.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // membuat string output

        return String.format("""
                ID    : %s
                Paket : %s
                Harga :
                %d kg x %d = %d
                Tanggal Terima  : %s
                Tanggal Selesai : %s""",id,paket,berat,hargaPerPaket,harga,tanggalTerima,tanggalSelesai);
    }
}
