package consumer;

import util.TimestampedNumber;

import java.util.Deque;
import java.util.LinkedList;

import static util.Constants.FIVE_MINUTES_IN_MILLIS;

public class AverageCalculatingConsumer implements Consumer{
    private final Deque<TimestampedNumber> deque;

    public AverageCalculatingConsumer() {
        deque = new LinkedList<>();
    }

    @Override
    public void accept(int number) {
        deque.addFirst(new TimestampedNumber(number));
        cleanUpOldEntries();
    }

    @Override
    public double mean() {
        cleanUpOldEntries();
        return deque.stream()
                .mapToInt(TimestampedNumber::value)
                .average()
                .orElse(0.0);
    }

    private void cleanUpOldEntries() {
        long cutoffTime = System.currentTimeMillis() - FIVE_MINUTES_IN_MILLIS; // 5 minutes in milliseconds
        while (!deque.isEmpty() && deque.peekLast().timestamp().toEpochMilli() < cutoffTime) {
            deque.removeLast();
        }
    }
}
