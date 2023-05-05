package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        //mengubah tanggal dan mengiterate semua nota kemudian memanggil method toNextDay() yang ada di class Nota
        cal.add(Calendar.DAY_OF_MONTH, 1);
        for (Nota nota : notaList){
            nota.toNextDay();
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        // memanjangkan notaList kemudian assign nota baru di array tersebut
        Nota[] lstDummy = Arrays.copyOf(notaList, notaList.length+1);
        lstDummy[lstDummy.length-1] = nota;
        notaList = lstDummy;
    }
}
