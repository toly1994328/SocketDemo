package com.toly1994.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/6 0006:21:06
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class UDPServerWithInput {
    //255,代表向该网段接收端发送 192.168.56.1~192.168.56.255 都能接收到
    public static final String IP="192.168.56.1";
    public static void main(String[] args) throws IOException {
        System.out.println("这是发送端");
        //1: 通过DatagramSocket对象创建updSocket服务：端口8081(此端口随意)
        DatagramSocket datagramSocket = new DatagramSocket(8081);
        //准备键盘录入字符读取流
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = br.readLine()) != null) {
            if ("886".equals(line)) {
                break;
            }
            byte[] buf = line.getBytes();
            //2：使用DatagramPacket对象打包数据
            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName(IP), 8080);
            //3：使用DatagramSocket对象发送数据包(字节数组,发送长度,ip,端口)
            datagramSocket.send(dp);
        }
        //4：关闭DatagramSocket对象
        datagramSocket.close();
    }
}
