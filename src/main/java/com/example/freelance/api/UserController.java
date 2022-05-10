package com.example.freelance.api;

import com.example.freelance.model.Commande;
import com.example.freelance.model.Pharmacie;
import com.example.freelance.model.User;
import com.example.freelance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));

    }
    @DeleteMapping("/id")
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
    }
    @PostMapping("/addPharmacieToUser/{idUser}")
    public ResponseEntity<User> addPharmacieToUser(@RequestBody Pharmacie pharmacie,@PathVariable("idUser") String idUser) {
       User user=userService.addPharmacieToUser(idUser,pharmacie);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }
    @PostMapping("/addCommandToUser/{idUser}")
    public ResponseEntity<User> addCommandToUser(@RequestBody Commande commande, @PathVariable("idUser") String idUser) {
        User user=userService.addCommandToUser(idUser,commande);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

}
