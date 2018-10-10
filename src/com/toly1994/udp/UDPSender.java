package com.toly1994.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/6 0006:21:06
 * 邮箱：1981462002@qq.com
 * 说明：
 * <p>
 * 1---通过DatagramSocket创建对象：端口8081(此端口随意)
 * 2---使用DatagramPacket对象打包数据
 * 3---使用DatagramSocket对象发送数据包(字节数组,发送长度,ip,端口)
 * 4---关闭DatagramSocket对象
 */
public class UDPSender {
    public static void main(String[] args) {
        System.out.println("这是发送端");
        try {
            //1: 通过DatagramSocket对象创建updSocket服务：端口8081(此端口随意)
            DatagramSocket datagramSocket = new DatagramSocket(8081);
            //2: 使用DatagramPacket对象打包数据
            byte[] buf = "土豆土豆,我是地瓜".getBytes();
            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.56.1"), 8080);
            //3：使用DatagramSocket对象发送数据包(字节数组,发送长度,ip,端口)
            datagramSocket.send(dp);
            //4: 关闭DatagramSocket对象
            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
