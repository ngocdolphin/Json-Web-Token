package com.Controller;

import com.Entity.User;
import com.Form.UserCreateForm;
import com.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
    @Autowired
    private IUserService service;

    @GetMapping
    public List<User> findAll(){
        return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody UserCreateForm form){
        service.create(form);
    }

}
