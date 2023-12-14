package com.hanwha.robotics.user.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest extends SearchRequest {
    static int DEFAULT_SIZE = 10;
    static int MAX_SIZE = 50;

    private int page = 1;
    private int size = 10;
    private int offset = 0;
    private String sort;
    private String direction;

    private String categoryLang;
    private String selectLang;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
		this.setOffest();
    }

    public void setSize(int size) {
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
		this.setOffest();
    }

    public void setOffest(){
        this.offset = (page - 1)* size;
    }

}
