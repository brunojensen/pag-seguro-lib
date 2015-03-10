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
package br.com.uol.pagseguro.exception;

import java.util.ArrayList;
import java.util.List;
import br.com.uol.pagseguro.domain.Error;
import br.com.uol.pagseguro.enums.HttpStatus;

/**
 * Encapsulates a problem that occurred calling a PagSeguro web service
 */
public class PagSeguroServiceException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3042057812162033491L;

    /**
     * @var HttpStatus see HttpStatus
     */
    private HttpStatus httpStatus;

    /**
     * @var list
     */
    private List<Error> errors;

    /**
     * Construct
     *
     * @param httpStatus
     */
    public PagSeguroServiceException(final HttpStatus httpStatus) {
        super(String.format("HTTP %1$s - %2$s [%3$s]", httpStatus.getCode(), httpStatus, httpStatus.getDescription()));
        this.httpStatus = httpStatus;
    }

    /**
     * Construct
     *
     * @param httpStatus
     */
    public PagSeguroServiceException(final HttpStatus httpStatus, final Exception exception) {
        super(String.format("HTTP %1$s - %2$s [%3$s] \n %4$s", httpStatus.getCode(), httpStatus,
                            httpStatus.getDescription(), exception.getMessage()));
        this.httpStatus = httpStatus;
    }

    /**
     * Construct
     *
     * @param httpStatus
     * @param erros
     */
    public PagSeguroServiceException(final HttpStatus httpStatus, final List<Error> erros) {
        super(String.format("HTTP %1$s - %2$s [%3$s]", httpStatus.getCode(), httpStatus, httpStatus.getDescription()));
        this.httpStatus = httpStatus;
        errors = erros;
    }

    /**
     * Construct
     *
     * @param httpStatus
     */
    public PagSeguroServiceException(final String message) {
        super(message);
    }

    /**
     * Construct
     *
     * @param httpStatus
     */
    public PagSeguroServiceException(final String message, final Exception exception) {
        super(message, exception);
    }

    /**
     * @return the errors
     */
    public List<Error> getErrors() {
        if (errors == null) {
            errors = new ArrayList<Error>();
        }
        return errors;
    }

    /**
     * @return the httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {

        final StringBuilder message = new StringBuilder(super.getMessage());

        if (!getErrors().isEmpty()) {

            for (Error error : errors) {

                message.append("\n")
                       .append(error.getCode())
                       .append(" - ")
                       .append(error.getMessage());

            }
        }

        return message.toString();
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(final List<Error> errors) {
        this.errors = errors;
    }

    /**
     * @param httpStatus the httpStatus to set
     */
    public void setHttpStatus(final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
