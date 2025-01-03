package com.bytebuddy.JornalApp.controller;

import com.bytebuddy.JornalApp.entity.JournalEntry;
import com.bytebuddy.JornalApp.entity.User;
import com.bytebuddy.JornalApp.repository.UserRepository;
import com.bytebuddy.JornalApp.service.JournalEntryService;
import com.bytebuddy.JornalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;




//    @GetMapping            //localhost:8080/user :-----------------GET
//    public List<User> getAll()
//    {
//        return userService.getAll();
//
//    }



    @PutMapping()      //localhost:8080/user/name   :----------------for update
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
          Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
          String userName=authentication.getName();
          User userInDb=userService.findByUserName(userName);
          userInDb.setUserName(user.getUserName());
          userInDb.setPassword(user.getPassword());
          userService.saveEntry(userInDb);

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }


    @PutMapping("/delete-user")      //localhost:8080/user   :----------------for update
    public ResponseEntity<?> deleteUserById()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
          userRepository.deleteByUserName(authentication.getName());


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

}
