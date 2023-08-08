package org.example.week2;

public class Course {
    String name;
    String teacherName;
}

class OfflineCourse extends Course {
    OfflineCourse(String name, String teacherName) {
        this.name = name;
        this.teacherName = teacherName;
    }
}

class OnlineCourse extends Course {
    OnlineCourse(String name, String teacherName) {
        this.name = name;
        this.teacherName = teacherName;
    }
}

