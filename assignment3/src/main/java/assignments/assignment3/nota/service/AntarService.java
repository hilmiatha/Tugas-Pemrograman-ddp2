package assignments.assignment3.nota.service;

public class AntarService implements LaundryService{
    private Boolean status = false;
    @Override
    public String doWork() {
        // TODO
        this.status = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return status;
    }

    @Override
    public long getHarga(int berat) {
        if (berat<=4){
            return 2000L;
        }
        int dummy = berat - 4;
        return 2000 + (dummy * 500L);
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
