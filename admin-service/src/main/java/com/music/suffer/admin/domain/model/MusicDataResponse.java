package com.music.suffer.admin.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MusicDataResponse {
    private long id;
    private int position;
}
