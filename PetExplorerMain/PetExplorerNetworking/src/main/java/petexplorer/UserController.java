package petexplorer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.User;
import petexplorer.userrepos.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userRepository.findOne(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User existingUser = userRepository.findOne(id);
        if (existingUser != null) {
            if (updatedUser.getEmail() != null && !updatedUser.getEmail().isBlank()) {
                existingUser.setEmail(updatedUser.getEmail());
            }

            if (updatedUser.getNume() != null && !updatedUser.getNume().isBlank()) {
                existingUser.setNume(updatedUser.getNume());
            }

            if (updatedUser.getNrTelefon() != null && !updatedUser.getNrTelefon().isBlank()) {
                existingUser.setNrTelefon(updatedUser.getNrTelefon());
            }

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String hashedPassword = encoder.encode(updatedUser.getPassword());
                existingUser.setPassword(hashedPassword);
            }

            userRepository.update(existingUser);
            return existingUser;
        } else {
            return null;
        }
    }



    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.delete(id);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Utilizator inexistent.");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Parolă incorecta.");
        }
        return user;
    }


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

}
