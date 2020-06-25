package com.example.springSecurity.controller;

import com.example.springSecurity.dto.StudentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentContoller {

    private static final List<StudentDTO> STUDENT =
            Arrays.asList(
                    new StudentDTO(1,"nazwa1"),
                    new StudentDTO(2,"nazwa2"),
                    new StudentDTO(3,"nazwa3"),
                    new StudentDTO(4,"nazwa4"),
                    new StudentDTO(5,"nazwa5"));

    @GetMapping("{id}")
    public StudentDTO getStudnet(@PathVariable("id") Integer id ){
        try{
            return STUDENT.get(id - 1);
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
}
