package com.piotrgrochowiecki.eriderent_identity_provider.configuration;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

public class PasswordUtils {

    private static String hashPassword(String password) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(password.getBytes());
        return Hex.toHexString(digest);
    }

    public static boolean isPasswordValid(String inputPassword, String hashedPassword) {
        String hashedInputPassword = hashPassword(inputPassword);
        return hashedInputPassword.equals(hashedPassword);
    }

}
