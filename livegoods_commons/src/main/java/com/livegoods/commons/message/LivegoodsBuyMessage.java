package com.livegoods.commons.message;

import lombok.Data;

@Data
public class LivegoodsBuyMessage extends LivegoodsMessage {
    private String itemId;
    private String username;
}
