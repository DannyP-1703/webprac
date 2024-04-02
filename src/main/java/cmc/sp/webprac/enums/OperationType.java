package cmc.sp.webprac.enums;

public enum OperationType {
    TOPUP("Пополнение"),
    SERVICE_CONNECT("Подключение услуги"),
    SERVICE_SUBSCRIPTION("Абонентская плата"),
    SERVICE_DISCONNECT("Отключение услуги"),
    ACCOUNT_OPEN("Открытие счёта"),
    ACCOUNT_BLOCK("Блокировка счёта"),
    ACCOUNT_UNBLOCK("Разблокировка счёта"),
    ACCOUNT_CLOSE("Закрытие счёта");

    private final String value;
    OperationType(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }
}
