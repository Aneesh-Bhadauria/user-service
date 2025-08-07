package com.example.ExpenseTracker.userservice.controller;


import com.example.ExpenseTracker.userservice.entities.UserInfoDTO;
import com.example.ExpenseTracker.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/v1/createUpdate")
    public ResponseEntity<UserInfoDTO> createUpdate (@RequestBody UserInfoDTO userInfoDTO){
       try{
           UserInfoDTO user = userService.createOrUpdateUser(userInfoDTO);
           return new ResponseEntity<>(user, HttpStatus.OK);
       }catch (Exception e){
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/user/v1/getUser")
    public ResponseEntity<UserInfoDTO> getUser (UserInfoDTO userInfoDTO){
        try{
          UserInfoDTO user = userService.getUser(userInfoDTO);
          return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Boolean> checkHealth(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
