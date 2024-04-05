package cmc.sp.webprac.filters;

import cmc.sp.webprac.enums.SubscriptionType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ServiceFilter {
    private final List<SubscriptionType> subscriptionTypes;
    private final String searchText;
    private final SubscriptionFee subscriptionFee;
    public static class SubscriptionFee {
        public BigDecimal min = BigDecimal.valueOf(0);
        public BigDecimal max = BigDecimal.valueOf(Long.MAX_VALUE);
        public SubscriptionFee(BigDecimal min, BigDecimal max) {
            if (min != null) {
                this.min = min;
            }
            if (max != null) {
                this.max = max;
            }
        }
    }
    public static class Builder {
        private final List<SubscriptionType> subscriptionTypes;
        private String searchText = null;
        private SubscriptionFee subscriptionFee = null;

        public Builder(List<SubscriptionType> subscriptionTypes) {
            this.subscriptionTypes = subscriptionTypes;
        }

        public ServiceFilter build() {
            return new ServiceFilter(this);
        }

        public Builder searchText(String text) {
            this.searchText = text;
            return this;
        }

        public Builder subsctiptionFee(BigDecimal min, BigDecimal max) {
            this.subscriptionFee = new SubscriptionFee(min, max);
            return this;
        }
    }

    private ServiceFilter(Builder builder) {
        this.subscriptionTypes = builder.subscriptionTypes;
        this.searchText = builder.searchText;
        this.subscriptionFee = builder.subscriptionFee;
    }

    public List<SubscriptionType> getSubscriptionTypes() {
        return subscriptionTypes;
    }

    public String getSearchText() {
        return searchText;
    }

    public SubscriptionFee getSubscriptionFee() {
        return subscriptionFee;
    }
}

