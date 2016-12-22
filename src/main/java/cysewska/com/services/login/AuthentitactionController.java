package cysewska.com.services.login;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * Created by Ola on 2016-11-08.
 */
@Component
public class AuthentitactionController {

    private static int workload = 12;

    public  boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;
        if(null == stored_hash)
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
        return(password_verified);
    }

}
