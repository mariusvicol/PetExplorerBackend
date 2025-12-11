package petexplorer.notification;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import petexplorer.domain.AnimalPierdut;

public class NotificationServiceAll implements NotificationService {

    protected final SimpMessagingTemplate messagingTemplate;

    public NotificationServiceAll(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Trimite către toți clienții abonați la topicul /topic/animale-pierdute
     */
    public void notifyNewLostAnimal(AnimalPierdut animal) {
        messagingTemplate.convertAndSend("t ", animal);
    }

    public void notifyMarkAsResolved(AnimalPierdut animal) {
        messagingTemplate.convertAndSend("/topic/animale-pierdute/resolved", animal);
    }

}
