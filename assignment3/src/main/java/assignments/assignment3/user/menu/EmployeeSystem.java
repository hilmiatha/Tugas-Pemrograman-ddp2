package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO:
        if (choice == 1){
            //iterate semua nota yang ada kemudian melakukan method kerjakan() yang ada pada class Nota
            System.out.printf("Stand back! %s beginning to nyuci!%n", loginMember.getNama());
            for (Nota nota : notaList){
                System.out.println(nota.kerjakan());
            }
        } else if (choice == 2) {
            //iterate semua nota kemudian memanggil method getNotaStatus() yang ada di class Nota
            for (Nota nota : notaList){
                System.out.println(nota.getNotaStatus());
            }
        } else if (choice == 3) {
            //mengubah boolean logout agar user bisa keluar dari menu Employee
            logout = true;
        }else{
            System.out.println("Perintah tidak diketahui!");
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}