package com.kodluyoruz.playlistapi.Exceptions;

import lombok.Getter;

import java.util.Date;

@Getter
public class ExceptionDetails {
    private String errorMessage;
    private Date time;

    public ExceptionDetails(String errorMessage, Date time) {
        this.errorMessage = errorMessage;
        this.time = time;
    }
}
