/*
 ************************************************************************
 Copyright [2011] [PagSeguro Internet Ltda.]

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ************************************************************************
 */

package br.com.uol.pagseguro.enums;

/**
 * Enum constants that represents notificationTypes that can be returned by
 * PagSeguro
 */
public enum NotificationType {

    TRANSACTION("THIS NOTIFICATION REPRESENTS A TRANSACTION", "transaction"),

    UNKNOWN_TYPE("UNKNOWN TYPE. SEE ONLINE DOCUMENTATION", "unknown");

    /**
     * Returns the enum constant of this type with the specified value. If a
     * given value are not recognized return a generic enum constant
     * <code>UNKNOWN_TYPE</code>
     *
     * @param value - the value of the enum constant to be returned
     * @return the enum constant from a given value
     */
    public static NotificationType fromValue(final String value) {

        for (final NotificationType notificationType : NotificationType.values()) {
            if (notificationType.value.equals(value)) {
                return notificationType;
            }
        }

        UNKNOWN_TYPE.setValue(value);
        return UNKNOWN_TYPE;

    }

    private String description;

    private String value;

    /**
     * Initializes a newly created enum constant of this type with the specified
     * arguments
     *
     * @param description - the description of the enum constant
     * @param value - the value of the enum constant
     */
    NotificationType(final String description, final String value) {
        this.description = description;
        this.value = value;
    }

    /**
     * @return this enum constant description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return this enum constant value
     */
    public String getValue() {
        return value;

    }

    /**
     * @param description - new description for this enum constant
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param value - new value for this enum constant
     */
    public void setValue(final String value) {
        this.value = value;

    }

}
