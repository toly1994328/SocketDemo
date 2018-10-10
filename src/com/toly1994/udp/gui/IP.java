package com.toly1994.udp.gui;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 作者：张风捷特烈
 * 时间：2018/10/7 0007:12:16
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class IP {
    private JTextField mTextField1;
    private JPanel mPanel1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("IP");
        IP ip = new IP();
        frame.setContentPane(ip.mPanel1);

        frame.setSize(400, 400);
        frame.setLocation(300, 200);

        InetAddress localHost = null;
        try {
            localHost = InetAddress.getLocalHost();

            ip.mTextField1.setText(localHost.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
