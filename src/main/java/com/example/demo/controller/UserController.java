package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
//dam bao DA khac thi van goi den du an cua minh
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    // get all users
    //
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> list= userService.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


//    @RequestMapping(value = "/users", method = RequestMethod.POST)
//    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder){
//        userService.save(user);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
//        return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
//    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // get one user
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = (User) userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // update user
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateCustomer(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        User user1 = (User) userService.findById(id);

        if (user1 == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user1.setName(user.getName());
        user1.setNumberPhone(user.getNumberPhone());
        user1.setEmail(user.getEmail());


        userService.save(user1);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    // delete user
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        System.out.println("Delete User with id = " + id);
        if (id == null){
            System.out.println("Unable delete, User was not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}