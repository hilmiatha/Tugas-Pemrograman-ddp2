package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount = 0;
    public int nomorKaryawan;
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password);
        this.nomorKaryawan = employeeCount;
        employeeCount++;
    }

    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */
    private static String generateId(String nama) {
        // generate id untuk employee berbentuk NAMA-NOMORID
        String namaDepan;
        if (nama.contains(" ")){namaDepan = nama.split(" ")[0].toUpperCase();}
        else{namaDepan = nama.toUpperCase();}
        return String.format("%s-%d",namaDepan, employeeCount);
    }
}
