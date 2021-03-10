package cn.misection.miscourse.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{
    // md5 加密方法
    public static String md5(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : result) {
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1) {
                    sb.append("0" + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
