package com.demo.example.student_library_management_system.service;


import com.demo.example.student_library_management_system.converter.StudentConverter;
import com.demo.example.student_library_management_system.enums.CardStatus;
import com.demo.example.student_library_management_system.model.Card;
import com.demo.example.student_library_management_system.model.Student;
import com.demo.example.student_library_management_system.repository.StudentRepository;
import com.demo.example.student_library_management_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
       else
       {
           throw new RuntimeException("Student not found : "+id);
       }

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



    /*
    Pagination--> fetching or getting the records or data in the form of pages
    pagenumber --> the number of page you want to see(0,1,2,3,..)
    pagesize --> total number od records in each page(fixed for all pages)


    Total no.of records-28
    Pagesize-5
    0th page- 1-5
    1st page- 6-10
    2nd page- 11-15
    3rd page- 16-20
    4th page- 21-25
    5th page- 26-28
    6th page-  0


    Total no of records-11,page size-3
    0th page- 1-3
    1st page- 4-6
    2nd page- 7-9
    3rd page- 10-11

    //only Pagination use this method
    public List<Student> getAllStudentsByPage(int pageNo, int pageSize)
    {
       List<Student> studentList=  studentRepository.findAll(PageRequest.of(pageNo,pageSize)).getContent();
       return studentList;
    }

     */


    //Pagination and sorting together use this
    public List<Student> getAllStudentsByPage(int pageNo, int pageSize)
    {
       List<Student> studentList=  studentRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by("name").ascending())).getContent();
       return studentList;
    }
}
