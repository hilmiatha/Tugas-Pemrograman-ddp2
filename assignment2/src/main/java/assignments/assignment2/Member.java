package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {

    //attributes class ini
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter = -1;

    public Member(String nama, String noHp, String id) {
        // constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
        this.id = id;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getId() {
        return id;
    }

    public int getBonusCounter() {
        return bonusCounter;
    }

    public void setBonusCounter(int bonusCounter) {
        this.bonusCounter = bonusCounter;
    }
}
