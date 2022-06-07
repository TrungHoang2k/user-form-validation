package com.codegym.controller;

import com.codegym.model.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserFormController {
    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("userForm", new UserForm());
        return "/index";
    }
    @PostMapping("/")
    public String checkValidation (@Valid @ModelAttribute("userForm")UserForm userForm, BindingResult bindingResult, Model model){
        new UserForm().validate(userForm, bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/index";
        }
        else {
            model.addAttribute("userForm", userForm);
            return "/result";
        }
    }
}