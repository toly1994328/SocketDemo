package com.toly1994.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/8 0008:10:19
 * 邮箱：1981462002@qq.com
 * 说明：
 * <p>
 * 源：socket读取流。
 * 目的：socket输出流。
 * 都是文本，装饰。
 */
public class TransServer {
    public static void main(String[] args) {
        try {
            //1.创建ServerSocket服务对象，并指定服务端口
            ServerSocket serverSocket = new ServerSocket(8080);
            //2.通过accept方法获取Socket对象
            Socket socket = serverSocket.accept();
            String ip = socket.getInetAddress().getHostAddress();
            System.out.println(ip + "....connected");//日志：打印连接的客户端，
            //3.获得socket对象的字节输入流，并转化为字符流,包装成BufferedReader----用于读取客户端数据
            BufferedReader brIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //4.获得socket对象的字节输出流,并包装成PrintWriter----用于发送给客户端数据
            PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
            String line = null;
            while ((line = brIn.readLine()) != null) {
                pwOut.println(line.toUpperCase());//将读到的数据转为大写，写出到客户端
                System.out.println(ip + ":" + line.toUpperCase());//日志：将读到的数据转为大写，打印出来
            }
            //5.关闭资源
            serverSocket.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
