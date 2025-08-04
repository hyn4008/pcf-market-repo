package com.project.pcf_market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    // 기본 생성자 (메세지 없이 사용할 경우)
    public NotFoundException() {
        super("404: Not Found");
    }

    // 메시지 전달용 생성자
    public NotFoundException(String message) {
        super(message);
    }
}
