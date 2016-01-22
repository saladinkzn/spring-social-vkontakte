package org.springframework.social.vkontakte.api.impl.photo;

/**
 *
 */
public class PhotosSearchRequest {
    private final String q;
    private final Double lat;
    private final Double longitude;
    private final Long startTime;
    private final Long endTime;
    private final Boolean sort;
    private final Integer offset;
    private final Integer count;
    private final Integer radius;

    public PhotosSearchRequest(String q, Double lat, Double longitude, Long startTime, Long endTime, Boolean sort, Integer offset, Integer count, Integer radius) {
        this.q = q;
        this.lat = lat;
        this.longitude = longitude;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sort = sort;
        this.offset = offset;
        this.count = count;
        this.radius = radius;
    }

    public String getQ() {
        return q;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public Boolean getSort() {
        return sort;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getRadius() {
        return radius;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private  String q;
        private Double lat;
        private Double longitude;
        private Long startTime;
        private Long endTime;
        private Boolean sort;
        private Integer offset;
        private Integer count;
        private Integer radius;
        
        public Builder setQuery(String q) {
            this.q = q;
            return this;
        }

        public Builder setLatLongRadius(Double lat, Double longitude, Integer radius) {
            this.lat = lat;
            this.longitude = longitude;
            this.radius = radius;
            return this;
        }

        public Builder setStartEndTime(Long startTime, Long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
            return this;
        }

        public Builder setOffsetCount(Integer offset, Integer count) {
            this.offset = offset;
            this.count = count;
            return this;
        }

        public PhotosSearchRequest builder() {
            return new PhotosSearchRequest(q, lat, longitude, startTime, endTime, sort, offset, count, radius);
        }

    }
}
