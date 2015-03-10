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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.uol.pagseguro.enums.TransactionStatus;
import br.com.uol.pagseguro.enums.TransactionType;

/**
 * Represents a PagSeguro transaction
 */
public class Transaction {

    private static final int MIN_VALUE = 0;

    /** Date the last notification about this transaction was sent */
    private Date lastEventDate;

    /** Transaction date */
    private Date date;

    /** Transaction code */
    private String code;

    /**
     * A reference to associate the PagSeguro transaction to a transaction in
     * your system.
     */
    private String reference;

    /** Transaction type */
    private TransactionType type;

    /** Transaction status */
    private TransactionStatus status;

    /** Transaction payment method */
    private PaymentMethod paymentMethod;

    /** Transaction gross amount */
    private BigDecimal grossAmount;

    /** Transaction discount amount */
    private BigDecimal discountAmount;

    /** Transaction fee amount */
    private BigDecimal feeAmount;

    /** Transaction net amount */
    private BigDecimal netAmount;

    /** Transaction extra/discount amount */
    private BigDecimal extraAmount;

    /** Transaction installment count */
    private Integer installmentCount;

    /** Transaction items count */
    private Integer itemCount;

    /** Transaction escrow end date */
    private Date escrowEndDate;

    /** Cancellation source */
    private String cancellationSource;

    /** Transaction payment link */
    private String paymentLink;

    /**
     * item/product list in this transaction
     *
     * @see Item
     */
    private List<Item> items;

    /**
     * Payer information, who is sending money
     *
     * @see Sender
     */
    private Sender sender;

    /**
     * Shipping information
     *
     * @see Shipping
     */
    private Shipping shipping;

    /**
     * Initializes a new instance of the Transaction class
     */
    public Transaction() {

        date = new Date();
        paymentMethod = new PaymentMethod();
        items = new ArrayList<Item>();
        itemCount = Integer.valueOf(Transaction.MIN_VALUE);
        sender = new Sender();
        shipping = new Shipping();

    }

    /**
     * Get Cancellation Source
     *
     * @return String
     */
    public String getCancellationSource() {
        return cancellationSource;
    }

    /**
     * @return the transaction code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the transaction date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the discount amount
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Get Escrow End Date
     *
     * @return Date
     */
    public Date getEscrowEndDate() {
        return escrowEndDate;
    }

    /**
     * @return the extra amount
     */
    public BigDecimal getExtraAmount() {
        return extraAmount;
    }

    /**
     * @return the fee amount
     */
    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    /**
     * @return the transaction gross amount
     */
    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    /**
     * @return the installment count
     */
    public Integer getInstallmentCount() {
        return installmentCount;
    }

    /**
     * @return the items/products count in this transaction
     */
    public Integer getItemCount() {
        return itemCount;
    }

    /**
     * @return the items/products list in this transaction
     * @see Item
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Date the last notification about this transaction was sent
     *
     * @return the last event date
     */
    public Date getLastEventDate() {
        return lastEventDate;
    }

    /**
     * @return the net amount
     */
    public BigDecimal getNetAmount() {
        return netAmount;
    }

    /**
     * Get Payment Link
     *
     * @return String
     */
    public String getPaymentLink() {
        return paymentLink;
    }

    /**
     * @return the payment method used in this transaction
     * @see PaymentMethod
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * You can use the reference code to store an identifier so you can
     * associate the PagSeguro transaction to a transaction in your system.
     *
     * @return the reference code
     */
    public String getReference() {
        return reference;
    }

    /**
     * @return the sender information
     * @see Sender
     */
    public Sender getSender() {
        return sender;
    }

    /**
     * @return the shipping information
     * @see Shipping
     */
    public Shipping getShipping() {
        return shipping;
    }

    /**
     * @return the transaction status
     * @see TransactionStatus
     */
    public TransactionStatus getStatus() {
        return status;
    }

    /**
     * @return the transaction Type
     * @see TransactionType
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Set Cancellation Source
     *
     * @param cancellationSource
     */
    public void setCancellationSource(final String cancellationSource) {
        this.cancellationSource = cancellationSource;
    }

    /**
     * Sets the transaction code
     *
     * @param code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Sets the transaction date
     *
     * @param date
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Sets the discount amount
     *
     * @param discountAmount
     */
    public void setDiscountAmount(final BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Set Escrow End Date
     *
     * @param escrowEndDate
     */
    public void setEscrowEndDate(final Date escrowEndDate) {
        this.escrowEndDate = escrowEndDate;
    }

    /**
     * Sets the extra amount
     *
     * @param extraAmount
     */
    public void setExtraAmount(final BigDecimal extraAmount) {
        this.extraAmount = extraAmount;
    }

    /**
     * Sets the fee amount
     *
     * @param feeAmount
     */
    public void setFeeAmount(final BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     * Sets the transaction gross amount
     *
     * @param grossAmount
     */
    public void setGrossAmount(final BigDecimal totalValue) {
        grossAmount = totalValue;
    }

    /**
     * Sets the installment count in this transaction
     *
     * @param installmentCount
     */
    public void setInstallmentCount(final Integer installmentCount) {
        this.installmentCount = installmentCount;
    }

    /**
     * Sets the items count
     *
     * @param itemCount
     */
    public void setItemCount(final Integer itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * Sets the list of items/products in this transaction
     *
     * @see Item
     *
     * @param items
     */
    public void setItems(final List<Item> items) {
        this.items = items;
    }

    /**
     * Sets the last event date
     *
     * @param lastEventDate
     */
    public void setLastEventDate(final Date lastEventDate) {
        this.lastEventDate = lastEventDate;
    }

    /**
     * Sets the net amount
     *
     * @param netAmount
     */
    public void setNetAmount(final BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    /**
     * Set Payment Link
     *
     * @param paymentLink
     */
    public void setPaymentLink(final String paymentLink) {
        this.paymentLink = paymentLink;
    }

    /**
     * Sets the payment method for this transaction
     *
     * @param paymentMethod
     */
    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Sets the reference code
     *
     * @param reference
     */
    public void setReference(final String reference) {
        this.reference = reference;
    }

    /**
     * Sets the sender information, who is sending money in this transaction
     *
     * @see Sender
     *
     * @param sender
     */
    public void setSender(final Sender sender) {
        this.sender = sender;
    }

    /**
     * sets the shipping information for this transaction
     *
     * @see Shipping
     *
     * @param shipping
     */
    public void setShipping(final Shipping shipping) {
        this.shipping = shipping;
    }

    /**
     * Sets the transaction status
     *
     * @see TransactionStatus
     *
     * @param status
     */
    public void setStatus(final TransactionStatus status) {
        this.status = status;
    }

    /**
     * Sets the transaction type
     *
     * @see TransactionType
     *
     * @param type
     */
    public void setType(final TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {

        final String codeValue = "Transaction(Code=" + code;
        final String dateValue = ",Date=" + date;
        final String referenceValue = ",Reference=" + reference;
        final String statusValue = ",Status=" + status.getValue()
                                                      .toString();
        final String emailValue = ",Email=" + sender != null ? sender.getEmail() : null;
        final String itemsValue = ",ItemsCount=" + itemCount;

        final StringBuilder sb = new StringBuilder();
        sb.append(codeValue);
        sb.append(dateValue);
        sb.append(referenceValue);
        sb.append(statusValue);
        sb.append(emailValue);
        sb.append(itemsValue);
        sb.append(")");

        return sb.toString();

    }
}
