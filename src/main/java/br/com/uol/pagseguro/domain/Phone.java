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

import br.com.uol.pagseguro.helper.PagSeguroUtil;

/**
 * Represents a phone number
 */
public class Phone {

    /**
     * Area code
     */
    private String areaCode;

    /**
     * Phone number
     */
    private String number;

    /**
     * Initializes a new instance of the Phone class
     */
    public Phone() {
    }

    /**
     * Initializes a new instance of the Phone class
     *
     * @param areaCode
     * @param number
     */
    public Phone(final String areaCode, final String number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    /**
     * @return the area code
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the area code
     *
     * @param areaCode
     */
    public void setAreaCode(final String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * Sets the number from a formatted string
     *
     * @param number String formatted string like <code>(99) 9-9999-9999</code>
     */
    public void setFullPhone(String number) {

        number = PagSeguroUtil.removeCharacterPhone(number);
        final Integer length = number.length();

        setAreaCode(number.substring(0, 2));
        setNumber(number.substring(2, length));
    }

    /**
     * Sets the number
     *
     * @param number
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * return toString
     */
    @Override
    public String toString() {
        return areaCode + number;
    }
}
