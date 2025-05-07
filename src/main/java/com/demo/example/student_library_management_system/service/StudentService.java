package com.demo.example.student_library_management_system.service;


import com.demo.example.student_library_management_system.converter.StudentConverter;
import com.demo.example.student_library_management_system.enums.CardStatus;
import com.demo.example.student_library_management_system.model.Card;
import com.demo.example.student_library_management_system.model.Student;
import com.demo.example.student_library_management_system.repository.StudentRepository;
import com.demo.example.student_library_management_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(StudentRequestDto studentRequestDto)
    {
        //first convert the studentrequestdto into student model class
       Student student= StudentConverter.convertStudentRequestDtoIntoStudent(studentRequestDto);

       //whenever student is created , card also automatically get created
        Card card=new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        student.setCard(card);

        card.setStudent(student);

       studentRepository.save(student);
       return "Student and Card Saved Successfully";

    }


    public Student getByStudentId(int id)
    {
       Optional<Student> studentOptional= studentRepository.findById(id);
       if(studentOptional.isPresent())
       {
           return studentOptional.get();
       }
       return null;
    }



    public List<Student> getAllstudents()
    {
        List<Student> studentList=studentRepository.findAll();
        return studentList;
    }


    public String deletedStudentById(int id)
    {
     studentRepository.deleteById(id);
     return " Student with Id : "+id+"got deleted along with its card";
    }

    public String updateStudent(int id,StudentRequestDto studentRequestDto)
    {
        //check whether the student is present with that id or not

       Student student= getByStudentId(id);

       if(student!=null)
       {
        student.setName(studentRequestDto.getName());
        student.setMobile(studentRequestDto.getMobile());
        student.setDob(studentRequestDto.getDob());
        student.setSem(studentRequestDto.getSem());
        student.setEmail(studentRequestDto.getEmail());
        student.setDept(studentRequestDto.getDept());
        student.setGender(studentRequestDto.getGender());

        studentRepository.save(student);
        return "Student updated successfully";
       }
       else
       {
           return "Student not found, we cannot update ";
       }
    }
}
