package org.springframework.social.vkontakte.api.impl.photo;

import org.springframework.social.vkontakte.api.vkenums.AlbumType;
import org.springframework.social.vkontakte.api.vkenums.SortOrder;

/**
 * @author sala
 */
public class PhotosGetRequest {
    private final Long ownerId;
    private final String albumId;
    private final String photoIds;
    private final Boolean rev;
    private final Boolean extended;
    private final String feedType;
    private final String feed;
    private final Boolean photoSizes;
    private final Integer offset;
    private final Integer count;

    public PhotosGetRequest(Long ownerId, String albumId, String photoIds, Boolean rev, Boolean extended, String feedType, String feed, Boolean photoSizes, Integer offset, Integer count) {
        this.ownerId = ownerId;
        this.albumId = albumId;
        this.photoIds = photoIds;
        this.rev = rev;
        this.extended = extended;
        this.feedType = feedType;
        this.feed = feed;
        this.photoSizes = photoSizes;
        this.offset = offset;
        this.count = count;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getPhotoIds() {
        return photoIds;
    }

    public Boolean getRev() {
        return rev;
    }

    public Boolean getExtended() {
        return extended;
    }

    public String getFeedType() {
        return feedType;
    }

    public String getFeed() {
        return feed;
    }

    public Boolean getPhotoSizes() {
        return photoSizes;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getCount() {
        return count;
    }

    public static PhotoGetRequestBuilder builder(long albumId) {
        return new PhotoGetRequestBuilder(albumId);
    }

    public static PhotoGetRequestBuilder builder(AlbumType albumType) {
        return new PhotoGetRequestBuilder(albumType);
    }

    public static class PhotoGetRequestBuilder {
        private Long ownerId;
        private String albumId;
        private String photoIds;
        private Boolean rev;
        private Boolean extended;
        private String feedType;
        private String feed;
        private Boolean photoSizes;
        private Integer offset;
        private Integer count;

        public PhotoGetRequestBuilder(long albumId) {
            this.albumId = String.valueOf(albumId);
        }

        public PhotoGetRequestBuilder(AlbumType albumType) {
            this.albumId = albumType.getInternalValue();
        }

        public PhotosGetRequest build() {
            return new PhotosGetRequest(ownerId, albumId, photoIds, rev, extended, feedType, feed, photoSizes, offset, count);
        }

        public PhotoGetRequestBuilder setOwnerId(Long ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public PhotoGetRequestBuilder setAlbumId(String albumId) {
            this.albumId = albumId;
            return this;
        }

        public PhotoGetRequestBuilder setPhotoIds(String photoIds) {
            this.photoIds = photoIds;
            return this;
        }

        public PhotoGetRequestBuilder setSortOrder(SortOrder sortOrder) {
            this.rev = sortOrder == SortOrder.desc;
            return this;
        }

        public PhotoGetRequestBuilder setExtended(boolean extended) {
            this.extended = extended;
            return this;
        }

        public PhotoGetRequestBuilder setFeedTypeAndFeed(String feedType, String feed) {
            this.feedType = feedType;
            this.feed = feed;
            return this;
        }

        public PhotoGetRequestBuilder setPhotoSizes(boolean photoSizes) {
            this.photoSizes = photoSizes;
            return this;
        }

        public PhotoGetRequestBuilder setOffsetCount(int offset, int count) {
            if(offset < 0) {
                throw new IllegalArgumentException("offset must be positive, got: " + offset);
            }
            if(count < 0 || count > 1000) {
                throw new IllegalArgumentException("count must be positive and lesser than 1000, got: " + count);
            }
            this.offset = offset;
            this.count = count;
            return this;
        }

    }
}
