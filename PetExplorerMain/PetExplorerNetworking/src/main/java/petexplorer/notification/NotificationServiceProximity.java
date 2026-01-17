package petexplorer.notification;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import petexplorer.domain.AnimalPierdut;
import petexplorer.notification.utils.ProximityFilterService;

@Service
public class NotificationServiceProximity extends NotificationServiceAll {
    private final ProximityFilterService proximityFilterService;

    public NotificationServiceProximity(SimpMessagingTemplate messagingTemplate, ProximityFilterService proximityFilterService) {
        super(messagingTemplate);
        this.proximityFilterService = proximityFilterService;
    }

    /**
     * Notify only one user about an event (lost or resolved)
     * @param userId user's id
     * @param animal the event's animal
     */
    private void notifyUser(int userId, AnimalPierdut animal) {
        messagingTemplate.convertAndSendToUser(String.valueOf(userId), "/queue/notifications", animal);
    }

    /**
     * Notify about a lost animal all the users which didn't send their location and only the users found in the proximity which sent their location
     * @param animal lost animal
     */
    @Override
    public void notifyNewLostAnimal(AnimalPierdut animal) {
        super.notifyNewLostAnimal(animal);
        for (int userId : proximityFilterService.getUsersInProximity(animal.getLatitudine(), animal.getLongitudine())) {
            notifyUser(userId, animal);
        }
    }

    /**
     * Notify about a resolved animal all the users which didn't send their location and only the users found in the proximity which sent their location
     * @param animal resolved animal
     */
    @Override
    public void notifyMarkAsResolved(AnimalPierdut animal) {
        super.notifyMarkAsResolved(animal);
        for (int userId : proximityFilterService.getUsersInProximity(animal.getLatitudine(), animal.getLongitudine())) {
            notifyUser(userId, animal);
        }
    }
}
