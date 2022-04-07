package com.kanto.ecommerce.controller;

import com.kanto.ecommerce.entity.AppUser;
import com.kanto.ecommerce.entity.RegisterForm;
import com.kanto.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterForm userForm) {
        if (!userForm.getPassword().equals(userForm.getRepassword())) {
            throw new RuntimeException("You must confirm your password") ;
        }

        AppUser user = accountService.findUserByUsername(userForm.getUsername()) ;
        if (user != null) {
            throw new RuntimeException("This user already exists") ;
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(userForm.getUsername());
        appUser.setPassword(userForm.getPassword());
        appUser.setEmail(userForm.getEmail());
        accountService.saveUser(appUser) ;
        accountService.addRoleToUser(userForm.getUsername(), "USER");
        return appUser;
    }

    @GetMapping("/test/findUser")
    public AppUser findUser(@RequestParam("username") String username) {
        return accountService.findUserByUsername(username) ;
    }

}
