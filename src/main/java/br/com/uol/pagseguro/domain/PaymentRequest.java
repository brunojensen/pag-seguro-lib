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

package br.com.uol.pagseguro.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.DocumentType;
import br.com.uol.pagseguro.enums.MetaDataItemKey;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;

/**
 * Represents a payment request
 *
 * @deprecated use {@link Checkout} instead.
 */
@Deprecated
public class PaymentRequest {

    /**
     * New Checkout class
     */
    private final Checkout checkout;

    /**
     * Shipping information associated with this checkout request
     */
    private Shipping shipping;

    /**
     * Initializes a new instance of the PaymentRequest class
     */
    public PaymentRequest() {
        checkout = new Checkout();
    }

    /**
     * Add indexed parameters in the checkout request.
     *
     * See availables <a href=
     * "https://pagseguro.uol.com.br/v2/guia-de-integracao/api-de-pagamentos.html"
     * >parameters</a>
     *
     * @param name
     * @param value
     * @param index
     */
    public void addIndexedParameter(final String name, final String value, final Integer index) {
        checkout.addIndexedParameter(name, value, index);
    }

    /**
     * Adds a new product/item in this checkout request
     *
     * @see Item
     *
     * @param item
     */
    public void addItem(final Item item) {
        checkout.addItem(item);
    }

    /**
     * Adds a new product/item in this checkout request
     *
     * @see Item
     *
     * @param id
     * @param description
     * @param quantity
     * @param amount
     * @param weight
     * @param shippingCost
     */
    public void addItem(final String id,
                        final String description,
                        final Integer quantity,
                        final BigDecimal amount,
                        final Long weight,
                        final BigDecimal shippingCost) {
        checkout.addItem(id, description, quantity, amount, weight, shippingCost);
    }

    /**
     * Add extra information, not grouped, on the checkout request.
     *
     * @param key
     * @param value
     */
    public void addMetaDataItem(final MetaDataItemKey key, final String value) {
        checkout.addMetaDataItem(key, value);
    }

    /**
     * Add extra information, grouped, on the checkout request.
     *
     * @param key
     * @param value
     * @param group
     */
    public void addMetaDataItem(final MetaDataItemKey key, final String value, final Integer group) {
        checkout.addMetaDataItem(key, value, group);
    }

    /**
     * Add parameters in the checkout request.
     *
     * See availables <a href=
     * "https://pagseguro.uol.com.br/v2/guia-de-integracao/api-de-pagamentos.html"
     * >parameters</a>
     *
     * @param name
     * @param value
     */
    public void addParameter(final String name, final String value) {
        checkout.addParameter(name, value);
    }

    /**
     * Add document for sender documents list
     *
     * @param type
     * @param value
     */
    public void addSenderDocument(final DocumentType type, final String value) {
        checkout.addSenderDocument(type, value);
    }

    /**
     * Add document for sender documents list
     *
     * @param document
     */
    public void addSenderDocument(final SenderDocument document) {
        checkout.addSenderDocument(document);
    }

    /**
     * @return the currency Example: BRL
     */
    public Currency getCurrency() {
        return checkout.getCurrency();
    }

    /**
     * This value can be used to add an extra charge to the transaction or
     * provide a discount in the case ExtraAmount is a negative value.
     *
     * @return the extra amount
     */
    public BigDecimal getExtraAmount() {
        return checkout.getExtraAmount();
    }

    /**
     * @return the items/products list in this checkout request
     * @see Item
     */
    public List<Item> getItems() {
        return checkout.getItems();
    }

    /**
     * @return the max age of this checkout request
     *
     *         After this checkout request is submitted, the payment code
     *         returned will remain valid for the period specified.
     */
    public BigInteger getMaxAge() {
        return checkout.getMaxAge();
    }

    /**
     * After this checkout request is submitted, the payment redirect uri
     * returned by the checkout web service will remain valid for the number of
     * uses specified here.
     *
     * @return the max uses configured for this checkout request
     */
    public BigInteger getMaxUses() {
        return checkout.getMaxUses();
    }

    /**
     * Get MetaData
     *
     * @return MetaData
     */
    public MetaData getMetaData() {
        return checkout.getMetaData();
    }

    /**
     * Get the notification status url
     *
     * @return String
     */
    public String getNotificationURL() {
        return checkout.getNotificationURL();
    }

    /**
     * Gets parameter for PagSeguro checkout requests
     *
     * @return Parameter
     */
    public Parameter getParameter() {
        return checkout.getParameter();
    }

    /**
     * Uri to where the PagSeguro checkout page should redirect the user after
     * the payment information is processed. Typically this is a confirmation
     * page on your web site.
     *
     * @return the redirectURL
     */
    public String getRedirectURL() {
        return checkout.getRedirectURL();
    }

    /**
     * @return the reference of this checkout request
     */
    public String getReference() {
        return checkout.getReference();
    }

    /**
     * @return the sender
     *
     *         Party that will be sending the Uri to where the PagSeguro
     *         checkout page should redirect the user after the payment
     *         information is processed. money
     */
    public Sender getSender() {
        return checkout.getSender();
    }

