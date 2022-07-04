package com.BackendHomework2.web.dto.common;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
public class CommonResponse {
    @Builder.Default // Builder default 지정
    private String id = UUID.randomUUID().toString(); // uuid
    @Builder.Default
    private Date createAt = new Date(); // date
    private int status;
    private String message;
    private Object list;
}
