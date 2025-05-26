package petexplorer.notification;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import petexplorer.domain.AnimalPierdut;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Trimite către toți clienții abonați la topicul /topic/animale-pierdute
     */
    public void notifyNewLostAnimal(AnimalPierdut animal) {
        messagingTemplate.convertAndSend("/topic/animale-pierdute", animal);
    }

    public void notifyMarkAsResolved(AnimalPierdut animal) {
        messagingTemplate.convertAndSend("/topic/animale-pierdute/resolved", animal);
    }

    /**
     * (Opțional) trimite unui user anume:
     */
    public void notifyUser(String username, AnimalPierdut animal) {
        messagingTemplate.convertAndSendToUser(username, "/queue/notifications", animal);
    }
}
