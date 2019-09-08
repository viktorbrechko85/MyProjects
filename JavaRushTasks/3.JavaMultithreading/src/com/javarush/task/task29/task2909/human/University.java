package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University  {
    protected int age;
    protected String name;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student st = null;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getAverageGrade()==averageGrade){
                st = students.get(i);
                break;
            }
        }
        return st;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student st = null;
        double MaxAverageGrade = students.get(0).getAverageGrade();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getAverageGrade()>MaxAverageGrade){
                st = students.get(i);
                MaxAverageGrade = students.get(i).getAverageGrade();
            }
        }
        return st;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student st = null;
        double MaxAverageGrade = students.get(0).getAverageGrade();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getAverageGrade()<MaxAverageGrade){
                st = students.get(i);
                MaxAverageGrade = students.get(i).getAverageGrade();
            }
        }
        return st;
    }

    public void expel(Student student) {
        //TODO:
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).equals(student))
            {
                students.remove(i);
                break;
            }
        }
    }
}