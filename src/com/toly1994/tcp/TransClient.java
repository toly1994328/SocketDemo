package com.toly1994.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/8 0008:10:19
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class TransClient {
    public static void main(String[] args) {
        String ip = "192.168.56.1";
        int port = 8080;
        try {
            //1.创建Socket对象(ip,端口)
            Socket socket = new Socket(ip, port);
            //准备键盘录入字符读取流
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //3.获得socket对象的字节输入流，并转化为字符流,包装成BufferedReader----用于读取服务端数据
            BufferedReader brIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //4.获得socket对象的字节输出流,并包装成PrintWriter----用于发送给服务端数据
            PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
            //注意这三个流的区别与作用：br--键盘录入  brIn---读取服务端数据  pwOut--发送给服务端数据
            String line = null;
            while ((line = br.readLine()) != null) {
                if ("over".equals(line)) {
                    break;
                }
                pwOut.println(line);//将键盘输入内容发送给服务端
                System.out.println("服务端：" + brIn.readLine());//读取服务端的数据，并打印出来
            }
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
