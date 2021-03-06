package com.app.legend.lan_play_android.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Locale;

//记录日志，排查错误
public class LogUtils {


    public static void log(String logs){

        FileOutputStream fileOutputStream=null;

        try {

            File file=new File(LanApp.getContext().getFilesDir(),"command.log");

            if (!file.exists()){
                LanApp.getContext().openFileOutput("command.log",Context.MODE_PRIVATE);

                file=new File(LanApp.getContext().getFilesDir(),"command.log");
            }



            fileOutputStream=new FileOutputStream(file,true);

            logs=getTime()+"--->>"+logs+"\n";

            fileOutputStream.write(logs.getBytes());

            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void log(String[] logs){

        StringBuilder builder=new StringBuilder();


        for (String s:logs){

//            log(s);

            builder.append(s).append(" ");

        }

        log(builder.toString());

    }

    public static void cleanLogs(){

        File file=new File(LanApp.getContext().getFilesDir(),"command.log");

        if (file.exists()){
            if (file.delete()){
                Toast.makeText(LanApp.getContext(), "日志已清除", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private static String getTime(){

        long time=System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss",Locale.CHINA);

        return sdf.format(time);

    }

    public static String getLogs(){

        String result=null;

        FileInputStream fileInputStream= null;
        try {
            fileInputStream = LanApp.getContext().openFileInput("command.log");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            StringBuffer buffer=new StringBuffer();
            line = reader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                buffer.append(line); // 将读到的内容添加到 buffer 中
                buffer.append("\n"); // 添加换行符
                line = reader.readLine(); // 读取下一行
            }
            reader.close();
            fileInputStream.close();

            result=buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

}