    /**
     * @return the shipping information for this checkout request
     * @see Shipping
     */
    public Shipping getShipping() {
        return shipping;
    }

    /**
     * Calls the PagSeguro web service and register this request for payment
     *
     * @param credentials
     * @return The URL to where the user needs to be redirected to in order to
     *         complete the payment process
     * @throws PagSeguroServiceException
     */
    public String register(final Credentials credentials) throws PagSeguroServiceException {
        return checkout.register(credentials);
    }

    /**
     * Calls the PagSeguro web service and register this request for payment
     *
     * @param credentials
     * @param onlyCheckoutCode
     * @return The checkout code
     * @throws PagSeguroServiceException
     */
    public String register(final Credentials credentials, final Boolean onlyCheckoutCode)
        throws PagSeguroServiceException {
        return checkout.register(credentials, onlyCheckoutCode);
    }

    /**
     * Sets the currency
     *
     * @param currency
     */
    public void setCurrency(final Currency currency) {
        checkout.setCurrency(currency);
    }

    /**
     * Sets the extra amount This value can be used to add an extra charge to
     * the transaction or provide a discount in the case <b>extraAmount</b> is a
     * negative value.
     *
     * @param extraAmount
     */
    public void setExtraAmount(final BigDecimal extraAmount) {
        checkout.setExtraAmount(extraAmount);
    }

    /**
     * Sets the items/products list in this checkout request
     *
     * @see Item
     *
     * @param items
     */
    public void setItems(final List<Item> items) {
        checkout.setItems(items);
    }

    /**
     * Sets the max age of this payment request After this checkout request is
     * submitted, the payment code returned will remain valid for the period
     * specified here.
     *
     * @param maxAge
     */
    public void setMaxAge(final BigInteger maxAge) {
        checkout.setMaxAge(maxAge);
    }

    /**
     * Sets the max uses of this checkout request
     *
     * After this checkout request is submitted, the payment redirect uri
     * returned by the checkout web service will remain valid for the number of
     * uses specified here.
     *
     * @param maxUses
     */
    public void setMaxUses(final BigInteger maxUses) {
        checkout.setMaxUses(maxUses);
    }

    /**
     * Sets metadata for PagSeguro checkout requests
     *
     * @param MetaData metaData
     */
    public void setMetaData(final MetaData metaData) {
        checkout.setMetaData(metaData);
    }

    /**
     * Sets the url that PagSeguro will send the new notifications statuses
     *
     * @param notificationURL
     */
    public void setNotificationURL(final String notificationURL) {
        checkout.setNotificationURL(notificationURL);
    }

    /**
     * Sets parameter for PagSeguro checkout requests
     *
     * @param parameter
     */
    public void setParameter(final Parameter parameter) {
        checkout.setParameter(parameter);
    }

    /**
     * Sets the redirect URL
     *
     * Uri to where the PagSeguro checkout page should redirect the user after
     * the payment information is processed. Typically this is a confirmation
     * page on your web site.
     *
     * @param redirectURL
     */
    public void setRedirectURL(final String redirectURL) {
        checkout.setRedirectURL(redirectURL);
    }

    /**
     * Sets the reference of this checkout request
     *
     * @param reference
     */
    public void setReference(final String reference) {
        checkout.setReference(reference);
    }

    /**
     * Sets the Sender, party that will be sending the money
     *
     * @param sender
     *
     * @see Sender
     */
    public void setSender(final Sender sender) {
        checkout.setSender(sender);
    }

    /**
     * Sets the Sender, party that will be sending the money
     *
     * @param name the sender full name or at least first and surname
     * @param email the sender e-mail address
     */
    public void setSender(final String name, final String email) {
        checkout.setSender(name, email);
    }

    /**
     * Sets the Sender, party that will be sending the money
     *
     * @param name the sender full name or at least first and surname
     * @param email the sender e-mail address
     * @param number
     */
    public void setSender(final String name, final String email, final Phone number) {
        checkout.setSender(name, email, number);
    }

    /**
     * Sets the Sender, party that will be sending the money
     *
     * @param name the sender full name or at least first and surname
     * @param email the sender e-mail address
     * @param number
     * @param documentType
     * @param documentValue
     */
    public void setSender(final String name,
                          final String email,
                          final Phone number,
                          final DocumentType documentType,
                          final String documentValue) {
        checkout.setSender(name, email, number, documentType, documentValue);
    }

    /**
     * Sets the Sender, party that will be sending the money
     *
     * @param name the sender full name or at least first and surname
     * @param email the sender e-mail address
     * @param number
     * @param documentType
     * @param documentValue
     * @param bornDate
     */
    public void setSender(final String name,
                          final String email,
                          final Phone number,
                          final DocumentType documentType,
                          final String documentValue,
                          final String bornDate) {
        checkout.setSender(name, email, number, documentType, documentValue, bornDate);
    }

