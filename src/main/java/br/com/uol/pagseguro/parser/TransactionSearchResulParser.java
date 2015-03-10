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
package br.com.uol.pagseguro.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.TransactionSearchResult;
import br.com.uol.pagseguro.domain.TransactionSummary;
import br.com.uol.pagseguro.helper.PagSeguroUtil;

/**
 * Utility class for handle transactions search in PagSeguro's web service
 */
public class TransactionSearchResulParser extends DefaultHandler {

    public static TransactionSearchResulParser getHandler(final InputStream xml,
                                                          final TransactionSearchResult transactionSearchResult)
        throws ParserConfigurationException, SAXException, IOException {

        final SAXParser parser = SAXParserFactory.newInstance()
                                                 .newSAXParser();
        final InputSource input = new InputSource(xml);
        final TransactionSearchResulParser handler = new TransactionSearchResulParser(transactionSearchResult);

        parser.parse(input, handler);

        return handler;

    }

    private List<String> handledElements = new ArrayList<String>();
    private static final String ROOT_ELEMENT = "transactionSearchResult";

    private static final String TRANSACTIONS_ELEMENT = "transactions";
    private String parentElement = TransactionSearchResulParser.ROOT_ELEMENT;

    private String currentElement = TransactionSearchResulParser.ROOT_ELEMENT;

    private StringBuilder xmlTransaction;
    private TransactionSearchResult transactionSearchResult;

    private final List<TransactionSummary> transactions = new ArrayList<TransactionSummary>();

    public TransactionSearchResulParser(final TransactionSearchResult transactionSearchResult) {

        handledElements = new ArrayList<String>();
        handledElements.add("date");
        handledElements.add("currentPage");
        handledElements.add("resultsInThisPage");
        handledElements.add("totalPages");

        this.transactionSearchResult = transactionSearchResult;

    }

    private TransactionSummary buildTransactionSummary(final Transaction transaction) {

        final TransactionSummary transactionSummary = new TransactionSummary();

        transactionSummary.setDate(transaction.getDate());
        transactionSummary.setLastEvent(transaction.getLastEventDate());
        transactionSummary.setCode(transaction.getCode());
        transactionSummary.setReference(transaction.getReference());
        transactionSummary.setType(transaction.getType());
        transactionSummary.setStatus(transaction.getStatus());
        transactionSummary.setPaymentMethod(transaction.getPaymentMethod());
        transactionSummary.setGrossAmount(transaction.getGrossAmount());
        transactionSummary.setDiscountAmount(transaction.getDiscountAmount());
        transactionSummary.setFeeAmount(transaction.getFeeAmount());
        transactionSummary.setNetAmount(transaction.getNetAmount());
        transactionSummary.setExtraAmount(transaction.getExtraAmount());

        return transactionSummary;

    }

    @Override
    public void characters(final char[] buffer, final int start, final int length) throws SAXException {

        final StringBuilder buf = new StringBuilder();

        if (parentElement.equals(TransactionSearchResulParser.ROOT_ELEMENT)) {

            if ("date".equals(currentElement)) {

                try {
                    transactionSearchResult.setDate(PagSeguroUtil.parse(buf.append(buffer, start, length)
                                                                           .toString()));
                } catch (final ParseException e) {
                    throw new SAXException(e);
                }

            } else if ("currentPage".equals(currentElement)) {
                transactionSearchResult.setPage(Integer.parseInt(buf.append(buffer, start, length)
                                                                    .toString()));
            } else if ("resultsInThisPage".equals(currentElement)) {
                transactionSearchResult.setResultsInThisPage(Integer.parseInt(buf.append(buffer, start, length)
                                                                                 .toString()));
            } else if ("totalPages".equals(currentElement)) {
                transactionSearchResult.setTotalPages(Integer.parseInt(buf.append(buffer, start, length)
                                                                          .toString()));
            }
        } else if ("transaction".equals(currentElement)) {
            xmlTransaction.append(buffer, start, length);
        }

    }

    @Override
    public void endDocument() throws SAXException {
        transactionSearchResult.setTransactions(transactions);
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {

        if (qName.equals(TransactionSearchResulParser.TRANSACTIONS_ELEMENT)) {
            parentElement = TransactionSearchResulParser.ROOT_ELEMENT;
        }

        if ("transaction".equals(currentElement)) {
            xmlTransaction.append("</" + qName + ">");
        }

        if ("transaction".equals(qName)) {

            try {

                final Transaction transaction = TransactionParser.readTransaction(new ByteArrayInputStream(xmlTransaction.toString()
                                                                                                                         .getBytes()));
                final TransactionSummary transactionSummary = buildTransactionSummary(transaction);
                transactions.add(transactionSummary);

            } catch (final ParserConfigurationException e) {
                throw new SAXException(e);
            } catch (final ParseException e) {
                throw new SAXException(e);
            } catch (final IOException e) {
                throw new SAXException(e);
            }

        }
    }

    public TransactionSearchResult getTransactionSearchResult() {
        return transactionSearchResult;
    }

    public void setTransactionSearchResult(final TransactionSearchResult transactionSearchResult) {
        this.transactionSearchResult = transactionSearchResult;
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes)
        throws SAXException {

        if (handledElements.contains(qName) && parentElement.equals(TransactionSearchResulParser.ROOT_ELEMENT)) {
            currentElement = qName;
        }

        if (qName.equals(TransactionSearchResulParser.TRANSACTIONS_ELEMENT)) {
            parentElement = TransactionSearchResulParser.TRANSACTIONS_ELEMENT;
        }

        if ("transaction".equals(qName)) {
            currentElement = "transaction";
            xmlTransaction = new StringBuilder();
        }

        if ("transaction".equals(currentElement)) {
            xmlTransaction.append("<" + qName + ">");
        }

    }
}
