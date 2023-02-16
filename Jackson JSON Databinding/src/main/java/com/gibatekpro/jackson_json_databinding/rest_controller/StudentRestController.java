package com.gibatekpro.jackson_json_databinding.rest_controller;

import com.gibatekpro.jackson_json_databinding.exception.StudentErrorResponse;
import com.gibatekpro.jackson_json_databinding.exception.StudentNotFoundException;
import com.gibatekpro.jackson_json_databinding.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    List<Student> studentList;

    //define @PostConstruct
    @PostConstruct
    public void loadData() {

        /*
         * Spring Rest will automatically use Jackson
         * to convert our POJO to Json
         * */

        studentList = new ArrayList<>();

        studentList.add(new Student(0, "Joe", "Gibah"));
        studentList.add(new Student(1, "Jean", "Ikediashi"));
        studentList.add(new Student(2, "Tony", "Gibah"));
        studentList.add(new Student(3, "Rachel", "Ikediashi"));

    }

    @GetMapping("/students")
    public List<Student> studentList() {

        /*
        * Spring Rest will automatically use Jackson
        * to convert our POJO to Json
        * */

        return studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student studentFromId(@PathVariable int studentId) {

//        Student theStudent = null;
//
//        for (Student student : studentList) {
//
//            if (student.getId() == studentId) {
//                theStudent = student;
//            }
//
//        }

        if ((studentId >= studentList.size()) || (studentId < 0)) {

            throw new StudentNotFoundException("Student id not found - " + studentId);

        }

        return studentList.get(studentId);
    }

}
