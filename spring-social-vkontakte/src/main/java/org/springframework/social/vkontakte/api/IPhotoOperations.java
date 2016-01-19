package org.springframework.social.vkontakte.api;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.vkontakte.api.attachment.Photo;
import org.springframework.social.vkontakte.api.impl.PhotoGetRequest;
import org.springframework.social.vkontakte.api.impl.json.VKArray;

/**
 * @author sala
 */
public interface IPhotoOperations {
    /**
     * Returns list of photos from specified album owned by specified user or group
     * <br/>
     * <a href="https://vk.com/dev/photos.get">photos.get</a>
     * <br/>
     * This method does not require authorization if ownerId != null
     *
     * @return list of photos
     * @param photoGetRequest
     *
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if called without authorization and ownerId == null
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if vk returned error
     */
    VKArray<Photo> get(PhotoGetRequest photoGetRequest) throws MissingAuthorizationException;
}
