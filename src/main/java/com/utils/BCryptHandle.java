package com.utils;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptHandle {
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    public static Boolean verify(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
