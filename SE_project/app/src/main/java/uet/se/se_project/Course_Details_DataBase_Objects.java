package uet.se.se_project;

class Course_Details_DataBase_Objects {
    private String course_name;
    private String teacher_name;
    public Course_Details_DataBase_Objects() {
    }

    public Course_Details_DataBase_Objects(String course_name, String teacher_name) {
        this.course_name = course_name;
        this.teacher_name = teacher_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

}
