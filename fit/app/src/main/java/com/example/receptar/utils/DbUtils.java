package com.example.receptar.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * class containing utility methods for database
 */
public class DbUtils {

    /**
     * method to encrypt password
     * <p>
     * algorithm source https://www.tutorialspoint.com/how-to-encrypt-password-and-store-in-android-sqlite
     */
    public static String passwordEncrypt(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                StringBuilder h = new StringBuilder(Integer.toHexString(0xFF & aMessageDigest));
                while (h.length() < 2) {
                    h.insert(0, "0");
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ignored) {
        }
        return "";
    }

}
