package com.dev.mcc_tools.controllers;

import com.dev.mcc_tools.domain.User;
import com.dev.mcc_tools.email.EmailSenderService;
import com.dev.mcc_tools.search.UserSearch;
import com.dev.mcc_tools.search.UserSearchRequest;
import com.dev.mcc_tools.services.UserService;
import com.dev.mcc_tools.validation.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserSearch userSearch;
    @Autowired
    private EmailSenderService senderService;
    @Autowired
    private PasswordEncoder encoder;
    private final UserValidator userValidator = new UserValidator();

//    @PostMapping("")
//    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result) {
//        HttpStatus httpStatus = HttpStatus.CREATED;
//
//        String encoded = encoder.encode(user.getPassword());
//        user.setPassword(encoded);
//        User created = userService.saveOrUpdateUser(user);
//        FormattedResponse response = new FormattedResponse(httpStatus.value(), true, created);
//        return new ResponseEntity<>(response, httpStatus);
//    }

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        HttpStatus httpStatus = HttpStatus.OK;

        Iterable<User> found = userService.findAllUsers();
        FormattedResponse response = new FormattedResponse(httpStatus.value(), true, found);

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        HttpStatus httpStatus = HttpStatus.OK;
        User found = userService.findUserByEmail(email);
        FormattedResponse response = new FormattedResponse(httpStatus.value(), true, found);
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/search")
    public ResponseEntity<?> userSearch(
            @RequestParam(required = false, name = "role") String role,
            @RequestParam(required = false, name = "email") String email
    ) {
        UserSearchRequest request = new UserSearchRequest();


        if (email != null) request.setEmail(email);
        if (role != null) request.setRole(role);

        HttpStatus httpStatus = HttpStatus.OK;
        FormattedResponse response;

        Iterable<User> found = userSearch.findAllByCriteria(request);
        response = new FormattedResponse(httpStatus.value(), true, found);
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/userid/{userID}")
    public ResponseEntity<?> getUserById(@PathVariable int userID) {
        HttpStatus httpStatus = HttpStatus.OK;

        User found = userService.findUserById(userID);
        System.out.println(found.getPassword());
        return new ResponseEntity<>(found, httpStatus);
    }

    @PutMapping("/{pk}/update_password")
    public ResponseEntity<?> updateUserPassword(@PathVariable int pk, @RequestBody Map<String, String> updateObj) {
//        userValidator.initializeErrors();
//        HttpStatus httpStatus = HttpStatus.CREATED;
//        FormattedResponse response;
//
//
//        // get user by id
//        User user = userService.findUserById(pk);
//        // get hashed user password
//        String oldPassword = user.getPassword();
//
//
//        // compare old password to new password
//        userValidator.checkPasswordUpdate(oldPassword, updateObj);
//        // check if new password is the same as the old password
////        HashMap<String, String> errors = userValidator.getErrors();
//        HashMap<String, ArrayList<String>> errors = userValidator.getErrors();
//
//
//        // hash the old password
//        // set the found user password to the new password
//        if (errors.isEmpty()) {
//            user.setPassword(encoder.encode(updateObj.get("newPassword")));
//            // save the user
//            userService.updatePassword(user);
//            // send and email to the user confirming that the password has been changed.
//
//            response = new FormattedResponse(httpStatus.value(), true, user);
//        } else {
//            httpStatus = HttpStatus.BAD_REQUEST;
//            response = new ErrorResponse(httpStatus.value(), false, errors);
//        }
//        System.out.println(user.getPassword());
        FormattedResponse response = userService.updatePassword(pk, updateObj);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));

    }

    @PutMapping("/update/{pk}")
    public ResponseEntity<?> updateUser(@PathVariable int pk, @RequestBody User user) {
//        userValidator.initializeErrors();
//
//        FormattedResponse response;
//
//        HttpStatus httpStatus = HttpStatus.CREATED;
//
////        HashMap<String, String> errors = userValidator.getErrors();
//        HashMap<String, ArrayList<String>> errors = userValidator.getErrors();
//
//        User found = userService.findUserById(pk);
//
//        userValidator.checkIDMatch(pk, user.getUserID());
//        userValidator.checkUserForUpdate(found);
//
//        if (errors.isEmpty()) {
//            user.setPassword(found.getPassword());
//            user.setDate_created(found.getDate_created());
//            User updated = userService.saveOrUpdateUser(user);
//            response = new FormattedResponse(httpStatus.value(), true, updated);
//        } else {
//            httpStatus = HttpStatus.BAD_REQUEST;
//            response = new ErrorResponse(httpStatus.value(), false, errors);
//        }

        FormattedResponse response = userService.updateUser(pk,user);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

}