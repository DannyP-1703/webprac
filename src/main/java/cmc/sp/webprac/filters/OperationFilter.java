package cmc.sp.webprac.filters;

import java.sql.Timestamp;
import java.time.Instant;

public class OperationFilter {
    private final TimeInterval interval;

    public static class TimeInterval {
        public Timestamp min = Timestamp.from(Instant.MIN);
        public Timestamp max = Timestamp.from(Instant.MAX);

        public TimeInterval(Timestamp min, Timestamp max) {
            if (min != null) {
                this.min = min;
            }
            if (max != null) {
                this.max = max;
            }
        }
    }
    public static class Builder {
        private TimeInterval interval;
        public Builder() {}
        public Builder interval(Timestamp min, Timestamp max) {
            this.interval = new TimeInterval(min, max);
            return this;
        }

        public OperationFilter build() {
            return new OperationFilter(this);
        }
    }

    private OperationFilter(Builder builder) {
        this.interval = builder.interval;
    }

    public TimeInterval getInterval() {
        return interval;
    }
}
