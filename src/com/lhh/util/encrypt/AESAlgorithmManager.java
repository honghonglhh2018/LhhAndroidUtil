package com.lhh.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESAlgorithmManager {

    public final static String ENCODING = "UTF-8";

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 生成密钥
     * 自动生成base64 编码后的AES128位密钥
     *
     * @throws //NoSuchAlgorithmException
     * @throws //UnsupportedEncodingException
     */
    public static String getAESKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        return parseByte2HexStr(b);
    }

    /**
     * AES 加密
     *
     * @param base64Key base64编码后的 AES key
     * @param text      待加密的字符串
     * @return 加密后的byte[] 数组
     * @throws Exception
     */
    public static byte[] getAESEncode(String base64Key, String text) throws Exception {
        byte[] key = parseHexStr2Byte(base64Key);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
        byte[] bjiamihou = cipher.doFinal(text.getBytes(ENCODING));
        return bjiamihou;
    }

    /**
     * AES解密
     *
     * @param base64Key base64编码后的 AES key
     * @param text      待解密的字符串
     * @return 解密后的byte[] 数组
     * @throws Exception
     */
    public static byte[] getAESDecode(String base64Key, byte[] text) throws Exception {
        byte[] key = parseHexStr2Byte(base64Key);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
        byte[] bjiemihou = cipher.doFinal(text);
        return bjiemihou;
    }

    public static byte[] hexString2Bytes(String hexString) {
        if (isSpace(hexString))
            return null;
        int len = hexString.length();
        if (len % 2 != 0) {
            hexString = "0" + hexString;
            len = len + 1;
        }
        char[] hexBytes = hexString.toUpperCase().toCharArray();
        byte[] ret = new byte[len >> 1];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) (hex2Int(hexBytes[i]) << 4 | hex2Int(hexBytes[i + 1]));
        }
        return ret;
    }

    private static boolean isSpace(final String s) {
        if (s == null)
            return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int hex2Int(final char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return hexChar - 'A' + 10;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String bytes2HexString(byte[] bt) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bt == null || bt.length <= 0) {
            return null;
        }
        for (int i = 0; i < bt.length; i++) {
            int v = bt[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
