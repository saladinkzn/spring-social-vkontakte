package org.springframework.social.vkontakte.api.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.vkontakte.api.attachment.Photo;
import org.springframework.social.vkontakte.api.vkenums.AlbumType;
import org.springframework.social.vkontakte.api.vkenums.SortOrder;

import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
/**
 * @author sala
 */
public class PhotoTemplateTest extends AbstractVKontakteApiTest {
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
    }
}
