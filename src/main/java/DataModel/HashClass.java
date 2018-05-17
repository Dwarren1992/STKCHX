package DataModel;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;

public class HashClass {

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes("UTF-8"));

            return String.format("%064x", new java.math.BigInteger(1, digest.digest()));
        } catch (Exception ex) {
            return null;
        }
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        String salt = new BigInteger(130, random).toString(32);

        return salt;
    }

    public static HashMap<String, String> saltAndHashPassword(String password) {
        HashMap<String, String> map = new HashMap<>();

        String salt = generateSalt();
        password = hashPassword(password + salt);

        map.put("password", password);
        map.put("salt", salt);

        return map;
    }
}