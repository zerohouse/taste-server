package at.begin.infra.exception;

public class SendErrorMessage extends RuntimeException {
    public SendErrorMessage(String message) {
        super(message);
    }
}
