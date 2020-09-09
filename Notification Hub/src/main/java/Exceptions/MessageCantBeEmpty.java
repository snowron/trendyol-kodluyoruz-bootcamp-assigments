package Exceptions;

public class MessageCantBeEmpty extends RuntimeException{

    public MessageCantBeEmpty(String message) {
        super(message);
    }
}
