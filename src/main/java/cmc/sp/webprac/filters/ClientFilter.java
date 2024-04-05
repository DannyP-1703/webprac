package cmc.sp.webprac.filters;

import java.sql.Timestamp;
import java.time.Instant;

public class ClientFilter {
    private final Boolean isIndividual;
    private final Boolean isEntity;
    private final String partOfName;
    private final String startOfRegistrationNumber;
    private final String region;
    private final NumberOfAccounts numberOfAccounts;
    private final Boolean hasBlockedAccounts;
    private final ClientSince clientSince;
    public static class NumberOfAccounts {
        public Integer min = 0;
        public Integer max = Integer.MAX_VALUE;
        public NumberOfAccounts(Integer min, Integer max) {
            if (min != null) {
                this.min = min;
            }
            if (max != null) {
                this.max = max;
            }
        }
    }
    public static class ClientSince {
        public Timestamp min = Timestamp.from(Instant.MIN);
        public Timestamp max = Timestamp.from(Instant.MAX);
        public ClientSince(Timestamp min, Timestamp max) {
            if (min != null) {
                this.min = min;
            }
            if (max != null) {
                this.max = max;
            }
        }
    }
    public static class Builder {
        private final Boolean isIndividual;
        private final Boolean isEntity;
        private String partOfName = null;
        private String startOfRegistrationNumber = null;
        private String region = null;
        private NumberOfAccounts numberOfAccounts = null;
        private Boolean hasBlockedAccounts = null;
        private ClientSince clientSince = null;

        public Builder(Boolean isIndividual, Boolean isEntity) {
            this.isIndividual = isIndividual;
            this.isEntity = isEntity;
        }

        public ClientFilter build() {
            return new ClientFilter(this);
        }

        public Builder partOfName(String text) {
            this.partOfName = text;
            return this;
        }

        public Builder startOfRegistrationNumber(String text) {
            this.startOfRegistrationNumber = text;
            return this;
        }

        public Builder region(String text) {
            this.region = text;
            return this;
        }

        public Builder numberOfAccounts(Integer min, Integer max) {
            this.numberOfAccounts = new NumberOfAccounts(min, max);
            return this;
        }

        public Builder hasBlockedAccounts(Boolean hasBlockedAccounts) {
            this.hasBlockedAccounts = hasBlockedAccounts;
            return this;
        }

        public Builder clientSince(Timestamp min, Timestamp max) {
            this.clientSince = new ClientSince(min, max);
            return this;
        }

    }

    private ClientFilter(Builder builder) {
        this.isIndividual = builder.isIndividual;
        this.isEntity = builder.isEntity;
        this.partOfName = builder.partOfName;
        this.startOfRegistrationNumber = builder.startOfRegistrationNumber;
        this.region = builder.region;
        this.numberOfAccounts = builder.numberOfAccounts;
        this.hasBlockedAccounts = builder.hasBlockedAccounts;
        this.clientSince = builder.clientSince;
    }

    public Boolean getIndividual() {
        return isIndividual;
    }

    public Boolean getEntity() {
        return isEntity;
    }

    public String getPartOfName() {
        return partOfName;
    }

    public String getStartOfRegistrationNumber() {
        return startOfRegistrationNumber;
    }

    public String getRegion() {
        return region;
    }

    public NumberOfAccounts getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public Boolean getHasBlockedAccounts() {
        return hasBlockedAccounts;
    }

    public ClientSince getClientSince() {
        return clientSince;
    }
}
