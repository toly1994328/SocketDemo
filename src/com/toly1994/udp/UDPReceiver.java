package com.toly1994.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/6 0006:21:06
 * 邮箱：1981462002@qq.com
 * 说明：
 * <p>
 * 需求：
 * 定义一个应用程序，用于接收udp协议传输的数据并处理的。
 * <p>
 * 定义udp的接收端,思路：
 * 1---创建DatagramSocket对象,必须监听一个端口。(给网络应用定义数字标识--便于该应用程序处理传来数据过来)
 * 2---创建一个DatagramPacket对象,存储接收到的字节数据
 * 3---DatagramSocket对象的接收方法，将DatagramPacket对象传入
 * 4---通过DatagramPacket对象获取发送端传来的数据
 * 5---关闭DatagramSocket对象。
 */

public class UDPReceiver {
    public static void main(String[] args) throws Exception {
        System.out.println("这是接收端");
        //1：创建DatagramSocket对象,必须监听一个端口。
        DatagramSocket ds = new DatagramSocket(8080);
        while (true) {
            //2：创建一个DatagramPacket对象,存储接收到的字节数据
            DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
            //3:通过服务的receive方法将收到数据存入数据包中。
            ds.receive(dp);//阻塞式方法。
            //4:通过DatagramPacket对象获取发送端传来的数据
            String data = new String(dp.getData(), 0, dp.getLength());
            String ip = dp.getAddress().getHostAddress();
            int port = dp.getPort();
            System.out.println("来自" + ip + ":" + port + ":" + data);
        }
        //5:关闭DatagramSocket对象。
        //ds.close();
    }
}
