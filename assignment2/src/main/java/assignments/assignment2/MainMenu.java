package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<>();
    private static ArrayList<Member> memberList = new ArrayList<>();

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
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

    private static void handleGenerateUser() {
        //handle generate user
        System.out.println("Masukkan nama anda :");
        String nama = input.nextLine();
        System.out.println("Masukan nomor handphone Anda:");
        String nomorHp;
        while (true) { // validasi apabila nomorHp selain digit 0-9
            nomorHp = input.nextLine().trim();
            if (nomorHp.trim().length() > 0 && nomorHp.trim().chars().allMatch(Character::isDigit)){
                break;
            }else {
                System.out.println("Field nomor hp hanya menerima digit");
            }
        }
        String id = generateId(nama,nomorHp);
        for (Member member : memberList){
            if (member.getId().equals(id)){
                System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!%n",member.getNama(),member.getNoHp());
                return;
            }
        }
        Member user = new Member(nama,nomorHp,id);
        memberList.add(user);
        System.out.printf("Berhasil membuat member dengan ID %s!%n", id);

    }

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian
        System.out.println("Masukan ID member:");
        String id = input.nextLine();
        Member member = cekMember(id);
        if (member != null){
            String paketLaundry;
            int hariKerja;
            while (true) {
                System.out.println("Masukkan paket laundry :");
                paketLaundry = input.nextLine();
                if (paketLaundry.trim().equalsIgnoreCase("express")){
                    hariKerja = 1;
                    break; //user memilih salah satu dari 3 paket laundry
                } else if (paketLaundry.trim().equalsIgnoreCase("fast")) {
                    hariKerja = 2;
                    break; //user memilih salah satu dari 3 paket laundry
                } else if (paketLaundry.trim().equalsIgnoreCase("reguler")) {
                    hariKerja = 3;
                    break; //user memilih salah satu dari 3 paket laundry
                }else if (paketLaundry.trim().equals("?")) {
                    showPaket();
                }else {
                    System.out.println("Paket " + paketLaundry.trim() + " tidak diketahui"); // input user bukan termasuk salah satu dari ketiga paket
                }
            }

            System.out.println("Masukkan berat cucian Anda [Kg]:");
            long beratCucian;
            while(true){
                try{ // validasi apabila input bukan integer
                    beratCucian = input.nextLong();
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
            Nota nota = new Nota(member,paketLaundry.trim(),beratCucian,fmt.format(cal.getTime()).trim(),hariKerja);
            notaList.add(nota);
            System.out.println("Berhasil menambahkan nota!");
            System.out.println(nota.outputNota());


        }else{ //kondisi apabila id belum terdaftar di sistem
            System.out.printf("Member dengan ID %s tidak ditemukan!%n", id);
        }

    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        if (notaList.size() == 0){
            System.out.println("Terdaftar 0 nota dalam sistem.");
        }else{
            System.out.printf("Terdaftar %d nota dalam sistem.%n", notaList.size());
            for (Nota nota : notaList){
                System.out.printf("- [%d] Status      : %s%n",nota.getIdNota(),nota.getStatusReady());
            }
        }
    }

    private static void handleListUser() {
        //handle list semua user pada sistem
        if (memberList.size() == 0){
            System.out.println("Terdaftar 0 member dalam sistem.");
        }else{
            System.out.printf("Terdaftar %d member dalam sistem.%n",memberList.size());
            for (Member member:memberList){
                System.out.printf("- %s : %s%n",member.getId(),member.getNama());
            }
        }
    }

    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        System.out.println("Dek Depe tidur hari ini... zzz...");
        cal.add(Calendar.DAY_OF_MONTH, 1);
        for (Nota nota : notaList){
            nota.setSisaHariPengerjaan(nota.getSisaHariPengerjaan()-1);

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
    private static Member cekMember(String id){
        for (Member member : memberList){
            if (id.equals(member.getId())){
                return member;
            }
        }
        return null;
    }

}
