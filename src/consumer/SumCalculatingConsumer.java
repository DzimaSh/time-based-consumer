package consumer;

import util.TimestampedNumber;

import java.util.ArrayDeque;
import java.util.Deque;

import static util.Constants.FIVE_MINUTES_IN_MILLIS;

public class SumCalculatingConsumer implements Consumer {
    private final Deque<TimestampedNumber> deque;
    private long sum;

    public SumCalculatingConsumer() {
        deque = new ArrayDeque<>();
        sum = 0;
    }

    @Override
    public void accept(int number) {
        deque.addFirst(new TimestampedNumber(number));
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
        while (!deque.isEmpty()) {
            var last = deque.peekLast();
            if (last.timestamp().toEpochMilli() >= cutoffTime) {
                break;
            }
            sum -= last.value();
            deque.removeLast();
        }
    }
}

