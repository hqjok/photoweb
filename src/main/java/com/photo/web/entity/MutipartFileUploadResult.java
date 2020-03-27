package com.photo.web.entity;

import java.util.List;

/**
 * @Create 2020-02-26 17:04
 */
public class MutipartFileUploadResult {

    private Integer code;
    private String msg;
    private List data;
    private Integer articleType;

    public MutipartFileUploadResult(Integer code, String msg, Integer articleType, List data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.articleType = articleType;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
