package com.streamyear.course.common.entity;

public class HttpResult {
    /**
     * 状态码
     */
    private int statusCode;

    /**
     * 内容
     */
    private String content;

    public HttpResult(int statusCode, String s) {
        this.statusCode = statusCode;
        this.content = s;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "statusCode=" + statusCode +
                ", content='" + content + '\'' +
                '}';
    }
}
