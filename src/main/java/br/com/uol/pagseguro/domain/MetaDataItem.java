package br.com.uol.pagseguro.domain;

import br.com.uol.pagseguro.enums.MetaDataItemKey;
import br.com.uol.pagseguro.helper.PagSeguroUtil;

public class MetaDataItem {

    private MetaDataItemKey key;

    private String value;

    private Integer group;

    /**
     * Initializes a newly created object of this type
     *
     */
    public MetaDataItem() {
    }

    /**
     * Initializes a newly created object of this type with the specified
     * arguments
     *
     * @param key - the metaDataItemKey of the object
     * @param value - the metaDataItemValue of the object
     */
    public MetaDataItem(final MetaDataItemKey key, final String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Initializes a newly created object of this type with the specified
     * arguments
     *
     * @param key - the metaDataItemKey of the object
     * @param value - the metaDataItemValue of the object
     * @param group - the metaDataItemGroup of the object
     */
    public MetaDataItem(final MetaDataItemKey key, final String value, final Integer group) {
        setKey(key);
        setValue(value);
        setGroup(group);
    }

    /**
     * @return this MetaDataItem group
     */
    public Integer getGroup() {
        return group;
    }

    /**
     * @return this MetaDataItem key
     */
    public MetaDataItemKey getKey() {
        return key;
    }

    /**
     * @return this MetaDataItem value
     */
    public String getValue() {
        return value;
    }

    /**
     * Normalize a given metaDataItemValue
     *
     * @param value - the metaDataItemValue of the object
     */
    private String normalizeValue(String value) {

        if (getKey().equals(MetaDataItemKey.PASSENGER_CPF)) {
            value = PagSeguroUtil.getOnlyNumbers(value);
        }

        if (getKey().equals(MetaDataItemKey.TIME_IN_GAME_DAYS)) {
            value = PagSeguroUtil.getOnlyNumbers(value);
        }

        return value;
    }

    /**
     * @param group - new group for this MetaDataItem
     */
    public void setGroup(final Integer group) {
        this.group = group;
    }

    /**
     * @param key - new key for this MetaDataItem
     */
    public void setKey(final MetaDataItemKey key) {
        this.key = key;
    }

    /**
     * @param value - new normalized value for this MetaDataItem
     */
    public void setValue(final String value) {
        this.value = normalizeValue(value);
    }
}
