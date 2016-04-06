package at.begin.web.exception;

public class SendWarningMessage extends RuntimeException {
    public SendWarningMessage(String message) {
        super(message);
    }
}
