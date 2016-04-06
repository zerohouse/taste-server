package at.begin.web.exception;

public class SendErrorMessage extends RuntimeException {
    public SendErrorMessage(String message) {
        super(message);
    }
}
