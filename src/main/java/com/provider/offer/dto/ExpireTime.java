package com.provider.offer.dto;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ExpireTime {

    private Integer time;

    private TimeUnit unit;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }


    public static final class ExpireTimeBuilder {
        private Integer time;
        private TimeUnit unit;

        private ExpireTimeBuilder() {
        }

        public static ExpireTimeBuilder anExpireTime() {
            return new ExpireTimeBuilder();
        }

        public ExpireTimeBuilder withTime(Integer time) {
            this.time = time;
            return this;
        }

        public ExpireTimeBuilder withUnit(TimeUnit unit) {
            this.unit = unit;
            return this;
        }

        public ExpireTime build() {
            ExpireTime expireTime = new ExpireTime();
            expireTime.setTime(time);
            expireTime.setUnit(unit);
            return expireTime;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpireTime that = (ExpireTime) o;
        return time.equals(that.time) &&
                unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, unit);
    }
}
