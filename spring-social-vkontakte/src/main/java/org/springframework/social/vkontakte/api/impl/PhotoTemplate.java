package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.vkontakte.api.ApiVersion;
import org.springframework.social.vkontakte.api.IPhotoOperations;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.social.vkontakte.api.attachment.Photo;
import org.springframework.social.vkontakte.api.impl.json.VKArray;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Properties;

/**
 * @author sala
 */
public class PhotoTemplate extends AbstractVKontakteOperations implements IPhotoOperations {
    private final RestTemplate restTemplate;

    public PhotoTemplate(RestTemplate restTemplate, String accessToken, ObjectMapper objectMapper, boolean isAuthorized) {
        super(isAuthorized, accessToken, objectMapper);
        this.restTemplate = restTemplate;
    }

    @Override
    public VKArray<Photo> get(PhotoGetRequest photoGetRequest) throws MissingAuthorizationException {
        if(photoGetRequest.getOwnerId() == null) {
            requireAuthorization();
        }
        final Properties params = new Properties();

        if(photoGetRequest.getOwnerId() != null) {
            params.put("owner_id", photoGetRequest.getOwnerId());
        }
        params.put("album_id", photoGetRequest.getAlbumId());
        if(photoGetRequest.getPhotoIds() != null) {
            params.put("photo_ids", photoGetRequest.getPhotoIds());
        }
        if(photoGetRequest.getRev() != null) {
            params.put("rev", photoGetRequest.getRev() ? 1 : 0);
        }
        if(photoGetRequest.getExtended() != null) {
            params.put("extended", photoGetRequest.getExtended() ? 1 : 0);
        }
        if(photoGetRequest.getFeedType() != null) {
            params.put("feed_type", photoGetRequest.getFeedType());
        }
        if(photoGetRequest.getFeed() != null) {
            params.put("feed", photoGetRequest.getFeed());
        }
        if(photoGetRequest.getOffset() != null) {
            params.put("offset", photoGetRequest.getOffset());
        }
        if(photoGetRequest.getCount() != null) {
            params.put("count", photoGetRequest.getCount());
        }
        final URI uri = makeOptionalAuthOperationalURL("photos.get", params, ApiVersion.VERSION_5_41);
        final VKGenericResponse forObject = restTemplate.getForObject(uri, VKGenericResponse.class);
        return deserializeVK50ItemsResponse(forObject, Photo.class);
    }
}
