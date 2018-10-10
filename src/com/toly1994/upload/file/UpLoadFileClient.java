package com.toly1994.upload.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/8 0008:11:50
 * 邮箱：1981462002@qq.com
 * 说明：
 * 1，服务端点。
 * 2，读取客户端已有的图片数据。
 * 3，通过socket 输出流将数据发给服务端。
 * 4，读取服务端反馈信息。
 * 5，关闭。
 */
public class UpLoadFileClient {
    public static void main(String[] args) {
        String ip = "192.168.56.1";
//        String ip = "193.112.165.148";
        int port = 8080;
        try {
            Socket socket = new Socket(ip, port);

            String path = "C:\\Users\\Administrator\\Desktop\\数据结构.jpg";

            FileInputStream fis = new FileInputStream(path);
            OutputStream os = socket.getOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            //告诉服务端数据已写完
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            byte[] bufIn = new byte[1024];
            int num = is.read(bufIn);
            System.out.println(new String(bufIn, 0, num));

            fis.close();
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
