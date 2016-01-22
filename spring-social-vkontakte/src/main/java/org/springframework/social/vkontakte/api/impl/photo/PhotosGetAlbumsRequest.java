package org.springframework.social.vkontakte.api.impl.photo;

import java.util.List;

/**
 *
 */
public class PhotosGetAlbumsRequest {
    private Long ownerId;
    private String albumIds;
    private Integer offset;
    private Integer count;
    private Boolean needSystem;
    private Boolean needCovers;
    private Boolean photoSizes;

    /**
     * Creates albums request
     * @param ownerId id of owner (group, user, null if current user)
     * @param albumIds list of ids to filter (null for all)
     * @param offset skip first N albums
     * @param count count of result
     * @param needSystem include system albums
     * @param needCovers include thumbnails
     * @param photoSizes include photo sizes
     */
    public PhotosGetAlbumsRequest(Long ownerId, String albumIds, Integer offset, Integer count, Boolean needSystem, Boolean needCovers, Boolean photoSizes) {
        this.ownerId = ownerId;
        this.albumIds = albumIds;
        this.offset = offset;
        this.count = count;
        this.needSystem = needSystem;
        this.needCovers = needCovers;
        this.photoSizes = photoSizes;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getAlbumIds() {
        return albumIds;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getCount() {
        return count;
    }

    public Boolean getNeedSystem() {
        return needSystem;
    }

    public Boolean getNeedCovers() {
        return needCovers;
    }

    public Boolean getPhotoSizes() {
        return photoSizes;
    }

    public static Builder builder() {
        return new PhotosGetAlbumsRequest.Builder();
    }

    public static class Builder {
        private Long ownerId;
        private List<String> albumIds;
        private Integer offset;
        private Integer count;
        private Boolean needSystem;
        private Boolean needCovers;
        private Boolean photoSizes;

        public Builder setOwnerId(Long ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder setAlbumIds(List<String> albumIds) {
            this.albumIds = albumIds;
            return this;
        }

        public Builder setOffset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public Builder setCount(Integer count) {
            this.count = count;
            return this;
        }

        public Builder setNeedSystem(Boolean needSystem) {
            this.needSystem = needSystem;
            return this;
        }

        public Builder setNeedCovers(Boolean needCovers) {
            this.needCovers = needCovers;
            return this;
        }

        public Builder setPhotoSizes(Boolean photoSizes) {
            this.photoSizes = photoSizes;
            return this;
        }

        public PhotosGetAlbumsRequest build() {
            String albumIdsJoined = null;
            if(albumIds != null) {
                boolean first = true;
                for (String albumId : albumIds) {
                    if (first) {
                        albumIdsJoined = albumId;
                        first = false;
                    } else {
                        albumIdsJoined += ("," + albumId);
                    }
                }
            }
            return new PhotosGetAlbumsRequest(ownerId, albumIdsJoined, offset, count, needSystem, needCovers, photoSizes);
        }
    }
}
