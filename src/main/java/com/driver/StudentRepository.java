package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository {
    HashMap<String,Student> studentDatabase=new HashMap<>();
    HashMap<String,Teacher> teacherDatabase=new HashMap<>();
    HashMap<String, ArrayList<String>> studentTeacherPairDatabase=new HashMap<>();

    public void addStudent(Student student) {
        studentDatabase.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDatabase.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        List<String> studentList=new ArrayList<>();
        if(studentTeacherPairDatabase.containsKey(teacher))
            studentList=studentTeacherPairDatabase.get(teacher);
        if(!studentList.contains(student))
            studentList.add(student);
        studentTeacherPairDatabase.put(teacher, (ArrayList<String>) studentList);
//        if(!studentTeacherPairDatabase.containsKey(teacher))
//            studentTeacherPairDatabase.put(teacher,new ArrayList<Student>());
//        studentTeacherPairDatabase.get(teacher).add(studentDatabase.get(student));
    }

    public Student getStudentByName(String name) {
        return studentDatabase.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherDatabase.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> studentList=new ArrayList<>();
        if(studentTeacherPairDatabase.containsKey(teacher))
            studentList=studentTeacherPairDatabase.get(teacher);
        return studentList;
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentDatabase.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        ArrayList<String > pairList = new ArrayList<>();
        if(studentTeacherPairDatabase.containsKey(teacher)){
            pairList=studentTeacherPairDatabase.get(teacher);
            for(String student:pairList){
                studentDatabase.remove(student);
            }
            studentTeacherPairDatabase.remove(teacher);
        }
        teacherDatabase.remove(teacher);
    }

    public void deleteAllTeachers() {
        for (String teacher : studentTeacherPairDatabase.keySet()) {
            List<String> pairlist = studentTeacherPairDatabase.get(teacher);
            for (String st : pairlist) {
                if (studentDatabase.containsKey(st))
                    studentDatabase.remove(st);
            }
        }
        teacherDatabase.clear();
        studentTeacherPairDatabase.clear();
    }
}