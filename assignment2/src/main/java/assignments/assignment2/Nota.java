package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {
    // Attributes kelas nota
    private static int objectCount = 0;
    private int idNota;
    private String paket;
    private Member member;
    private long berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private Boolean isReady;

    public Nota(Member member, String paket, long berat, String tanggalMasuk, int sisaHariPengerjaan) {
        //constructor untuk class ini
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.sisaHariPengerjaan = sisaHariPengerjaan;
        this.member.setBonusCounter(this.member.getBonusCounter()+1);
        this.idNota = objectCount;
        objectCount++;


    }

    //TODO: methods class ini

    public int getIdNota() {
        return idNota;
    }


    public String getPaket() {
        return paket;
    }
    public int getHargaPaket(){
        int harga = 0;
        if (this.getPaket().equalsIgnoreCase("Express")){
            harga = 12000;
        } else if (this.getPaket().equalsIgnoreCase("Fast")) {
            harga = 10000;
        } else if (this.getPaket().equalsIgnoreCase("Reguler")) {
            harga = 7000;
        }
        return harga;
    }


    public Member getMember() {
        return member;
    }


    public long getBerat() {
        return berat;
    }


    public String getTanggalMasuk() {
        return tanggalMasuk;
    }



    public int getSisaHariPengerjaan() {
        return sisaHariPengerjaan;
    }

    public void setSisaHariPengerjaan(int sisaHariPengerjaan) {
        this.sisaHariPengerjaan = sisaHariPengerjaan;
    }

    public Boolean getReady() { //TODO
//        MainMenu.fmt
        if (sisaHariPengerjaan <= 0){
            return true;
        }
        else {
            return false;
        }
    }
    public String outputNota(){
        long harga;
        String output;
        if ((this.getMember().getBonusCounter()+1) % 3 == 0){
            this.getMember().setBonusCounter(0);
            harga = this.getBerat()*this.getHargaPaket();
            output = String.format("""
                [ID Nota = %d]
                ID    : %s
                Paket : %s
                Harga :
                %d kg x %d = %d = %d (Discount member 50%%!!!)
                Tanggal Terima  : %s
                Tanggal Selesai : %s
                Status          : %s%n""",this.getIdNota(),this.getMember().getId(),this.getPaket(),this.getBerat(),this.getHargaPaket(),harga,(harga/2),this.getTanggalMasuk(),this.getTanggalSelesai(),this.getStatusReady());

        }else {
            harga = this.getBerat()*this.getHargaPaket();
            output = String.format("""
                [ID Nota = %d]
                ID    : %s
                Paket : %s
                Harga :
                %d kg x %d = %d
                Tanggal Terima  : %s
                Tanggal Selesai : %s
                Status          : %s%n""",this.getIdNota(),this.getMember().getId(),this.getPaket(),this.getBerat(),this.getHargaPaket(),harga,this.getTanggalMasuk(),this.getTanggalSelesai(),this.getStatusReady());
        }
        return output;
    }
    public String getTanggalSelesai(){
        LocalDate tanggalAwal = LocalDate.parse(this.getTanggalMasuk(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate tanggalAkhir = tanggalAwal.plusDays(this.getSisaHariPengerjaan());
        String  tanggalSelesai = tanggalAkhir.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return tanggalSelesai;
    }
    public String getStatusReady(){
        String output;
        if (getReady()){
            output = "Sudah dapat diambil!";
        }else {
            output = "Belum bisa diambil :(";
        }
        return output;
    }



}
