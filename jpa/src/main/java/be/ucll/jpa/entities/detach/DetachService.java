package be.ucll.jpa.entities.detach;

public interface DetachService {

    DetachParent getDetach();

    void saveDetach(DetachParent detachParent);
}
