package com.demo.example.student_library_management_system.converter;

import com.demo.example.student_library_management_system.model.Student;
import com.demo.example.student_library_management_system.requestdto.StudentRequestDto;

public class StudentConverter {

    //converter- it converts requestdto into model classes so that model class can be saved in database.

    //foreign keys should not be used in converters, it is directly used in service class

    public static Student convertStudentRequestDtoIntoStudent(StudentRequestDto studentRequestDto)
    {
     Student student=new Student();

     student.setName(studentRequestDto.getName());
     student.setDept(studentRequestDto.getDept());
     student.setGender(studentRequestDto.getGender());
     student.setDob(studentRequestDto.getDob());
     student.setSem(studentRequestDto.getSem());
     student.setEmail(studentRequestDto.getEmail());
     student.setMobile(studentRequestDto.getMobile());
     student.setAddress(studentRequestDto.getAddress());

     return student;

    }

}
