package ch.pledarigrond.common.exception.dictionary;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityId) {
        super("Entity with id " + entityId + " not found");
    }
}
