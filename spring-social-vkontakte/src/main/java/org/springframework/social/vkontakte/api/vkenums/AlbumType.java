package org.springframework.social.vkontakte.api.vkenums;

/**
 * Special album_id for photos.get
 */
public enum AlbumType {
    WALL("wall"), PROFILE("profile"), SAVED("saved");

    private final String internalValue;

    AlbumType(final String internalValue) {
        this.internalValue = internalValue;
    }

    public String getInternalValue() {
        return internalValue;
    }
}
