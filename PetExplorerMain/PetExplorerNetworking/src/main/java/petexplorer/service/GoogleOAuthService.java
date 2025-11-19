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
        this.verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("YOUR_CLIENT_ID"))
                .build();
    }

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

    public void setClientId(String clientId) {
    }

    public static GoogleIdTokenVerifier createVerifier(String clientId) {
        return new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

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

