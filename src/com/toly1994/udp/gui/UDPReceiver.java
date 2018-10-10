package com.toly1994.udp.gui;

import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/7 0007:11:30
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class UDPReceiver {
    private JPanel mPanel1;
    private JTextArea mTextArea1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("接收端");
        frame.setSize(400, 400);
        frame.setLocation(300, 200);

        UDPReceiver client = new UDPReceiver();
        frame.setContentPane(client.mPanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        StringBuilder sb = new StringBuilder();
        //1：创建DatagramSocket对象,必须监听一个端口。
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(8080);
            while (true) {
                //2：创建一个DatagramPacket对象,存储接收到的字节数据
                DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
                //3:通过服务的receive方法将收到数据存入数据包中。
                ds.receive(dp);//阻塞式方法。
                //4:通过DatagramPacket对象获取发送端传来的数据
                String ip = dp.getAddress().getHostAddress();
                String data = new String(dp.getData(), 0, dp.getLength());
                int port = dp.getPort();
                sb.append("来自" + ip + ":" + port + ":" + data+"\n");
                client.mTextArea1.setText(sb.toString());
                System.out.println(sb.toString());
            }
            //5:关闭DatagramSocket对象。
            //ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
