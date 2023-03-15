package assignments.assignment2;

public class Member {

    //attributes class Member
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter = -1; //starting point untuk diskon member

    public Member(String nama, String noHp, String id) {        // constructor untuk class Member
        this.nama = nama;
        this.noHp = noHp;
        this.id = id;
    }

    //methods yang diperlukan untuk class Member

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
