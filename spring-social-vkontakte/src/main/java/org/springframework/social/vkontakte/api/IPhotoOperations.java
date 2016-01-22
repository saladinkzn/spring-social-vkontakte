package org.springframework.social.vkontakte.api;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.vkontakte.api.attachment.Album;
import org.springframework.social.vkontakte.api.attachment.Photo;
import org.springframework.social.vkontakte.api.impl.photo.PhotosGetAlbumsRequest;
import org.springframework.social.vkontakte.api.impl.photo.PhotosGetRequest;
import org.springframework.social.vkontakte.api.impl.json.VKArray;
import org.springframework.social.vkontakte.api.impl.photo.PhotosSearchRequest;

import java.util.List;

/**
 * @author sala
 */
public interface IPhotoOperations {

    /**
     * Returns list of albums by owner_id
     * @return list of albums
     *
     * @throws MissingAuthorizationException if called without authorization and ownerId == null
     */
    VKArray<Album> getAlbums(PhotosGetAlbumsRequest getAlbumsRequest) throws MissingAuthorizationException;

    /**
     * Returns list of photos from specified album owned by specified user or group
     * This method does not require authorization if ownerId != null
     *
     * @return list of photos
     * @param photoGetRequest request entity
     *
     * @throws org.springframework.social.ApiException if there is an error while communicating with VKontakte.
     * @throws org.springframework.social.MissingAuthorizationException if called without authorization and ownerId == null
     * @throws org.springframework.social.vkontakte.api.VKontakteErrorException if vk returned error
     *
     * @see <a href="https://vk.com/dev/photos.get">photos.get</a>
     */
    VKArray<Photo> get(PhotosGetRequest photoGetRequest) throws MissingAuthorizationException;

    /**
     * Return count of group's albums accessabe by owner_id
     *
     * @param ownerId id of user, null for current user
     * @param groupId id of group
     * @return count of albums
     * @throws MissingAuthorizationException
     */
    int getAlbumsCount(Long ownerId, long groupId) throws MissingAuthorizationException;

    /**
     * return photos by ids list
     *
     * @param photos list of ids
     * @param extended return likes, reposts etc
     * @param photoSizes return photo sizes
     * @return
     */
    VKArray<Photo> getById(List<String> photos, Boolean extended, Boolean photoSizes);

    /**
     *
     * @param photosSearchRequest photo's search request
     * @return
     */
    VKArray<Photo> search(PhotosSearchRequest photosSearchRequest);
}
