
package com.assignment.zolostays.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Rashmi on 13/08/17.
 */

public class PasswordUtils {

    public static String generateHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean checkIfEqual(String hash, String input) {
        return generateHash(input).equals(hash);
    }
}
