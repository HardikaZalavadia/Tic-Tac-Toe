package exception;

public class MoreThanOneBotException extends RuntimeException{
    public MoreThanOneBotException(String message) {
        super(message);
    }
}
