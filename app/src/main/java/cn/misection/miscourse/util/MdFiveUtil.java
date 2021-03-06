package cn.misection.miscourse.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.misection.miscourse.constant.global.EnumCommonString;

/**
 * @author Administrator
 */
public class MdFiveUtil {
    /**
     * md5 加密方法;
     *
     * @param text
     * @return
     */
    public static String md5(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : result) {
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                switch (hex.length()) {
                    case 1: {
                        builder.append(String.format("0%s", hex));
                        break;
                    }
                    default: {
                        builder.append(hex);
                        break;
                    }
                }
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return EnumCommonString.EMPTY.value();
        }
    }
}
