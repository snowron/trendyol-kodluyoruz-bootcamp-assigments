package Exceptions;

public class BillDateMustBeBiggerThanOneMonth extends RuntimeException {
    public BillDateMustBeBiggerThanOneMonth(String message) {
        super(message);
    }
}
