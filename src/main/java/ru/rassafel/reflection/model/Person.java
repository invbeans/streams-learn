/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rassafel.reflection.model;
import lombok.Data;
import ru.rassafel.reflection.annotation.ParseIndex;
/**
 *
 * @author Buzuluchanka
 */
@Data
public class Person {
    @ParseIndex(headerIndex = 0)
    private String firstName;
    
    @ParseIndex(headerIndex = 1)
    private String lastName;
    
    @ParseIndex(headerName = "email")
    private String email;
    
    @ParseIndex(headerIndex = 3)
    private String gender;

}
