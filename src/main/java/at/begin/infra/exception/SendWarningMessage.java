package at.begin.infra.exception;

public class SendWarningMessage extends RuntimeException {
    public SendWarningMessage(String message) {
        super(message);
    }
}
