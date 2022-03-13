package com.codegym.model;

public class SearchResult {
    private String name;
    private String url;
    private String previewContent;

    public SearchResult() {
    }

    public SearchResult(String name, String url, String previewContent) {
        this.name = name;
        this.url = url;
        this.previewContent = previewContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPreviewContent() {
        return previewContent;
    }

    public void setPreviewContent(String previewContent) {
        this.previewContent = previewContent;
    }
}
