package com.toly1994.upload.txt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/8 0008:11:50
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class UpLoadServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            Socket socket = serverSocket.accept();
            String ip = socket.getInetAddress().getHostAddress();
            System.out.println(ip + "....connected");

            BufferedReader brIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pwOut = new PrintWriter(new FileWriter("F:\\service.txt"));

            //获取第一行结束标识
            String mark = brIn.readLine();

            String line = null;
            while ((line = brIn.readLine()) != null) {
                if (mark.equals(line)) {
                    break;
                }
                pwOut.println(line);
            }

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("上传成功");

            pwOut.close();
            serverSocket.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
