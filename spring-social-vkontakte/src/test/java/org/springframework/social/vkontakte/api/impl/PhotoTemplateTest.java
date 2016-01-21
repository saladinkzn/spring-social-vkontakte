package org.springframework.social.vkontakte.api.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.vkontakte.api.AlbumsGetRequest;
import org.springframework.social.vkontakte.api.attachment.Album;
import org.springframework.social.vkontakte.api.attachment.Photo;
import org.springframework.social.vkontakte.api.impl.json.VKArray;
import org.springframework.social.vkontakte.api.vkenums.AlbumType;
import org.springframework.social.vkontakte.api.vkenums.SortOrder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
/**
 * @author sala
 */
public class PhotoTemplateTest extends AbstractVKontakteApiTest {
    @Test
    public void testGetAlbumsOwnerIdAlbumId() {
        unauthorizedMockServer.expect(requestTo("https://api.vk.com/method/photos.getAlbums?v=5.41&album_ids=210742268%2C225103872&owner_id=66748"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("albums-simple-5.27"), MediaType.APPLICATION_JSON));
        List<String> albumIds = new ArrayList<String>();
        albumIds.add("210742268");
        albumIds.add("225103872");

        final List<Album> albums = unauthorizedVKontakte.photoOperations().getAlbums(AlbumsGetRequest.builder()
                .setOwnerId(66748L)
                .setAlbumIds(albumIds)
                .build()).getItems();
        assertAlbums(albums);
    }

    @Test
    public void testGetAlbumsExtended() {
        unauthorizedMockServer.expect(requestTo("https://api.vk.com/method/photos.getAlbums?v=5.41&need_system=1&album_ids=210742268%2C225103872&owner_id=66748&need_covers=1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("albums-simple-5.27"), MediaType.APPLICATION_JSON));
        List<String> albumIds = new ArrayList<String>();
        albumIds.add("210742268");
        albumIds.add("225103872");
        final List<Album> albums = unauthorizedVKontakte.photoOperations().getAlbums(AlbumsGetRequest.builder()
                .setOwnerId(66748L)
                .setAlbumIds(albumIds)
                .setNeedCovers(true)
                .setNeedSystem(true)
                .build()).getItems();
        assertAlbums(albums);
    }

    @Test(expected = MissingAuthorizationException.class)
    public void testNotAuthorized() {
        final VKArray<Album> albums = unauthorizedVKontakte.photoOperations().getAlbums(AlbumsGetRequest.builder().build());
    }

    private void assertAlbums(List<Album> albums) {
        Assert.assertNotNull(albums);
        Assert.assertEquals(2, albums.size());
    }

    @Test
    public void testGetOwnerIdAlbumIdRevExtended() {
        unauthorizedMockServer.expect(requestTo("https://api.vk.com/method/photos.get?v=5.41&extended=1&rev=1&album_id=wall&owner_id=1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResource("photos-5.27"), MediaType.APPLICATION_JSON));
        final List<Photo> items = unauthorizedVKontakte.photoOperations().get(PhotoGetRequest.builder(AlbumType.WALL)
                .setOwnerId(1L)
                .setSortOrder(SortOrder.desc)
                .setExtended(true)
                .build()).getItems();
        assertItems(items);
    }

    private void assertItems(List<Photo> items) {
        Assert.assertNotNull(items);
        Assert.assertEquals(10, items.size());

        // TODO:
    }
}
