package uz.abbos.resumebuilder.exception;

public class BadRequestHandler extends RuntimeException{
    public BadRequestHandler(String message) {
        super(message);
    }
}
