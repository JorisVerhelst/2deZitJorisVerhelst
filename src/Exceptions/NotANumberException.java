
package Exceptions;

public class NotANumberException extends Exception{
        
    public NotANumberException() {
    }

    public NotANumberException(String message) {
        super(message);
    }

    public NotANumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotANumberException(Throwable cause) {
        super(cause);
    }
    
}
