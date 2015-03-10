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
package br.com.uol.pagseguro.properties;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Helper to get values from properties
 */
public class PagSeguroSystem {

    public static String getAcceptHeaderXML() {
        return PagSeguroSystem.resourceBundle.getString("acceptHeader.xml");
    }

    public static String getAuthorizationFindByCodePath() {
        return PagSeguroSystem.resourceBundle.getString("authorizationService.findByCodePath");
    }

    public static String getAuthorizationFindByDatePath() {
        return PagSeguroSystem.resourceBundle.getString("authorizationService.findByDatePath");
    }

    public static String getAuthorizationNotificationPath() {
        return PagSeguroSystem.resourceBundle.getString("authorizationService.notificationPath");
    }

    /**
     * Get authorization service path
     *
     * @return string
     */
    public static String getAuthorizationServicePath() {
        return PagSeguroSystem.resourceBundle.getString("authorizationService.servicePath");
    }

    /**
     * Get checkout service path
     *
     * @return string
     */
    public static String getCheckoutServicePath() {
        return PagSeguroSystem.resourceBundle.getString("checkoutService.servicePath");
    }

    public static String getCheckoutUrl() {
        return PagSeguroSystem.resourceBundle.getString("checkoutService." + PagSeguroConfig.getEnvironment()
            + ".checkoutUrl");
    }

    public static String getContentTypeFormUrlEncoded() {
        return PagSeguroSystem.resourceBundle.getString("contentType.formUrlEncoded");
    }

    public static String getLanguageEnginedescription() {
        return PagSeguroSystem.LANGUAGE_ENGINE_DESCRIPTION;
    }

    public static String getLibversion() {
        return PagSeguroSystem.LIB_VERSION;
    }

    public static String getNotificationUrl() {
        return PagSeguroSystem.resourceBundle.getString("notificationService." + PagSeguroConfig.getEnvironment()
            + ".url");
    }

    public static String getPagSeguroEncoding() {
        return PagSeguroSystem.resourceBundle.getString("encoding");
    }

    public static String getPaymentRequestFindByCodePath() {
        return PagSeguroSystem.resourceBundle.getString("paymentRequestService.findByCodePath");
    }

    public static String getPaymentRequestNotificationUrl() {
        return PagSeguroSystem.resourceBundle.getString("notificationService.paymentRequest");
    }

    public static String getPaymentRequestServicePath() {
        return PagSeguroSystem.resourceBundle.getString("paymentRequestService.servicePath");
    }

    public static String getRecurrenceCancelByCodePath() {
        return PagSeguroSystem.resourceBundle.getString("recurrenceService.cancelByCodePath");
    }

    public static String getRecurrenceFindByCodePath() {
        return PagSeguroSystem.resourceBundle.getString("recurrenceService.findByCodePath");
    }

    public static String getRecurrenceServicePath() {
        return PagSeguroSystem.resourceBundle.getString("recurrenceService.servicePath");
    }

    public static String getServiceTimeout() {
        return PagSeguroSystem.resourceBundle.getString("serviceTimeout");
    }

    public static String getTransactionSearchUrl() {
        return PagSeguroSystem.resourceBundle.getString("transactionSearchService." + PagSeguroConfig.getEnvironment()
            + ".url");
    }

    /**
     * Get Url to Authorization
     *
     * @return string
     */
    public static String getUrlAuthorization() {
        return PagSeguroSystem.resourceBundle.getString("authorizationService." + PagSeguroConfig.getEnvironment()
            + ".authorizationUrl");
    }

    /**
     * Get Url to Direct Payment
     *
     * @return string
     */
    public static String getUrlDirectPayment() {
        return PagSeguroSystem.resourceBundle.getString("paymentService." + PagSeguroConfig.getEnvironment()
            + ".directPayment");
    }

    /**
     * Get Url to Installments
     *
     * @return string
     */
    public static String getUrlInstallments() {
        return PagSeguroSystem.resourceBundle.getString("paymentService." + PagSeguroConfig.getEnvironment()
            + ".installments");
    }

    public static String getUrlNotification() {
        return PagSeguroSystem.resourceBundle.getString("url.notification");
    }

    /**
     * Get Url to Payment-Methods
     *
     * @return string
     */
    public static String getUrlPaymentMethods() {
        return PagSeguroSystem.resourceBundle.getString("paymentService." + PagSeguroConfig.getEnvironment()
            + ".paymentMethods");
    }

    public static String getUrlPaymentRedir() {
        return PagSeguroSystem.resourceBundle.getString("url.payment.redir");
    }

    /**
     * Get Url to create a payment request
     *
     * @return string
     */
    public static String getUrlPaymentRequest() {
        return PagSeguroSystem.resourceBundle.getString("paymentRequestService." + PagSeguroConfig.getEnvironment()
            + ".paymentRequestUrl");
    }

    /**
     * Get Url payment production
     *
     * @return string
     */
    public static String getUrlProduction() {
        return PagSeguroSystem.resourceBundle.getString("environment." + PagSeguroConfig.getEnvironment()
            + ".webserviceUrl");
    }

    /**
     * Get Url to create a session
     *
     * @return string
     */
    public static String getUrlSessions() {
        return PagSeguroSystem.resourceBundle.getString("paymentService." + PagSeguroConfig.getEnvironment()
            + ".sessions");
    }

    private static ResourceBundle resourceBundle;

    private static final String LIB_VERSION = "2.5.1";

    private static final String LANGUAGE_ENGINE_DESCRIPTION = System.getProperty("java.version") + ":"
        + System.getProperty("java.vendor");

    static {
        PagSeguroSystem.resourceBundle = ResourceBundle.getBundle("pagseguro-system", Locale.getDefault());
    }

    private PagSeguroSystem() {
    }

}
