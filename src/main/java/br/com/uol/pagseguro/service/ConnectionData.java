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

import br.com.uol.pagseguro.domain.Credentials;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.helper.PagSeguroUtil;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.properties.PagSeguroSystem;

public class ConnectionData {

    private Credentials credentials;

    private String webServiceUrl;

    private String wsPaymentRequestUrl;

    private String wsPaymentRequestFindByCodeUrl;

    private final String wsPaymentRequestNotificationUrl;

    private String wsRecurrenceUrl;

    private final String wsRecurrenceFindByCodeUrl;

    private final String wsRecurrenceCancelByCodeUrl;

    private String wsAuthorizationUrl;

    private final String wsAuthorizationNotificationUrl;

    private final String wsAuthorizationFindByCodeUrl;

    private final String wsAuthorizationFindByDateUrl;

    private final String sessionsUrl;

    private final String installmentsUrl;

    private final String directPayment;

    private final String authorizationUrl;

    private final String paymentMethodsUrl;

    private String checkoutServicePath;

    private String serviceTimeout;

    private String charset;

    public ConnectionData(final Credentials credentials) {
        this.credentials = credentials;

        webServiceUrl = validUrlWebService();

        wsPaymentRequestUrl = validUrlWSPaymentRequest();
        wsAuthorizationNotificationUrl = validUrlWSAuthorizationNotification();
        wsAuthorizationFindByCodeUrl = validUrlWSAuthorizationFindByCode();
        wsAuthorizationFindByDateUrl = validUrlWSAuthorizationFindByDate();
        wsPaymentRequestFindByCodeUrl = validUrlWSPaymentRequestFindByCode();
        wsPaymentRequestNotificationUrl = validUrlWSPaymentRequestByNotificationCode();

        wsRecurrenceUrl = validUrlWSRecurrence();
        wsRecurrenceFindByCodeUrl = validUrlWSRecurrenceFindByCode();
        wsRecurrenceCancelByCodeUrl = validUrlWSRecurrenceCancelByCode();

        wsAuthorizationUrl = validUrlWSAuthorization();

        charset = PagSeguroConfig.getApplicationCharset();
        serviceTimeout = PagSeguroSystem.getServiceTimeout();

        sessionsUrl = PagSeguroSystem.getUrlSessions();
        installmentsUrl = PagSeguroSystem.getUrlInstallments();
        directPayment = PagSeguroSystem.getUrlDirectPayment();
        authorizationUrl = PagSeguroSystem.getUrlAuthorization();
        paymentMethodsUrl = PagSeguroSystem.getUrlPaymentMethods();

        checkoutServicePath = PagSeguroSystem.getCheckoutServicePath();
    }

