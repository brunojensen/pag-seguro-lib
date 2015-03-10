package br.com.uol.pagseguro.enums;

public enum PaymentMode {

    DEFAULT("default"),

    GATEWAY("gateway");

    public static PaymentMode fromValue(final String value) {
        try {
            return PaymentMode.valueOf(value.toUpperCase());
        } catch (final Exception e) {
            return null;
        }
    }

    private final String value;

    private PaymentMode(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
