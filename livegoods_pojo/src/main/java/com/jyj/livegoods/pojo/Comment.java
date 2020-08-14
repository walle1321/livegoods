package com.jyj.livegoods.pojo;

import lombok.Data;

@Data
public class Comment {
    private String id;
    private String username;
    private String comment;
    private int star;
    private String itemId;
}
