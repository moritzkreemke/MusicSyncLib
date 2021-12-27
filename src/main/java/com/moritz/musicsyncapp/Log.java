package com.moritz.musicsyncapp;

public class Log {


    public static void d(String tag, String message)
    {
        System.out.println(tag + " : " + message);
    }

    public static void e (String tag, String message) {
        System.out.println(tag + " : " + message);
    }

    public static void e (String tag, String message, Throwable throwable) {
        System.out.println(tag + " : " + message);
        throwable.printStackTrace();
    }

}
