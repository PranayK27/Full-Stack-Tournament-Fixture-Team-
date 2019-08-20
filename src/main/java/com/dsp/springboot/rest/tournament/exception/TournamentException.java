package com.dsp.springboot.rest.tournament.exception;

import org.springframework.stereotype.Component;

public class TournamentException extends Exception {

    public TournamentException(String message) {
        super(message);
    }
}
