package com.toly1994.tcp;/*
客户端通过键盘录入用户名。
服务端对这个用户名进行校验。

如果该用户存在，在服务端显示xxx，已登陆。
并在客户端显示 xxx，欢迎光临。

如果该用户存在，在服务端显示xxx，尝试登陆。
并在客户端显示 xxx，该用户不存在。

最多就登录三次。
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


class LoginClient {
    public static void main(String[] args) throws Exception {
        //建立Socket连接
        Socket s = new Socket("192.168.56.1", 8080);
        //监听键盘录入
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        //从Socket对象中获取输出流，向服务端写数据
        PrintWriter pwOut = new PrintWriter(s.getOutputStream(), true);
        //从Socket对象中获取输入流，读取服务端发送来的数据
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

        for (int x = 0; x < 3; x++) {
            String line = bufr.readLine();
            if (line == null)
                break;
            //向服务端写数据
            pwOut.println(line);

            //从服务端读数据
            String info = bufIn.readLine();
            System.out.println("info:" + info);
            if (info.contains("欢迎"))
                break;
        }

        bufr.close();
        s.close();
    }
}


class UserThread implements Runnable {
    private Socket s;

    UserThread(Socket s) {
        this.s = s;
    }

    public void run() {
        String ip = s.getInetAddress().getHostAddress();
        System.out.println(ip + "....connected");
        try {
            for (int x = 0; x < 3; x++) {
                BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

                String name = bufIn.readLine();
                if (name == null)
                    break;

                String fileName = "F:\\源码集合\\黑马程序员_毕向东_Java基础源码\\毕向东_Java基础源代码\\day24\\user.txt";
                //读取字符文件
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line = null;
                boolean flag = false;
                while ((line = br.readLine()) != null) {
                    if (line.equals(name)) {
                        flag = true;
                        break;
                    }
                }

                // //从Socket对象中获取输出流，向客户端写数据
                PrintWriter pwOut = new PrintWriter(s.getOutputStream(), true);
                if (flag) {
                    System.out.println(name + ",已登录");
                    //向客户端写数据
                    pwOut.println(name + ",欢迎光临");
                    break;
                } else {
                    System.out.println(name + ",尝试登录");
                    //向客户端写数据
                    pwOut.println(name + ",用户名不存在");
                }
            }
            s.close();
        } catch (Exception e) {
            throw new RuntimeException(ip + "校验失败");
        }
    }
}

class LoginServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8080);

        while (true) {
            Socket s = ss.accept();
            new Thread(new UserThread(s)).start();
        }
    }
}
