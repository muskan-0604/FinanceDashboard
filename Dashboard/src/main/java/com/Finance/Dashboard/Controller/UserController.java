package com.Finance.Dashboard.Controller;


import com.Finance.Dashboard.Model.Enum.Role;
import com.Finance.Dashboard.Model.Enum.Status;
import com.Finance.Dashboard.Model.User;
import com.Finance.Dashboard.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    ResponseEntity<List<User>> getAllUsers (@RequestParam Role role){
  List<User> users =     userService.getAllUsers(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
        ResponseEntity<User> getUserById(@PathVariable Long id, @RequestParam Role role
                                         ){
             User user = userService.getById(id,role);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
             return new ResponseEntity<>(user,HttpStatus.OK);
        }

        @PostMapping
    ResponseEntity<User> createUser(@Valid @RequestBody User user,@RequestParam Role role){
        User user1 = userService.createUser(user,role);
        return new ResponseEntity<>(user1,HttpStatus.CREATED);
        }

        @PatchMapping("/{id}")
    ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user, @RequestParam Role role){
        User data = userService.updateUser(id,user,role);
        if(data == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data,HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id,@RequestParam Role role){
        User data = userService.getById(id,role);
        if(data == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id,role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping("/email")
    ResponseEntity<User> findByEmail(@PathVariable String email, @RequestParam Role role){
        User data = userService.findByEmail(email,role);
        if(data == null){
            return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(data,HttpStatus.OK);
        }

        @PatchMapping("/{id}/status")
    ResponseEntity<User> updateStatus(@PathVariable Long id,@RequestParam Status status,@RequestParam Role role){
        User user = userService.userStatus(id,status,role);
           if(user == null){
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
           return new ResponseEntity<>(user,HttpStatus.OK);
        }

}

