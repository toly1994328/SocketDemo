package com.toly1994.upload.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/8 0008:11:50
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class UpLoadFileServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            while (true) {
                Socket socket = serverSocket.accept();
                String ip = socket.getInetAddress().getHostAddress();
                System.out.println(ip + "....connected");

                InputStream is = socket.getInputStream();
                String fileName = "F:\\ip" + ip + "-" + rangeInt(3000, 10000) + ".jpg";

                FileOutputStream fos = new FileOutputStream(fileName);
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }

                OutputStream os = socket.getOutputStream();
                os.write("上传成功".getBytes());

                fos.close();
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取范围随机整数：如 rangeInt(1,9)
     *
     * @param s 前数(包括)
     * @param e 后数(包括)
     * @return 范围随机整数
     */
    public static int rangeInt(int s, int e) {
        int max = Math.max(s, e);
        int min = Math.min(s, e) - 1;
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }
}
