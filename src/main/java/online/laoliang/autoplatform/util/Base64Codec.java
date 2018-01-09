package online.laoliang.autoplatform.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Codec {

    /**
     * 对传入的字符进行 Base64 编码
     *
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encoder(String s) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }

    /**
     * 对传入的字符进行 Base64 解码
     *
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decoder(String s) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(s), "UTF-8");
    }
}
