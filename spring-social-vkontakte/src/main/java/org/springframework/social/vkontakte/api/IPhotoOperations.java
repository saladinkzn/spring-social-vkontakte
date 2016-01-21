package org.springframework.social.vkontakte.api;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.vkontakte.api.attachment.Album;
import org.springframework.social.vkontakte.api.attachment.Photo;
import org.springframework.social.vkontakte.api.impl.PhotoGetRequest;
import org.springframework.social.vkontakte.api.impl.json.VKArray;

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
    VKArray<Album> getAlbums(AlbumsGetRequest getAlbumsRequest) throws MissingAuthorizationException;

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
     * @param q query string
     * @param lat latitude in degrees (-90; 90)
     * @param longitude longitude in degrees (-180, 180)
     * @param startTime in unixtime
     * @param endTime in unixtime
     * @param sort true if sort by likes, false if sort by date
     * @param offset
     * @param count max value 5000
     * @param radius radius (in meters) for search if lat long are specified, may be (10. 100, 800, 5000, 50000)
     * @return
     */
    VKArray<Photo> search(String q, double lat, double longitude, long startTime, long endTime, boolean sort, int offset, int count, int radius);
}
