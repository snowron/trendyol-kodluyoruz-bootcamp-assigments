package Exceptions;

public class HasNoRequest extends RuntimeException{
    public HasNoRequest(String message) {
        super(message);
    }
}
