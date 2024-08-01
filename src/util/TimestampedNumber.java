package util;

import java.time.Instant;

public class TimestampedNumber {
    private final int number;
    private final Instant timestamp;

    public TimestampedNumber(int number) {
        this.number = number;
        this.timestamp = Instant.now();
    }

    public int value() {
        return number;
    }

    public Instant timestamp() {
        return timestamp;
    }
}
