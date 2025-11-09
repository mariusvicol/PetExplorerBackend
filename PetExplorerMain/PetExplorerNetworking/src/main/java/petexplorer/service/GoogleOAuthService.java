package petexplorer.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleOAuthService {

    private final GoogleIdTokenVerifier verifier;

    public GoogleOAuthService() {
        // Initialize Google ID Token Verifier
        // Note: For production, you should configure the client ID from application.properties
        this.verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("YOUR_CLIENT_ID")) // Will be configured via properties
                .build();
    }

    /**
     * Verifies a Google ID token and extracts user information
     * @param idTokenString The ID token string from Google Sign-In
     * @return GoogleUserInfo containing email, name, and Google ID, or null if invalid
     */
    public GoogleUserInfo verifyToken(String idTokenString) {
        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                
                String email = (String) payload.get("email");
                String name = (String) payload.get("name");
                String googleId = payload.getSubject();
                String pictureUrl = (String) payload.get("picture");
                
                return new GoogleUserInfo(email, name, googleId, pictureUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sets the Google Client ID for token verification
     * This should be called during initialization with the actual client ID
     */
    public void setClientId(String clientId) {
        // Note: In a real implementation, you'd recreate the verifier with the new client ID
        // For now, we'll handle this in the controller by creating a new verifier
    }

    /**
     * Creates a verifier with a specific client ID
     */
    public static GoogleIdTokenVerifier createVerifier(String clientId) {
        return new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    /**
     * Inner class to hold Google user information
     */
    public static class GoogleUserInfo {
        private final String email;
        private final String name;
        private final String googleId;
        private final String pictureUrl;

        public GoogleUserInfo(String email, String name, String googleId, String pictureUrl) {
            this.email = email;
            this.name = name;
            this.googleId = googleId;
            this.pictureUrl = pictureUrl;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public String getGoogleId() {
            return googleId;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }
    }
}

