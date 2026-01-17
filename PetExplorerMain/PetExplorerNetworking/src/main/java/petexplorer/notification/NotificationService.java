package petexplorer.notification;

import petexplorer.domain.AnimalPierdut;

public interface NotificationService {
    void notifyNewLostAnimal(AnimalPierdut animal);
    void notifyMarkAsResolved(AnimalPierdut animal);
}
