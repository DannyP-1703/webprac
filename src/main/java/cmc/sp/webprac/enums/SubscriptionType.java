package cmc.sp.webprac.enums;

public enum SubscriptionType {
    ONETIME("Без а/п"),
    DAILY("Ежедневно"),
    MONTHLY("Ежемесячно"),
    ANNUAL("Ежегодно");

    private final String value;

    SubscriptionType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
