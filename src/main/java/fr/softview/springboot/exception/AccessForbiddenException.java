package fr.softview.springboot.exception;

/**
 * Created by Samba Kamara on 21/11/2017.
 */

public class AccessForbiddenException extends RuntimeException{

    private static final long serialVersionUID = 254544514866231L;

    public AccessForbiddenException(String message) {
        super (message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
