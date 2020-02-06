package util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHAUtil {

    public static final String KEY_SHA = "SHA";

    public static String getResult(String str) {

        if (str == null || "".equals(str)){
            return null;
        }

        BigInteger sha = null;
        byte[] inputData = str.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sha.toString(32);
    }

}