    /**
     * Sets the Sender, party that will be sending the money
     *
     * @param name the sender full name or at least first and surname
     * @param email the sender e-mail address
     * @param areaCode
     * @param number
     */
    public void setSender(final String name, final String email, final Phone number, final String bornDate) {
        checkout.setSender(name, email, number, bornDate);
    }

    /**
     * Sets the Sender, party that will be sending the money
     *
     * @param name the sender full name or at least first and surname
     * @param email the sender e-mail address
     * @param areaCode
     * @param number
     */
    public void setSender(final String name, final String email, final String areaCode, final String number) {
        checkout.setSender(name, email, areaCode, number);
    }

    /**
     * Sets the Sender, party that will be sending the money
     *
     * @param name the sender full name or at least first and surname
     * @param email the sender e-mail address
     * @param areaCode
     * @param number
     * @param documentType
     * @param documentValue
     */
    public void setSender(final String name,
                          final String email,
                          final String areaCode,
                          final String number,
                          final DocumentType documentType,
                          final String documentValue) {
        checkout.setSender(name, email, areaCode, number, documentType, documentValue);
    }

    /**
     * Sets the Sender, party that will be sending the money
     *
     * @param name the sender full name or at least first and surname
     * @param email the sender e-mail address
     * @param areaCode
     * @param number
     * @param documentType
     * @param documentValue
     * @param bornDate
     */
    public void setSender(final String name,
                          final String email,
                          final String areaCode,
                          final String number,
                          final DocumentType documentType,
                          final String documentValue,
                          final String bornDate) {
        checkout.setSender(name, email, areaCode, number, documentType, documentValue, bornDate);
    }

    /**
     * Sets the Sender, party that will be sending the money
     *
     * @param name the sender full name or at least first and surname
     * @param email the sender e-mail address
     * @param areaCode
     * @param number
     * @param bornDate
     */
    public void setSender(final String name,
                          final String email,
                          final String areaCode,
                          final String number,
                          final String bornDate) {
        checkout.setSender(name, email, areaCode, number, bornDate);
    }

    /**
     * Sets the Sender phone number, phone of the party that will be sending the
     * money
     *
     * @param areaCode
     * @param number
     */
    public void setSenderPhone(final String areaCode, final String number) {
        checkout.setSenderPhone(areaCode, number);
    }

    /**
     * Sets the shipping information for this checkout request
     *
     * @see Shipping
     *
     * @param shipping
     */
    public void setShipping(final Shipping shipping) {
        checkout.setShipping(shipping);
    }

    /**
     * Sets the shipping information for this checkout request
     *
     * @param type
     * @param country
     * @param state
     * @param city
     * @param district
     * @param postalCode
     * @param street
     * @param number
     * @param complement
     */
    public void setShipping(final ShippingType type,
                            final String country,
                            final String state,
                            final String city,
                            final String district,
                            final String postalCode,
                            final String street,
                            final String number,
                            final String complement) {
        checkout.setShipping(type, country, state, city, district, postalCode, street, number, complement);
    }

    /**
     * /** Sets the shipping information for this checkout request
     *
     * @param type
     * @param country
     * @param state
     * @param city
     * @param district
     * @param postalCode
     * @param street
     * @param number
     * @param complement
     * @param cost
     */
    public void setShipping(final ShippingType type,
                            final String country,
                            final String state,
                            final String city,
                            final String district,
                            final String postalCode,
                            final String street,
                            final String number,
                            final String complement,
                            final BigDecimal cost) {
        checkout.setShipping(type, country, state, city, district, postalCode, street, number, complement, cost);
    }

    /**
     * Sets the shipping address for this checkout request
     *
     * @param address
     */
    public void setShippingAddress(final Address address) {
        checkout.setShippingAddress(address);
    }

    /**
     * Sets the shipping address for this checkout request
     *
     * @param country
     * @param state
     * @param city
     * @param district
     * @param postalCode
     * @param street
     * @param number
     * @param complement
     */
    public void setShippingAddress(final String country,
                                   final String state,
                                   final String city,
                                   final String district,
                                   final String postalCode,
                                   final String street,
                                   final String number,
                                   final String complement) {
        checkout.setShippingAddress(country, state, city, district, postalCode, street, number, complement);
    }

    /**
     * Sets the value of the shipping. <br />
     * Use it when you calculate the value of the shipping.
     *
     * @param cost
     */
    public void setShippingCost(final BigDecimal cost) {
        checkout.setShippingCost(cost);
    }

    /**
     * Sets the shipping type for this checkout request
     *
     * @see ShippingType
     *
     * @param type
     */
    public void setShippingType(final ShippingType type) {
        checkout.setShippingType(type);
    }

    /**
     * @return string
     */
    @Override
    public String toString() {
        return checkout.toString();
    }

    /**
     * Verify if the address of notificationURL or redirectURL is for tests and
     * return empty
     *
     * @param type url
     * @return type
     */
    public String verifyURLTest(final String url) {
        return checkout.verifyURLTest(url);
    }
}
