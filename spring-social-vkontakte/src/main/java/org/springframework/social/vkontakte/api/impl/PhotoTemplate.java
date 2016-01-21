package org.springframework.social.vkontakte.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.vkontakte.api.AlbumsGetRequest;
import org.springframework.social.vkontakte.api.ApiVersion;
import org.springframework.social.vkontakte.api.IPhotoOperations;
import org.springframework.social.vkontakte.api.VKGenericResponse;
import org.springframework.social.vkontakte.api.attachment.Album;
import org.springframework.social.vkontakte.api.attachment.Photo;
import org.springframework.social.vkontakte.api.impl.json.VKArray;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
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
    public VKArray<Album> getAlbums(AlbumsGetRequest getAlbumsRequest) throws MissingAuthorizationException {
        if(getAlbumsRequest.getOwnerId() == null) {
            requireAuthorization();
        }

        final Properties properties = new Properties();
        if(getAlbumsRequest.getOwnerId() != null) {
            properties.put("owner_id", getAlbumsRequest.getOwnerId());
        }
        if(getAlbumsRequest.getAlbumIds() != null) {
            properties.put("album_ids", getAlbumsRequest.getAlbumIds());
        }
        if(getAlbumsRequest.getOffset() != null) {
            properties.put("offset", getAlbumsRequest.getOffset());
        }
        if(getAlbumsRequest.getCount() != null) {
            properties.put("count", getAlbumsRequest.getCount());
        }
        if(getAlbumsRequest.getNeedCovers() != null) {
            properties.put("need_covers", getAlbumsRequest.getNeedCovers() ? 1 : 0);
        }
        if(getAlbumsRequest.getNeedSystem() != null) {
            properties.put("need_system", getAlbumsRequest.getNeedSystem() ? 1 : 0);
        }

        final URI uri = makeOptionalAuthOperationalURL("photos.getAlbums", properties, ApiVersion.VERSION_5_41);
        final VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        return deserializeVK50ItemsResponse(response, Album.class);
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

    @Override
    public int getAlbumsCount(Long ownerId, long groupId) throws MissingAuthorizationException {
        return 0;
    }

    @Override
    public VKArray<Photo> getById(List<String> photos, Boolean extended, Boolean photoSizes) {
        if(photos == null || photos.isEmpty()) {
            throw new IllegalArgumentException("photos cannot be null or empty");
        }
        final Properties properties = new Properties();
        String photosJoined = null;
        boolean first = true;
        for(String photo: photos) {
            if(first) {
                photosJoined = photo;
                first = false;
            } else {
                photosJoined += "," + photo;
            }
        }
        properties.put("photos", photosJoined);
        if(extended != null) {
            properties.put("extended", extended);
        }
        if(photoSizes != null) {
            properties.put("photo_sizes", photoSizes);
        }
        final URI uri = makeOptionalAuthOperationalURL("photos.getById", properties, ApiVersion.VERSION_5_41);
        final VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        return deserializeArray(response, Photo.class);

    }

    @Override
    public VKArray<Photo> search(String q, double lat, double longitude, long startTime, long endTime, boolean sort, int offset, int count, int radius) {
        Properties properties = new Properties();
        if(q != null) {
            properties.put("q", q);
        }
        properties.put("lat", lat);
        properties.put("long", longitude);
        properties.put("startTime", startTime);
        properties.put("endTime", endTime);
        properties.put("sort", sort);
        properties.put("offset", offset);
        properties.put("count", count);
        properties.put("radius", radius);
        final URI uri = makeOptionalAuthOperationalURL("photos.search", properties, ApiVersion.VERSION_5_41);
        final VKGenericResponse response = restTemplate.getForObject(uri, VKGenericResponse.class);
        return deserializeVK50ItemsResponse(response, Photo.class);
    }
}
