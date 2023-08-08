package org.example.week2;

public class Main {

    public static void main(String[] args) {

        Course mathOfflineClass = new OfflineCourseFactory().openCourse();
        Course scienceOnlineClass = new OnlineCourseFactory().openCourse();
    }
}