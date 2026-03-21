package lk.ijse.eca.orderservice.exception;

public class JobOrderNotFoundException extends RuntimeException {
    public JobOrderNotFoundException(String id) {
        super("JobOrder with ID '" + id + "' not found");
    }
}
