package com.megvii.springboot.entity;

/**
 * byte[]与16进制字符串相互转换
 *
 * @date：2017年4月10日 下午11:04:27
 */
public class BytesHexStrTranslate {

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * 方法一：
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun1(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for(byte b : bytes) { // 使用除与取余进行转换
            if(b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }

    /**
     * 方法二：
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun2(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for(byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

    /**
     * 方法三：
     * byte[] to hex string
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun3(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for(byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }
        return buf.toString();
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    /**
     * 将十六进制字符串转成十进制数组
     * 将每4个十六进制数(双字节)转成一个十进制数
     * 自动识别并处理FF字节后加00问题
     * @param str
     * @return
     */
    public static int[] doubleHexStrToIntArray(String str){
        int[] arr = new int[str.length()/4];
        String s1;
        int index = 0;
        for (int i = 0; i < arr.length;i++){
            for (int x = 1; x >= 0; x--) {

                if (index + 2 > str.length()) { return arr;}

                s1 = str.substring(index, index + 2);
                if (s1.equalsIgnoreCase("FF")) {
                    index += 4;
                } else {
                    index += 2;
                }
                arr[i] += (Integer.parseInt(s1,16) << (x * 8));
            }
//            arr[i] = Integer.parseInt(str.substring(i * 4, i * 4 + 4),16);
        }
        return arr;
    }

    /**
     * 将十六进制字符串转成十进制数组
     * 将每2个十六进制数(单字节)转成一个十进制数
     * 自动识别并处理FF字节后加00问题
     * @param str
     * @return
     */
    public static int[] singleHexStrToIntArray(String str){
        int[] arr = new int[str.length()/2];
        String s1;
        int index = 0;
        for (int i = 0; i < arr.length && (index + 2) <= str.length(); i++){
            s1 = str.substring(index, index + 2);
            if (s1.equalsIgnoreCase("FF")) {
                index += 4;
            } else {
                index += 2;
            }
            arr[i] += Integer.parseInt(s1,16);
        }
        return arr;
    }
    /**
     * 将16进制字符串转换为int[]
     *
     * @param data
     * @return
     */
    public static int[] toIntData(String data) {
        if(data == null || data.trim().equals("")) {
            return new int[0];
        }
        int[] bytes = new int[data.length() / 2];
        for(int i = 0; i < data.length() / 2; i++) {
            String subStr = data.substring(i * 2, i * 2 + 2);
            bytes[i] = Integer.parseInt(subStr, 16);
        }
        return bytes;
    }
}
