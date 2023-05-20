package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository {
    HashMap<String,Student> studentDatabase=new HashMap<>();
    HashMap<String,Teacher> teacherDatabase=new HashMap<>();
    HashMap<String, ArrayList<Student>> studentTeacherPairDatabase=new HashMap<>();

    public void addStudent(Student student) {
        studentDatabase.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDatabase.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(!studentTeacherPairDatabase.containsKey(teacher))
            studentTeacherPairDatabase.put(teacher,new ArrayList<Student>());
        studentTeacherPairDatabase.get(teacher).add(studentDatabase.get(student));
    }

    public Student getStudentByName(String name) {
       // return studentDatabase.get(name);
        for(Student student:studentDatabase.values()){
            if(student.getName().equals(name))return student;
        }
        return new Student();
    }

    public Teacher getTeacherByName(String name) {
        //return teacherDatabase.get(name);
        for(Teacher teacher:teacherDatabase.values()){
            if(teacher.getName().equals(name))return teacher;
        }
        return new Teacher();
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> studentList=new ArrayList<>();
        for(Student student:studentTeacherPairDatabase.get(teacher)){
            studentList.add(student.getName());
        }
        return studentList;
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentDatabase.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        for(int i=0;i<teacherDatabase.size();i++){
            if(teacherDatabase.get(i).equals(teacher))
                teacherDatabase.remove(teacher);
            break;
        }
        studentTeacherPairDatabase.remove(teacher);
        teacherDatabase.remove(teacher);
    }

    public void deleteAllTeachers() {
        teacherDatabase.clear();
        studentDatabase.clear();
        studentTeacherPairDatabase.clear();
    }
}