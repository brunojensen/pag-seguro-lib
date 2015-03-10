package br.com.uol.pagseguro.service;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import br.com.uol.pagseguro.domain.Credentials;
import br.com.uol.pagseguro.domain.Error;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.direct.PaymentRequest;
import br.com.uol.pagseguro.domain.direct.checkout.Checkout;
import br.com.uol.pagseguro.enums.HttpStatus;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.logs.Log;
import br.com.uol.pagseguro.parser.TransactionParser;
import br.com.uol.pagseguro.utils.HttpConnection;
import br.com.uol.pagseguro.xmlparser.ErrorsParser;

/**
 *
 * Class Transaction Service
 */
public class TransactionService {

    /**
     * @var Log
     */
    private static Log log = new Log(TransactionService.class);

    private static String buildDirectCheckoutUrl(final ConnectionData connectionData) //
        throws PagSeguroServiceException {
        return connectionData.getDirectPaymentUrl() + "?" + connectionData.getCredentialsUrlQuery();
    }

    /**
     * @param connectionData
     * @return
     * @throws PagSeguroServiceException
     *
     * @deprecated use {@link #buildDirectCheckoutUrl(ConnectionData)} instead.
     */
    @Deprecated
    private static String buildDirectPaymentRequestUrl(final ConnectionData connectionData) //
        throws PagSeguroServiceException {
        return connectionData.getDirectPaymentUrl() + "?" + connectionData.getCredentialsUrlQuery();
    }

    public static Transaction createTransaction(final Credentials credentials, //
                                                final Checkout checkout) //
        throws PagSeguroServiceException {
        TransactionService.log.info(String.format("TransactionService.createTransaction(%s) - begin", //
                                                  checkout.toString()));

        final ConnectionData connectionData = new ConnectionData(credentials);

        final Map<Object, Object> data = checkout.getMap();

        final String url = TransactionService.buildDirectCheckoutUrl(connectionData);

        final HttpConnection connection = new HttpConnection();
        HttpStatus httpCodeStatus = null;

        @SuppressWarnings("deprecation")
        final HttpURLConnection response = connection.post(url, //
                                                           data, //
                                                           connectionData.getServiceTimeout(), //
                                                           connectionData.getCharset());

        try {
            httpCodeStatus = HttpStatus.fromCode(response.getResponseCode());

            if (httpCodeStatus == null) {
                throw new PagSeguroServiceException("Connection Timeout");
            } else if (HttpURLConnection.HTTP_OK == httpCodeStatus.getCode()
                                                                  .intValue()) {
                final Transaction transaction = TransactionParser.readTransaction(response.getInputStream());

                TransactionService.log.info("TransactionService.createTransaction() - end");

                return transaction;
            } else if (HttpURLConnection.HTTP_BAD_REQUEST == httpCodeStatus.getCode()
                                                                           .intValue()) {
                final List<Error> errors = ErrorsParser.readErrosXml(response.getErrorStream());

                final PagSeguroServiceException exception = new PagSeguroServiceException(httpCodeStatus, errors);

                TransactionService.log.error(String.format("TransactionService.createTransaction() - error %s", //
                                                           exception.getMessage()));

                throw exception;
            } else if (HttpURLConnection.HTTP_UNAUTHORIZED == httpCodeStatus.getCode()
                                                                            .intValue()) {
                final PagSeguroServiceException exception = new PagSeguroServiceException(httpCodeStatus);

                TransactionService.log.error(String.format("TransactionService.createTransaction() - error %s", //
                                                           exception.getMessage()));

                throw exception;
            } else {
                throw new PagSeguroServiceException(httpCodeStatus);
            }
        } catch (final PagSeguroServiceException e) {
            throw e;
        } catch (final Exception e) {
            TransactionService.log.error(String.format("TransactionService.createTransaction() - error %s", //
                                                       e.getMessage()));

            throw new PagSeguroServiceException(httpCodeStatus, e);
        } finally {
            response.disconnect();
        }
    }

    /**
     * @param credentials
     * @param payment
     * @return
     * @throws PagSeguroServiceException
     *
     * @deprecated use {@link #createTransaction(Credentials, Checkout)}
     *             instead.
     */
    @Deprecated
    public static Transaction createTransaction(final Credentials credentials, //
                                                final PaymentRequest payment) //
        throws PagSeguroServiceException {
        TransactionService.log.info(String.format("TransactionService.createTransaction(%s) - begin", //
                                                  payment.toString()));

        final ConnectionData connectionData = new ConnectionData(credentials);

        final Map<Object, Object> data = payment.getMap();

        final String url = TransactionService.buildDirectPaymentRequestUrl(connectionData);

        final HttpConnection connection = new HttpConnection();
        HttpStatus httpCodeStatus = null;

        final HttpURLConnection response = connection.post(url, //
                                                           data, //
                                                           connectionData.getServiceTimeout(), //
                                                           connectionData.getCharset());

        try {
            httpCodeStatus = HttpStatus.fromCode(response.getResponseCode());

            if (httpCodeStatus == null) {
                throw new PagSeguroServiceException("Connection Timeout");
            } else if (HttpURLConnection.HTTP_OK == httpCodeStatus.getCode()
                                                                  .intValue()) {
                final Transaction transaction = TransactionParser.readTransaction(response.getInputStream());

                TransactionService.log.info("TransactionService.createTransaction() - end");

                return transaction;
            } else if (HttpURLConnection.HTTP_BAD_REQUEST == httpCodeStatus.getCode()
                                                                           .intValue()) {
                final List<Error> errors = ErrorsParser.readErrosXml(response.getErrorStream());

                final PagSeguroServiceException exception = new PagSeguroServiceException(httpCodeStatus, errors);

                TransactionService.log.error(String.format("TransactionService.createTransaction() - error %s", //
                                                           exception.getMessage()));

                throw exception;
            } else if (HttpURLConnection.HTTP_UNAUTHORIZED == httpCodeStatus.getCode()
                                                                            .intValue()) {
                final PagSeguroServiceException exception = new PagSeguroServiceException(httpCodeStatus);

                TransactionService.log.error(String.format("TransactionService.createTransaction() - error %s", //
                                                           exception.getMessage()));

                throw exception;
            } else {
                throw new PagSeguroServiceException(httpCodeStatus);
            }
        } catch (final PagSeguroServiceException e) {
            throw e;
        } catch (final Exception e) {
            TransactionService.log.error(String.format("TransactionService.createTransaction() - error %s", //
                                                       e.getMessage()));

            throw new PagSeguroServiceException(httpCodeStatus, e);
        } finally {
            response.disconnect();
        }
    }

}
