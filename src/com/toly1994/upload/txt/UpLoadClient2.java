package com.toly1994.upload.txt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/8 0008:11:50
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class UpLoadClient2 {
    public static void main(String[] args) {
        String ip = "192.168.56.1";
        int port = 8080;

        try {
            Socket socket = new Socket(ip, port);

            String path = "I:\\Java\\Base\\Thinking\\src\\1.txt";
            BufferedReader br = new BufferedReader(new FileReader(path));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            String line = null;

            while ((line = br.readLine()) != null) {
                pw.println(line);
            }
            ////关闭客户端的输出流。相当于给流中加入一个结束标记-1.
            socket.shutdownOutput();

            BufferedReader brIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = brIn.readLine();
            System.out.println(str);
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