    /**
     * Get Authorization Payment Url
     *
     * @return string
     */
    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }

    public String getCheckoutUrl() {
        return PagSeguroSystem.getCheckoutUrl();
    }

    /**
     * @return the credentials
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Create url
     *
     * @return string
     * @throws PagSeguroServiceException
     */
    public String getCredentialsUrlQuery() throws PagSeguroServiceException {
        return PagSeguroUtil.urlQuery(getCredentials().getAttributes());
    }

    /**
     * Get Direct Payment Url
     *
     * @return string
     */
    public String getDirectPaymentUrl() {
        return directPayment;
    }

    /**
     * Get Installments Url
     *
     * @return string
     */
    public String getInstallmentsUrl() {
        return installmentsUrl;
    }

    /**
     * @return the paymentMethodsUrl
     */
    public String getPaymentMethodsUrl() {
        return paymentMethodsUrl;
    }

    /**
     * @return the servicePath
     */
    public String getServicePath() {
        return checkoutServicePath;
    }

    /**
     * @return the serviceTimeout
     */
    public String getServiceTimeout() {
        return serviceTimeout;
    }

    /**
     * Get Service Url
     *
     * @return string
     */
    public String getServiceUrl() {
        return getWebServiceUrl() + getServicePath();
    }

    /**
     * Get Sessions Url
     *
     * @return string
     */
    public String getSessionsUrl() {
        return sessionsUrl;
    }

    public String getTransactionSearchUrl() {
        return PagSeguroSystem.getTransactionSearchUrl();
    }

    /**
     * @return the webServiceUrl
     */
    public String getWebServiceUrl() {
        return webServiceUrl;
    }

    /**
     * @return the wsAuthorizationFindByCode Url
     */
    public String getWSAuthorizationFindByCodeUrl() {
        return wsAuthorizationFindByCodeUrl;
    }

    /**
     * @return the wsAuthorizationFindAll Url
     */
    public String getWSAuthorizationFindByDateUrl() {
        return wsAuthorizationFindByDateUrl;
    }

    /**
     * @return the wsAuthorizationNotification Url
     */
    public String getWSAuthorizationNotificationUrl() {
        return wsAuthorizationNotificationUrl;
    }

    /**
     * @return the wsAuthorization Url
     */
    public String getWSAuthorizationUrl() {
        return wsAuthorizationUrl;
    }

    /**
     * @return the wsPaymentRequestFindByCodeUrl
     */
    public String getWSPaymentRequestFindByCodeUrl() {
        return wsPaymentRequestFindByCodeUrl;
    }

    /**
     * @return the wsPaymentRequestNotificationUrl
     */
    public String getWsPaymentRequestNotificationUrl() {
        return wsPaymentRequestNotificationUrl;
    }

    /**
     * @return the wsPaymentRequestUrl
     */
    public String getWSPaymentRequestUrl() {
        return wsPaymentRequestUrl;
    }

    /**
     * @return the wsRecurrenceCancelByCodeUrl
     */
    public String getWSRecurrenceCancelByCodeUrl() {
        return wsRecurrenceCancelByCodeUrl;
    }

    /**
     * @return the wsRecurrenceFindByCodeUrl
     */
    public String getWSRecurrenceFindByCodeUrl() {
        return wsRecurrenceFindByCodeUrl;
    }

    /**
     * @return the wsRecurrenceUrl
     */
    public String getWSRecurrenceUrl() {
        return wsRecurrenceUrl;
    }

    /**
     * @param charset the charset to set
     */
    public void setCharset(final String charset) {
        this.charset = charset;
    }

    /**
     * @param credentials the credentials to set
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * @param servicePath the servicePath to set
     */
    public void setServicePath(final String servicePath) {
        checkoutServicePath = servicePath;
    }

    /**
     * @param serviceTimeout the serviceTimeout to set
     */
    public void setServiceTimeout(final String serviceTimeout) {
        this.serviceTimeout = serviceTimeout;
    }

    /**
     * @param webServiceUrl the webServiceUrl to set
     */
    public void setWebServiceUrl(final String webServiceUrl) {
        this.webServiceUrl = webServiceUrl;
    }

    /**
     * @param wsAuthorizationUrl the wsAuthorizationUrl to set
     */
    public void setWSAuthorizationUrl(final String wsAuthorizationUrl) {
        this.wsAuthorizationUrl = wsAuthorizationUrl;
    }

    /**
     * @param wsPaymentRequestFindByCodeUrl the wsPaymentRequestFindByCodeUrl to
     *            set
     */
    public void setWSPaymentRequestFindByCodeUrl(final String wsPaymentRequestFindByCodeUrl) {
        this.wsPaymentRequestFindByCodeUrl = wsPaymentRequestFindByCodeUrl;
    }

    /**
     * @param wsPaymentRequestUrl the wsPaymentRequestUrl to set
     */
    public void setWSPaymentRequestUrl(final String wsPaymentRequestUrl) {
        this.wsPaymentRequestUrl = wsPaymentRequestUrl;
    }

    /**
     * @param wsRecurrenceUrl the wsRecurrenceUrl to set
     */
    public void setWSRecurrenceUrl(final String wsRecurrenceUrl) {
        this.wsRecurrenceUrl = wsRecurrenceUrl;
    }

    /**
     * Valid url web service production or development
     *
     * @return string
     */
    private String validUrlWebService() {

        final String url = PagSeguroSystem.getUrlProduction();

        return url + PagSeguroSystem.getCheckoutServicePath();

    }

    /**
     * Valid url web service production or development for authorization request
     *
     * @return string
     */
    private String validUrlWSAuthorization() {
        return PagSeguroSystem.getUrlProduction() + PagSeguroSystem.getAuthorizationServicePath();
    }

    /**
     * Valid url web service production or development for authorization find by
     * coderequest
     *
     * @return string
     */
    private String validUrlWSAuthorizationFindByCode() {
        return PagSeguroSystem.getUrlProduction() + PagSeguroSystem.getAuthorizationFindByCodePath();
    }

    /**
     * Valid url web service production or development for authorization find
     * all request
     *
     * @return string
     */
    private String validUrlWSAuthorizationFindByDate() {
        return PagSeguroSystem.getUrlProduction() + PagSeguroSystem.getAuthorizationFindByDatePath();
    }

    /**
     * Valid url web service production or development for authorization
     * notification request
     *
     * @return string
     */
    private String validUrlWSAuthorizationNotification() {
        return PagSeguroSystem.getUrlProduction() + PagSeguroSystem.getAuthorizationNotificationPath();
    }

    /**
     * Valid url web service production or development for payment request
     *
     * @return string
     */
    private String validUrlWSPaymentRequest() {
        return PagSeguroSystem.getUrlProduction() + PagSeguroSystem.getPaymentRequestServicePath();
    }

    /**
     * Valid url web service production or development for finding a payment
     * request by notification code
     *
     * @return string
     */
    private String validUrlWSPaymentRequestByNotificationCode() {
        return PagSeguroSystem.getUrlProduction() + PagSeguroSystem.getPaymentRequestNotificationUrl();
    }

    /**
     * Valid url web service production or development for finding a payment
     * request transaction
     *
     * @return string
     */
    private String validUrlWSPaymentRequestFindByCode() {
        return PagSeguroSystem.getUrlProduction() + PagSeguroSystem.getPaymentRequestFindByCodePath();
    }

    /**
     * Valid url web service production or development for recurrence
     *
     * @return string
     */
    private String validUrlWSRecurrence() {
        return PagSeguroSystem.getUrlProduction() + PagSeguroSystem.getRecurrenceServicePath();
    }

    /**
     * Valid url web service production or development for canceling a
     * recurrence transaction
     *
     * @return string
     */
    private String validUrlWSRecurrenceCancelByCode() {
        return PagSeguroSystem.getUrlProduction() + PagSeguroSystem.getRecurrenceCancelByCodePath();
    }

    /**
     * Valid url web service production or development for finding a recurrence
     * transaction
     *
     * @return string
     */
    private String validUrlWSRecurrenceFindByCode() {
        return PagSeguroSystem.getUrlProduction() + PagSeguroSystem.getRecurrenceFindByCodePath();
    }
}
