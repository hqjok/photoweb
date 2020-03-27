package com.photo.web.entity;

import lombok.Data;

import java.util.List;

/**
 * @Create 2020-02-15 19:02
 */
@Data
public class UploadResponseResult {

    private Integer errno;

    private List data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public UploadResponseResult() {
    }

    public UploadResponseResult(Integer errno, List data) {
        this.errno = errno;
        this.data = data;
    }
}
