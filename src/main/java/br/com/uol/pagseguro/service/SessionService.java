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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import br.com.uol.pagseguro.domain.Credentials;
import br.com.uol.pagseguro.domain.Error;
import br.com.uol.pagseguro.enums.HttpStatus;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.logs.Log;
import br.com.uol.pagseguro.utils.HttpConnection;
import br.com.uol.pagseguro.xmlparser.ErrorsParser;
import br.com.uol.pagseguro.xmlparser.XMLParserUtils;

/**
 * Class Session Service
 */
public class SessionService {

    private static String buildSessionsRequestUrl(final ConnectionData connectionData) //
        throws PagSeguroServiceException {
        return connectionData.getSessionsUrl();
    }

    public static String createSession(final Credentials credentials) //
        throws PagSeguroServiceException {
        SessionService.log.info("SessionService.createSession() - begin");

        final ConnectionData connectionData = new ConnectionData(credentials);

        final String url = SessionService.buildSessionsRequestUrl(connectionData);

        final HttpConnection connection = new HttpConnection();
        HttpStatus httpCodeStatus = null;

        final HttpURLConnection response = connection.post(url, //
                                                           credentials.getAttributes(), //
                                                           connectionData.getServiceTimeout(), //
                                                           connectionData.getCharset(), null);

        try {
            httpCodeStatus = HttpStatus.fromCode(response.getResponseCode());

            if (httpCodeStatus == null) {
                throw new PagSeguroServiceException("Connection Timeout");
            } else if (HttpURLConnection.HTTP_OK == httpCodeStatus.getCode()
                                                                  .intValue()) {
                final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

                final InputSource inputSource = new InputSource(response.getInputStream());
                final Document document = documentBuilder.parse(inputSource);

                final Element element = document.getDocumentElement();

                // setting <session><id>
                final String sessionId = XMLParserUtils.getTagValue("id", element);

                SessionService.log.info("SessionService.createSession() - end");

                return sessionId;
            } else if (HttpURLConnection.HTTP_BAD_REQUEST == httpCodeStatus.getCode()
                                                                           .intValue()) {
                final List<Error> errors = ErrorsParser.readErrosXml(response.getErrorStream());

                final PagSeguroServiceException exception = new PagSeguroServiceException(httpCodeStatus, errors);

                SessionService.log.error(String.format("SessionService.createSession() - error %s", //
                                                       exception.getMessage()));

                throw exception;
            } else if (HttpURLConnection.HTTP_UNAUTHORIZED == httpCodeStatus.getCode()
                                                                            .intValue()) {
                final PagSeguroServiceException exception = new PagSeguroServiceException(httpCodeStatus);

                SessionService.log.error(String.format("SessionService.createSession() - error %s", //
                                                       exception.getMessage()));

                throw exception;
            } else {
                throw new PagSeguroServiceException(httpCodeStatus);
            }
        } catch (final PagSeguroServiceException e) {
            throw e;
        } catch (final Exception e) {
            SessionService.log.error(String.format("SessionService.createSession() - error %s", //
                                                   e.getMessage()));

            throw new PagSeguroServiceException(httpCodeStatus, e);
        } finally {
            response.disconnect();
        }
    }

    /**
     * @var Log
     */
    private static Log log = new Log(SessionService.class);

}
