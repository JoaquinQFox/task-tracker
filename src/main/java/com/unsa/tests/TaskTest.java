package com.unsa.tests;

import com.unsa.objects.Task;

public class TaskTest {
    public static void main(String[] args) {
        Task t = new Task("Sacar la basura");
        Task t2 = new Task();
        System.out.println(t.toJson());
        System.out.println(t2.toJson());
    }
}
