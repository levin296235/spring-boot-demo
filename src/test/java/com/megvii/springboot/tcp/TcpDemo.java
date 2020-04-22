package com.megvii.springboot.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TcpDemo {

    public static void convertDataForm(){
        //补充:十进制数字转换二进制、八进制和16进制字符串的方法:
        System.out.println(Integer.toBinaryString(25));// 转换为二进制字符串
        System.out.println(Integer.toOctalString(25));// 转换为8进制字符串
        System.out.println(Integer.toHexString(25));// 转换为16进制字符串

        //字符串按照进制转换为十进制数的方法:
        System.out.println(Integer.parseInt("11001", 2));// 二进制字符串转换十进制数
        System.out.println(Integer.parseInt("31", 8));// 8进制字符串转换十进制数
        System.out.println(Integer.parseInt("19", 16));// 16进制字符串转换十进制数
    }

    public void byteToHex(){
        //byte转16进制的字符串
        byte b = -64;
        int intNum2 = b > 0 ? b : b + 256;
        String string = Integer.toString(intNum2, 16);
        System.out.println(string);

        //16转byte字符串  byte型数据时，会产生一个-128~127的有符号字节
        int intNum = 192;
        byte byteNum = (byte) intNum;
        int intNum3 = byteNum > 0 ? byteNum : byteNum + 256;
        System.out.println(intNum);
        System.out.println(byteNum);
        System.out.println(intNum3);
    }

    public void tcpTest() throws IOException {
        // 1:建立服务器端的tcp socket服务,必须监听一个端口
        ServerSocket ss = new ServerSocket(24992);
        // 2: 通过服务器端的socket对象的accept方法获取连接上的客户端对象
        Socket s = null;
        // 3：获取客户端的数据
        while (true) {
            // 接受Socket服务,如果有,没有则堵塞,等待
            s = ss.accept();
            System.out.println("accept success.......");
            try {
                // 从Socekt输入流中获取客户端发送过来的输出流
                InputStream in = s.getInputStream();
                byte[] buf = new byte[1024];
                int len = in.read(buf);
                System.out.println("从客户端传送来的数据如下:");
                System.out.println(Arrays.toString(buf));

                // 通过服务器端Socket输出流,写数据,会传送到客户端Socket输入流中
                OutputStream out = s.getOutputStream();
                String retunStr = "C0 01 01 03 FF 00 C0";
                out.write(SocketUtils.hexStrToByteArrs(retunStr));
            } catch (Exception e) {
                System.out.println("error");
            } finally {
                s.close();
            }
        }
    }

    public static void main(String[] args) {
        convertDataForm();
    }

}
