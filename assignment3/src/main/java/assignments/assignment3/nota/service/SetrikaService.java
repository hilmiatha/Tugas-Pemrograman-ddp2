package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private Boolean status = false;
    @Override
    public String doWork() {
        // TODO
        this.status = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        return status;
    }

    @Override
    public long getHarga(int berat) {
        return berat * 1000L;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
