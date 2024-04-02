package cmc.sp.webprac.enums;

public enum AccountStatus {
    ACTIVE("Активен"),
    BLOCKED("Заблокирован"),
    CLOSED("Закрыт");

    private final String value;

    AccountStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
