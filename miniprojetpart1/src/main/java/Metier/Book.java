package Metier;

import java.io.Serializable;
import java.util.List;


import java.io.Serializable;
import java.util.List;


public class Book implements Serializable {

    private String title ;
    private List<String> authors;
    private String editor;
    private String year;
    private  String summary;
    private int cover ;
    private int iconCover;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public int getIconCover() {
        return iconCover;
    }

    public void setIconCover(int iconCover) {
        this.iconCover = iconCover;
    }
}

