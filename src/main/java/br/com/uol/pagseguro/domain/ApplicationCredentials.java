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

import java.util.HashMap;
import java.util.Map;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;

/**
 * Represents an PagSeguro application identification
 */
public class ApplicationCredentials extends Credentials {

    private static final int HASH_SIZE = 3;

    /**
     * PagSeguro production application ID
     */
    private String productionAppId;

    /**
     * PagSeguro production application key
     */
    private String productionAppKey;

    /**
     * PagSeguro sandbox application ID
     */
    private String sandboxAppId;

    /**
     * PagSeguro sandbox application key
     */
    private String sandboxAppKey;

    /**
     * PagSeguro authorization code
     */
    private String authorizationCode;

    /**
     * Initializes a newly created instance of this type with the specified
     * arguments
     *
     * @param productionAppId the production pagseguro application ID. Max
     *            length 60 characters.
     * @param productionAppKey the production pagseguro application key. A
     *            sequence of 32 characters
     */
    public ApplicationCredentials(final String productionAppId, final String productionAppKey) throws PagSeguroServiceException {

        this(productionAppId, productionAppKey, null, null);
    }

    /**
     * Initializes a newly created instance of this type with the specified
     * arguments
     *
     * @param productionAppId the production pagseguro application ID. Max
     *            length 60 characters.
     * @param productionAppKey the production pagseguro application Key. A
     *            sequence of 32 characters
     * @param sandboxAppId the sandbox pagseguro application ID. Max length 60
     *            characters.
     * @param sandboxAppKey the sandbox pagseguro application Key. A sequence of
     *            32 characters
     */
    public ApplicationCredentials(final String productionAppId,
                                  final String productionAppKey,
                                  final String sandboxAppId,
                                  final String sandboxAppKey) throws PagSeguroServiceException {

        this.productionAppId = productionAppId.trim();
        this.productionAppKey = productionAppKey.trim();
        this.sandboxAppId = sandboxAppId.trim();
        this.sandboxAppKey = sandboxAppKey.trim();

        if (productionAppId == null || "".equals(productionAppId.trim()) && productionAppKey == null
            || "".equals(productionAppKey.trim()) || sandboxAppId == null || "".equals(sandboxAppId.trim())
            || sandboxAppKey == null || "".equals(sandboxAppKey.trim())) {
            throw new PagSeguroServiceException("Application Credentials not set correctly.");
        }

    }

    /**
     * @return the application ID
     */
    public String getAppId() {
        if (PagSeguroConfig.isSandboxEnvironment()) return sandboxAppId;
        return productionAppId;
    }

    /**
     * @return the application key
     */
    public String getAppKey() {
        if (PagSeguroConfig.isSandboxEnvironment()) return sandboxAppKey;
        return productionAppKey;
    }

    /**
     * @return array a map of name value pairs that compose this set of
     *         application credentials
     * @throws PagSeguroServiceException
     */
    @Override
    public Map<Object, Object> getAttributes() throws PagSeguroServiceException {

        final Map<Object, Object> attributeMap = new HashMap<Object, Object>(ApplicationCredentials.HASH_SIZE);

        if (PagSeguroConfig.isSandboxEnvironment()) {
            if (sandboxAppId == null || "".equals(sandboxAppId) || sandboxAppKey == null || "".equals(sandboxAppKey)) {
                throw new PagSeguroServiceException("Sandbox credentials not set.");
            }
            attributeMap.put("appId", sandboxAppId);
            attributeMap.put("appKey", sandboxAppKey);
        } else {
            attributeMap.put("appId", productionAppId);
            attributeMap.put("appKey", productionAppKey);
        }

        if (authorizationCode != null && !"".equals(authorizationCode)) {
            attributeMap.put("authorizationCode", authorizationCode);
        }

        return attributeMap;

    }

    /**
     * @return the authorization code
     */
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    /**
     * @param authorizationCode the authorization Code. Max length 30
     *            characters.
     */
    public void setAuthorizationCode(final String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    /**
     * @param productionAppId the production application ID account to set. Max
     *            length 60 characters.
     */
    public void setProductionAppId(final String productionAppId) {
        this.productionAppId = productionAppId;
    }

    /**
     * @param productionAppkey the production application key account to set.
     *            Max length 32 characters.
     */
    public void setProductionAppKey(final String productionAppKey) {
        this.productionAppKey = productionAppKey;
    }

    /**
     * @param sandboxAppId the sandbox application ID account to set. Max length
     *            60 characters.
     */
    public void setSandboxAppId(final String sandboxAppId) {
        this.sandboxAppId = sandboxAppId;
    }

    /**
     * @param sandboxAppkey the sandbox application key account to set. Max
     *            length 32 characters.
     */
    public void setSandboxAppKey(final String sandboxAppKey) {
        this.sandboxAppKey = sandboxAppKey;
    }

    /**
     * @return a string that represents the current object
     */
    @Override
    public String toString() {
        return productionAppId + " (production app Id) - " + productionAppKey + " (production app key) - "
            + sandboxAppId + " (sandbox app Id) - " + sandboxAppKey + " (sandbox app key) - " + authorizationCode
            + " (authorization Code)";
    }
}
