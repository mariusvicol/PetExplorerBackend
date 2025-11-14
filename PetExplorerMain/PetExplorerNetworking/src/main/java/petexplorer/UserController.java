package petexplorer;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import petexplorer.domain.User;
import petexplorer.service.GoogleOAuthService;
import petexplorer.service.TotpService;
import petexplorer.userrepos.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final TotpService totpService;
    
    @Value("${google.oauth.client.id}")
    private String googleClientId;

    public UserController(UserRepository userRepository, TotpService totpService) {
        this.userRepository = userRepository;
        this.totpService = totpService;
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

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setAuthProvider("EMAIL");
        return userRepository.save(user);
    }

    @PostMapping("/google-login")
    public Map<String, Object> googleLogin(@RequestBody Map<String, String> request) {
        String idToken = request.get("idToken");
        
        if (idToken == null || idToken.isEmpty()) {
            throw new RuntimeException("ID token is required");
        }

        GoogleIdTokenVerifier verifier = GoogleOAuthService.createVerifier(googleClientId);
        GoogleOAuthService.GoogleUserInfo googleUserInfo = null;
        
        try {
            GoogleIdToken token = verifier.verify(idToken);
            if (token != null) {
                GoogleIdToken.Payload payload = token.getPayload();
                String email = (String) payload.get("email");
                String name = (String) payload.get("name");
                String googleId = payload.getSubject();
                String pictureUrl = (String) payload.get("picture");
                googleUserInfo = new GoogleOAuthService.GoogleUserInfo(email, name, googleId, pictureUrl);
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid Google ID token: " + e.getMessage());
        }

        if (googleUserInfo == null) {
            throw new RuntimeException("Failed to verify Google ID token");
        }

        User user = userRepository.findByEmail(googleUserInfo.getEmail());
        
        if (user == null) {
            user = new User(googleUserInfo.getEmail(), googleUserInfo.getName(), googleUserInfo.getGoogleId());
            user = userRepository.save(user);
        } else {
            if (user.getGoogleId() == null || user.getGoogleId().isEmpty()) {
                user.setGoogleId(googleUserInfo.getGoogleId());
                user.setAuthProvider("GOOGLE");
                userRepository.update(user);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("requires2FA", user.getTotpEnabled() != null && user.getTotpEnabled());
        
        return response;
    }

    @PostMapping("/enable-2fa")
    public Map<String, String> enable2FA(@RequestBody Map<String, Integer> request) {
        Integer userId = request.get("userId");
        User user = userRepository.findOne(userId);
        
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Generate TOTP secret
        String secret = totpService.generateSecret();
        user.setTotpSecret(secret);
        userRepository.update(user);

        // Generate QR code data URI
        String qrCodeDataUri = totpService.generateQrCodeDataUri(user.getEmail(), secret);

        Map<String, String> response = new HashMap<>();
        response.put("secret", secret);
        response.put("qrCodeDataUri", qrCodeDataUri);
        
        return response;
    }

    @PostMapping("/verify-2fa-setup")
    public Map<String, Object> verify2FASetup(@RequestBody Map<String, Object> request) {
        Integer userId = ((Number) request.get("userId")).intValue();
        String code = (String) request.get("code");
        
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String secret = user.getTotpSecret();
        if (secret == null || secret.isEmpty()) {
            throw new RuntimeException("2FA not initialized. Please enable 2FA first.");
        }

        boolean isValid = totpService.verifyCode(secret, code);
        
        Map<String, Object> response = new HashMap<>();
        if (isValid) {
            user.setTotpEnabled(true);
            userRepository.update(user);
            response.put("success", true);
            response.put("message", "2FA enabled successfully");
        } else {
            response.put("success", false);
            response.put("message", "Invalid verification code");
        }
        
        return response;
    }

    @PostMapping("/verify-2fa-login")
    public User verify2FALogin(@RequestBody Map<String, Object> request) {
        String email = (String) request.get("email");
        String code = (String) request.get("code");
        
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (user.getTotpEnabled() == null || !user.getTotpEnabled()) {
            throw new RuntimeException("2FA is not enabled for this user");
        }

        String secret = user.getTotpSecret();
        if (secret == null || secret.isEmpty()) {
            throw new RuntimeException("2FA secret not found");
        }

        boolean isValid = totpService.verifyCode(secret, code);
        if (!isValid) {
            throw new RuntimeException("Invalid 2FA code");
        }

        return user;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Utilizator inexistent.");
        }
        
        // Check if user is trying to login with password but account is Google-only
        if ("GOOGLE".equals(user.getAuthProvider()) && (user.getPassword() == null || user.getPassword().isEmpty())) {
            throw new RuntimeException("Acest cont folosește autentificare Google. Te rugăm să te conectezi cu Google.");
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Parolă incorecta.");
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("requires2FA", user.getTotpEnabled() != null && user.getTotpEnabled());
        
        return response;
    }

}
