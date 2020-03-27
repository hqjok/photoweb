package com.photo.web.entity;

/**
 * @Create 2020-02-27 18:38
 */
public class SearchPropery {
    private String searchContent;
    private Integer areaId;

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
