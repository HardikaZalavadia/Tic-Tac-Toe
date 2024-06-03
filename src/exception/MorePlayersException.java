package exception;

public class MorePlayersException extends RuntimeException{
    public MorePlayersException(String message) {
        super(message);
    }
}
