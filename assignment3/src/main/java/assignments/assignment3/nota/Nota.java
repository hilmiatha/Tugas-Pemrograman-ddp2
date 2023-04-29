package assignments.assignment3.nota;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService[] {new CuciService()};
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private boolean isDone = false;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggal;
        this.sisaHariPengerjaan = toHariKerja(paket);
        this.id = totalNota;
        this.baseHarga = toBaseHarga(paket);
        totalNota++;
    }

    public void addService(LaundryService service) {
        //TODO
        LaundryService[] lstDummy = Arrays.copyOf(services, services.length + 1);
        lstDummy[lstDummy.length - 1] = service;
        services = lstDummy;
    }

    public String kerjakan() {
        // TODO
        for (LaundryService service : services){
            if (!service.isDone()){
                String output = service.doWork();
                if (cekProgress()){
                    this.isDone = true;
                }
                return String.format("Nota %d : %s", getId(), output);
            }
        }
        return String.format("Nota %d : Sudah selesai.", getId());
    }

    public void toNextDay() {
        // TODO
        System.out.println("sebelum " + sisaHariPengerjaan);
        if (!isDone()){
            sisaHariPengerjaan-=1;
        }
        System.out.println("sesudah "+sisaHariPengerjaan);
    }

    public long calculateHarga() {
        // TODO
        long totalHarga = getBaseHarga() * getBerat();
        for (LaundryService service : services){
            totalHarga += service.getHarga(getBerat());
        }
        if (sisaHariPengerjaan < 0){
            totalHarga += (sisaHariPengerjaan * 2000L);
        }
        if (totalHarga < 0){
            totalHarga = 0;
        }
        return totalHarga;
    }

    public String getNotaStatus() {
        // TODO
        if (isDone()) {
            return String.format("Nota %d : Sudah selesai.",getId());
        }
        return String.format("Nota %d : Belum selesai.",getId());
    }

    @Override
    public String toString() {
        // TODO
        String output = String.format("""
                [ID Nota = %d]
                ID    : %s
                Paket : %s
                Harga :
                %d kg x %d = %d
                tanggal terima  : %s
                tanggal selesai : %s
                --- SERVICE LIST ---
                -Cuci @ Rp.0
                """, getId(),getMember().getId(),getPaket(),getBerat(),getBaseHarga(),(getBaseHarga() * getBerat()),getTanggal(),getTanggalSelesai());
        for (LaundryService service : services){
            if (service.getServiceName().equals("Setrika")){
                output += String.format("-%s @ Rp.%d%n",service.getServiceName(),service.getHarga(getBerat()));
            } else if (service.getServiceName().equals("Antar")) {
                output += String.format("-%s @ Rp.%d%n",service.getServiceName(),service.getHarga(getBerat()));
            }
        }
        if (getSisaHariPengerjaan()<0){
            output += String.format("Harga Akhir: %d Ada kompensasi keterlambatan %d * 2000 hari",calculateHarga(),(-getSisaHariPengerjaan()));
        }else{
            output += String.format("Harga Akhir: %d",calculateHarga());
        }
        return output;
    }

    /*
    fungsi untuk return integer lama hari pengerjaan sesuai dengan paket yang diinput
     */
    public int toHariKerja(String paket) {
        paket = paket.toLowerCase();
        if (paket.equalsIgnoreCase("express"))
            return 1;
        if (paket.equalsIgnoreCase("fast"))
            return 2;
        if (paket.equalsIgnoreCase("reguler"))
            return 3;
        return -1;
    }
    /*
    fungsi untuk return integer harga paket sesuai dengan paket yang diinput
     */
    public int toBaseHarga(String paket){
        paket = paket.toLowerCase();
        if (paket.equalsIgnoreCase("express"))
            return 12000;
        if (paket.equalsIgnoreCase("fast"))
            return 10000;
        if (paket.equalsIgnoreCase("reguler"))
            return 7000;
        return -1;
    }

    public Boolean cekProgress(){
        for (LaundryService service : services){
            if (!service.isDone()){
                return false;
            }
        }
        return true;
    }
    public String getTanggalSelesai(){
        // Membuat objek LocalDate dari sebuah string yang merepresentasikan tanggal
        LocalDate tanggalAwal = LocalDate.parse(getTanggal(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // Menambahkan jumlah hari sesuai lama paket laundry ke tanggal awal untuk mendapatkan tanggal akhir
        LocalDate tanggalAkhir = tanggalAwal.plusDays(toHariKerja(getPaket()));
        // Mengonversi objek LocalDate menjadi string dengan pola "dd/MM/yyyy" dan menyimpannya di variabel tanggalSelesai
        return tanggalAkhir.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }

    public int getId() {return id;}

    public Member getMember() {return member;}

    public long getBaseHarga() {return baseHarga;}

}
