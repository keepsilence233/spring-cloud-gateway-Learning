package qx.leizige.verification.dto;

import java.io.Serializable;

public class Hello implements Serializable {

    String message;

    public Hello() {
    }

    public Hello(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
