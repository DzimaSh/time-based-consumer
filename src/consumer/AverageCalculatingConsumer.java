package consumer;

import java.time.Instant;
import java.util.Deque;
import java.util.LinkedList;

public class AverageCalculatingConsumer implements Consumer{
    private final Deque<TimestampedNumber> deque;

    public AverageCalculatingConsumer() {
        deque = new LinkedList<>();
    }

    @Override
    public void accept(int number) {
        deque.addLast(new TimestampedNumber(number));
        cleanUpOldEntries();
    }

    @Override
    public double mean() {
        cleanUpOldEntries();
        return deque.stream()
                .mapToInt(entry -> entry.number)
                .average()
                .orElse(0.0);
    }

    private void cleanUpOldEntries() {
        long cutoffTime = System.currentTimeMillis() - FIVE_MINUTES_IN_MILLIS; // 5 minutes in milliseconds
        while (!deque.isEmpty() && deque.peekFirst().timestamp.toEpochMilli() < cutoffTime) {
            deque.pollFirst();
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
