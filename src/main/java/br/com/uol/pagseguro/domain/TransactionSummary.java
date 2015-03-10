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
import java.util.Date;
import br.com.uol.pagseguro.enums.TransactionStatus;
import br.com.uol.pagseguro.enums.TransactionType;

/**
 * Represents a summary of a PagSeguro transaction, typically returned by search
 * services.
 */
public class TransactionSummary {

    /** Transaction date */
    private Date date;

    /**
     * Last event date Date the last notification about this transaction was
     * sent
     */
    private Date lastEvent;

    /** Transaction Code. */
    private String code;

    /** Transaction Reference. */
    private String reference;

    /** Transaction Gross Amount. */
    private BigDecimal grossAmount;

    /** Transaction Type */
    private TransactionType type;

    /**
     * Transaction status
     *
     * @see TransactionStatus
     */
    private TransactionStatus status;

    /**
     * Net amount
     */
    private BigDecimal netAmount;

    /**
     * Discount amount
     */
    private BigDecimal discountAmount;

    /**
     * Fee amount
     */
    private BigDecimal feeAmount;

    /**
     * Extra amount
     */
    private BigDecimal extraAmount;

    /**
     * Payment Method.
     */
    private PaymentMethod paymentMethod;

    /**
     * Escrow End Date.
     */
    private Date escrowEndDate;

    /**
     * Cancellation Source
     */
    private String cancellationSource;

    /**
     * Payment Link
     */
    private String paymentLink;

    /**
     * Items count
     */
    private Integer itemCount;

    /**
     * Get Cancellation Source
     *
     * @return cancellationSource
     */
    public String getCancellationSource() {
        return cancellationSource;
    }

    /**
     * Get Code
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Get Date
     *
     * @return Date
     */
    public Date getDate() {

        if (date == null) {
            date = new Date();
        }

        return date;

    }

    /**
     * Get discount amount
     *
     * @return BigDecimal
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Get Escrow End Date
     *
     * @return escrowEndDate
     */
    public Date getEscrowEndDate() {
        if (escrowEndDate == null) {
            escrowEndDate = new Date();
        }
        return escrowEndDate;
    }

    /**
     * Get Extra Amount
     *
     * @return
     */
    public BigDecimal getExtraAmount() {
        return extraAmount;
    }

    /**
     * Get Fee Amount
     *
     * @return BigDecimal
     */
    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    /**
     * Get Gross Amount
     *
     * @return BigDecimal
     */
    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    /**
     * @return the items/products count in this transaction
     */
    public Integer getItemCount() {
        return itemCount;
    }

    /**
     * Get Last Event
     *
     * @return Date
     */
    public Date getLastEvent() {
        if (lastEvent == null) {
            lastEvent = new Date();
        }
        return lastEvent;
    }

    /**
     * Get Net Amount
     *
     * @return BigDecimal
     */
    public BigDecimal getNetAmount() {
        return netAmount;
    }

    /**
     * Get Payment Link
     *
     * @return the booklet payment url
     */
    public String getPaymentLink() {
        return paymentLink;
    }

    /**
     * Get Payment Method
     *
     * @return the transaction payment method
     */
    public PaymentMethod getPaymentMethod() {

        if (paymentMethod == null) {
            paymentMethod = new PaymentMethod();
        }

        return paymentMethod;

    }

    /**
     * Get Reference
     *
     * @return String
     */
    public String getReference() {
        return reference;
    }

    /**
     * Get Status
     *
     * @return TransactionStatus
     */
    public TransactionStatus getStatus() {
        return status;

    }

    /**
     * Get TransactionType
     *
     * @return TransactionType
     */
    public TransactionType getType() {
        return type;

    }

    /**
     * Set Cancellation Source
     *
     * @param the source of the transaction cancellation
     */
    public void setCancellationSource(final String cancellationSource) {
        this.cancellationSource = cancellationSource;
    }

    /**
     * Set Code
     *
     * @param code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Set Date
     *
     * @param date
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Set Discount amount
     *
     * @param discountAmount
     */
    public void setDiscountAmount(final BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Set Extra Amount
     *
     * @param extraAmount
     */
    public void setExtraAmount(final BigDecimal extraAmount) {
        this.extraAmount = extraAmount;
    }

    /**
     * Set Fee Amount
     *
     * @param feeAmount
     */
    public void setFeeAmount(final BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     * Set Gross Amount
     *
     * @param grossAmount
     */
    public void setGrossAmount(final BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
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
     * Set Last Event
     *
     * @param lastEvent
     */
    public void setLastEvent(final Date lastEvent) {
        this.lastEvent = lastEvent;
    }

    /**
     * Set net Amount
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
     * Set Payment Method
     *
     * @param paymentMethod
     */
    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Set Reference
     *
     * @param reference
     */
    public void setReference(final String reference) {
        this.reference = reference;
    }

    /**
     * Set Status
     *
     * @param status
     */
    public void setStatus(final TransactionStatus status) {
        this.status = status;
    }

    /**
     * Set TransactionType
     *
     * @param type
     */
    public void setType(final TransactionType type) {
        this.type = type;
    }

    /**
     * return string
     */
    @Override
    public String toString() {
        return "Status: " + status;
    }

}
