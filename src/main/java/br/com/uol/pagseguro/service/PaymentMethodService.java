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

package br.com.uol.pagseguro.service;

import java.net.HttpURLConnection;
import java.util.List;
import br.com.uol.pagseguro.domain.Credentials;
import br.com.uol.pagseguro.domain.Error;
import br.com.uol.pagseguro.domain.paymentmethod.PaymentMethods;
import br.com.uol.pagseguro.enums.HttpStatus;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.logs.Log;
import br.com.uol.pagseguro.parser.PaymentMethodsParser;
import br.com.uol.pagseguro.utils.HttpConnection;
import br.com.uol.pagseguro.xmlparser.ErrorsParser;

/**
 *
 * Class Payment Method Service
 */
public class PaymentMethodService {

    private static String buildPaymentMethodsUrl(final ConnectionData connectionData) {
        return connectionData.getPaymentMethodsUrl();
    }

    public static PaymentMethods getPaymentMethods(final Credentials credentials, //
                                                   final String publicKey) //
        throws PagSeguroServiceException {
        PaymentMethodService.log.info("PaymentMethodService.getPaymentMethods() - begin");

        final ConnectionData connectionData = new ConnectionData(credentials);

        final StringBuilder url = new StringBuilder(PaymentMethodService.buildPaymentMethodsUrl(connectionData));
        url.append("?publicKey=" + publicKey);

        final HttpConnection connection = new HttpConnection();
        HttpStatus httpCodeStatus = null;

        final HttpURLConnection response = connection.get(url.toString(), //
                                                          connectionData.getServiceTimeout(), //
                                                          connectionData.getCharset(), null);

        try {
            httpCodeStatus = HttpStatus.fromCode(response.getResponseCode());

            if (httpCodeStatus == null) {
                throw new PagSeguroServiceException("Connection Timeout");
            } else if (HttpURLConnection.HTTP_OK == httpCodeStatus.getCode()
                                                                  .intValue()) {
                final PaymentMethods paymentMethods = PaymentMethodsParser.readPaymentMethods(response.getInputStream());

                PaymentMethodService.log.info("PaymentMethodService.getPaymentMethods() - end");

                return paymentMethods;
            } else if (HttpURLConnection.HTTP_BAD_REQUEST == httpCodeStatus.getCode()
                                                                           .intValue()) {
                final List<Error> errors = ErrorsParser.readErrosXml(response.getErrorStream());

                final PagSeguroServiceException exception = new PagSeguroServiceException(httpCodeStatus, errors);

                PaymentMethodService.log.error(String.format("PaymentMethodService.getPaymentMethods() - error %s", //
                                                             exception.getMessage()));

                throw exception;
            } else if (HttpURLConnection.HTTP_UNAUTHORIZED == httpCodeStatus.getCode()
                                                                            .intValue()) {
                final PagSeguroServiceException exception = new PagSeguroServiceException(httpCodeStatus);

                PaymentMethodService.log.error(String.format("PaymentMethodService.getPaymentMethods() - error %s", //
                                                             exception.getMessage()));

                throw exception;
            } else {
                throw new PagSeguroServiceException(httpCodeStatus);
            }
        } catch (final PagSeguroServiceException e) {
            throw e;
        } catch (final Exception e) {
            PaymentMethodService.log.error(String.format("PaymentMethodService.getPaymentMethods() - error %s", //
                                                         e.getMessage()));

            throw new PagSeguroServiceException(httpCodeStatus, e);
        } finally {
            response.disconnect();
        }

    }

    /**
     * @var Log
     */
    private static Log log = new Log(PaymentMethodService.class);
}
