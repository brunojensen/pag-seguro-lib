package br.com.uol.pagseguro.enums;

public enum PermissionType {

    CREATE_CHECKOUTS("CREATE_CHECKOUTS"),
    RECEIVE_TRANSACTION_NOTIFICATIONS("RECEIVE_TRANSACTION_NOTIFICATIONS"),
    SEARCH_TRANSACTIONS("SEARCH_TRANSACTIONS"),
    MANAGE_PAYMENT_PRE_APPROVALS("MANAGE_PAYMENT_PRE_APPROVALS"),
    DIRECT_PAYMENT("DIRECT_PAYMENT");

    public static PermissionType fromValue(final String value) {

        for (final PermissionType permissionType : PermissionType.values()) {
            if (permissionType.value.equals(value)) {
                return permissionType;
            }
        }
        return null;
    }

    private String value;

    private PermissionType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
