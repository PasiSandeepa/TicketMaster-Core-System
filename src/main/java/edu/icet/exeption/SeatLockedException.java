package edu.icet.exeption;

import lombok.Getter;

@Getter
public class SeatLockedException extends RuntimeException {
    private final long remainingSeconds;

    public SeatLockedException(long remainingSeconds) {
        super("Seat is currently locked.");
        this.remainingSeconds = remainingSeconds;
    }
}