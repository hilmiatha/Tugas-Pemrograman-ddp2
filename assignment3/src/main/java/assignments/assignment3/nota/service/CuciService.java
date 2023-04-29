package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    private Boolean status = false;
    @Override
    public String doWork() {
        // TODO
        this.status = true;
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return status;
    }

    @Override
    public long getHarga(int berat) {
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
