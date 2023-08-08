package org.example.week2;

abstract class CourseFactory {

    // 객체 생성 전처리 / 후처리 메서드 (상속 불가)
    final Course openCourse() {

        Course course = createCourse();

        return course;
    }

    abstract protected Course createCourse();

}

class OfflineCourseFactory extends CourseFactory {
    @Override
    protected Course createCourse() {
        return new OfflineCourse("OfflineCourse", "Brandon");
    }
}

class OnlineCourseFactory extends CourseFactory {
    @Override
    protected Course createCourse() {
        return new OnlineCourse("OnlineCourse", "Robert");
    }
}