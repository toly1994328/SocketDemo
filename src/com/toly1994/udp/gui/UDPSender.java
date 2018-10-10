package com.toly1994.udp.gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/7 0007:11:15
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class UDPSender {
    private JPanel mPanel1;
    private JButton mButton1;
    private JTextField mMsg;
    private DatagramSocket mDatagramSocket;

    public UDPSender() {
        mDatagramSocket = null;
        try {
            //1: 通过DatagramSocket创建对象：端口8081(此端口随意)
            mDatagramSocket = new DatagramSocket(8081);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mButton1.addActionListener(e -> {
            //2: 使用DatagramPacket对象打包数据
            byte[] buf = mMsg.getText().getBytes();
            DatagramPacket dp = null;
            try {
                dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.56.1"), 8080);
                //3: 使用DatagramSocket对象发送数据包(字节数组,发送长度,ip,端口)
                mDatagramSocket.send(dp);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void close() {
        //4: 关闭DatagramSocket对象
        mDatagramSocket.close();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("发送端");
        frame.setSize(400, 400);
        frame.setLocation(300, 200);

        UDPSender UDPSender = new UDPSender();
        frame.setContentPane(UDPSender.mPanel1);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UDPSender.close();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
