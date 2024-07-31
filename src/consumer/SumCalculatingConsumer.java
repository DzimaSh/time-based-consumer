package consumer;

import java.time.Instant;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class SumCalculatingConsumer implements Consumer {
    private final Deque<TimestampedNumber> deque;
    private long sum;

    public SumCalculatingConsumer() {
        deque = new LinkedList<>();
        sum = 0;
    }

    @Override
    public void accept(int number) {
        deque.addLast(new TimestampedNumber(number));
        sum += number;
        cleanUpOldEntries();
    }

    @Override
    public double mean() {
        cleanUpOldEntries();
        if (deque.isEmpty()) {
            return 0.0;
        }
        return (double) sum / deque.size();
    }

    private void cleanUpOldEntries() {
        long cutoffTime = System.currentTimeMillis() - FIVE_MINUTES_IN_MILLIS;
        while (!deque.isEmpty() && deque.peekFirst().timestamp.toEpochMilli() < cutoffTime) {
            sum -= Objects.requireNonNull(deque.pollFirst()).number;
        }
    }

    private static class TimestampedNumber {
        private final int number;
        private final Instant timestamp;

        private TimestampedNumber(int number) {
            this.number = number;
            this.timestamp = Instant.now();
        }
    }
}

