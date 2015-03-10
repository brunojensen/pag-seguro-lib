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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a page of transactions returned by the transaction search service
 */
public class TransactionSearchResult {

    /**
     * Date/time when this search was executed
     */
    private Date date;

    /**
     * Transactions in the current page
     */
    private int resultsInThisPage;

    /**
     * Total number of pages
     */
    private int totalPages;

    /**
     * Current page.
     */
    private int page;

    /**
     * Transactions in this page
     */
    private List<TransactionSummary> transactions;

    /**
     * @param transactionSummary
     */
    public void addTransactionSummary(final TransactionSummary transactions) {
        getTransactionSummaries().add(transactions);
    }

    /**
     * @return the date/time when this search was executed
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the current page number
     */
    public int getPage() {
        return page;
    }

    /**
     * @return the number of Transactions in the current page
     */
    public int getResultsInThisPage() {
        return resultsInThisPage;
    }

    /**
     * @return the total number of pages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * @return the Transactions in this page
     * @see TransactionSummary
     */
    public List<TransactionSummary> getTransactionSummaries() {

        if (transactions == null) {
            transactions = new ArrayList<TransactionSummary>();
        }

        return transactions;

    }

    /**
     * Set the date/time when this search was executed
     *
     * @param date
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Sets the current page number
     *
     * @param page
     */
    public void setPage(final int page) {
        this.page = page;
    }

    /**
     * Sets the number of Transactions in the current page
     *
     * @param resultsInThisPage
     */
    public void setResultsInThisPage(final int resultsInThisPage) {
        this.resultsInThisPage = resultsInThisPage;
    }

    /**
     * Sets the total number of pages
     *
     * @param totalPages
     */
    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * Sets the transactions in this page
     *
     * @param transactionSummaries
     */
    public void setTransactions(final List<TransactionSummary> transactions) {
        this.transactions = transactions;
    }

    /**
     * @return String a string that represents the current object
     */
    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        sb.append("TransactionSearchResult(Date=");
        sb.append(date);
        sb.append(",CurrentPage=");
        sb.append(page);
        sb.append(",TotalPages=");
        sb.append(totalPages);
        sb.append(",Transactions in this page=");
        sb.append(transactions != null ? transactions.size() : 0);

        return sb.toString();
    }

}
