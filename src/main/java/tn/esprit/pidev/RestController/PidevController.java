package tn.esprit.pidev.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.pidev.entities.Role;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.security.services.UserDetailsServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PidevController {

    @Autowired
    UserDetailsServiceImpl userService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getuser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserbyId(@PathVariable Long id){
        return userService.getUserbyId(id);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        String result = userService.deleteUser(id);

        return "User deleted Successfully" ;

    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    List<Role> getAvailableRoles(){
        return userService.getRoles();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
