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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import br.com.uol.pagseguro.domain.Error;
import br.com.uol.pagseguro.logs.Log;
import br.com.uol.pagseguro.logs.Logger;
import br.com.uol.pagseguro.xmlparser.XMLParserUtils;

public class ServiceParser {

    public static List<Error> readErrors(final InputStream xmlInputStream) throws ParserConfigurationException,
        SAXException, IOException {

        final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        final InputSource is = new InputSource(xmlInputStream);
        final Document doc = dBuilder.parse(is);

        final Element errorsElement = doc.getDocumentElement();
        final List<Element> itElements = XMLParserUtils.getElements("error", errorsElement);

        final List<Error> errors = new ArrayList<Error>();

        ServiceParser.log.debug("Parsing list of errors");

        for (int i = 0; i < itElements.size(); i++) {

            final Element itElement = itElements.get(i);

            final Error error = new Error();
            error.setCode(XMLParserUtils.getTagValue("code", itElement));
            error.setMessage(XMLParserUtils.getTagValue("message", itElement));

            errors.add(error);
        }

        return errors;

    }

    /**
     * PagSeguro Log tool
     *
     * @see Logger
     */
    private static Log log = new Log(ServiceParser.class);

    private ServiceParser() {
    }
}
