package fr.softview.springboot.exception;

/**
 * Created by sambakamara on 19/11/2017.
 */
public class AccountException  extends RuntimeException  {

    private static final long serialVersionUID = 254544514866231L;

    public AccountException(String message) {
        super (message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
