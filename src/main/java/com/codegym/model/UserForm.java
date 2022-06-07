package com.codegym.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserForm implements Validator {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long age;
    private String email;

    public UserForm() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm userForm = (UserForm) target;
        String firstName = userForm.getFirstName();
        String lastName = userForm.getLastName();
        String phoneNumber = userForm.getPhoneNumber();
        Long age = userForm.getAge();
        String email = userForm.getEmail();

        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");

        //validate first name
        if (firstName.length()>45 || firstName.length()<5){
            errors.rejectValue("firstName", "firstName.length");
        }

        //validate last name
        if (lastName.length()>45 || lastName.length()<5){
            errors.rejectValue("lastName", "lastName.length");
        }

        //validate phone number
        if (phoneNumber.length()>11 || phoneNumber.length()<10){
            errors.rejectValue("phoneNumber", "phoneNumber.length");
        }
        if (!phoneNumber.startsWith("0")){
            errors.rejectValue("phoneNumber", "phoneNumber.startsWith");
        }
        if (!phoneNumber.matches("(^[0-9]*$)")){
            errors.rejectValue("phoneNumber", "phoneNumber.matches");
        }

        //validate age
        if (age < 18) {
            errors.rejectValue("age", "age.lowerThan18");
        }

        //validate email
        if (!phoneNumber.matches("(^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$)")){
            errors.rejectValue("email", "email.matches");
        }
    }
}
