/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.billpayment.rest_controller;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.context.MessageContext;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 *
 * @author mahmoud
 */
@Endpoint
public class BillerInqueryEndpoint {
    private static final String NAMESPACE_URI = "http://www.etisalat.com.eg/services/PaymentGateway/Subscriber/ExecuteService/xsd/v1";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ExecuteServiceRequest")
    @ResponsePayload
    public Source  handleSoapRequest(@RequestPayload Source request, MessageContext messageContext) throws TransformerException {
        // Process headers
        

        // Transform request to string for processing (if needed)
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        StringWriter writer = new StringWriter();
        transformerFactory.newTransformer().transform(request, new StreamResult(writer));
        String requestXml = writer.toString();
        
        String responseXml = "";
        
        if(requestXml.contains("<Amount>")){ // Inquery
            responseXml = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<SOAP-ENV:Body>" +
                "<ns0:ExecuteServiceResponse xmlns:ns0=\"http://www.etisalat.com.eg/services/PaymentGateway/Subscriber/ExecuteService/xsd/v1\">" +
                "<ns1:header xmlns:ns1=\"http://etisalat.com.eg/EMF/MessageResponseHeaderV1.0.xsd\">" +
                "<ns1:RespondingSystem>" +
                "<ns1:correlationID>1297008</ns1:correlationID>" +
                "</ns1:RespondingSystem>" +
                "<ns1:Transaction>" +
                "<ns1:transactionID>205d1b79fb1843ff8939de055c36570d</ns1:transactionID>" +
                "</ns1:Transaction>" +
                "<ns1:serviceStatus>" +
                "<ns1:status>SUCCESS</ns1:status>" +
                "<ns1:statusDetail>" +
                "<ns1:errorCode>ESB-00000</ns1:errorCode>" +
                "<ns1:errorMSG>Success</ns1:errorMSG>" +
                "</ns1:statusDetail>" +
                "</ns1:serviceStatus>" +
                "</ns1:header>" +
                "<ns0:ResponseBody>" +
                "<ns0:Result>0</ns0:Result>" +
                "<ns0:Message>You request has been received. Your transaction ID is 1712080046190</ns0:Message>" +
                "<ns0:TransactionID>1712080046190</ns0:TransactionID>" +
                "<ns0:ClientID>000001505187</ns0:ClientID>" +
                "<ns0:Fees>0.0E0</ns0:Fees>" +
                "<ns0:Amount>1.0E1</ns0:Amount>" +
                "<ns0:customerWalletProfile>SelfRegisteration</ns0:customerWalletProfile>" +
                "<ns0:FeesDetails/>" +
                "</ns0:ResponseBody>" +
                "</ns0:ExecuteServiceResponse>" +
                "</SOAP-ENV:Body>" +
                "</SOAP-ENV:Envelope>";
        }
        else if(!requestXml.contains("<Amount>")){ // payment
        responseXml = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
                + "<SOAP-ENV:Body>\n"
                + "<ns0:ExecuteServiceResponse xmlns:ns0=\"http://www.etisalat.com.eg/services/PaymentGateway/Subscriber/ExecuteService/xsd/v1\">\n"
                + "<ns1:header xmlns:ns1=\"http://etisalat.com.eg/EMF/MessageResponseHeaderV1.0.xsd\">\n"
                + "<ns1:RespondingSystem>\n"
                + "<ns1:correlationID>1046450</ns1:correlationID>\n"
                + "</ns1:RespondingSystem>\n"
                + "<ns1:Transaction>\n"
                + "<ns1:transactionID>8745b898081e4cc59a788617b7230e78</ns1:transactionID>\n"
                + "</ns1:Transaction>\n"
                + "<ns1:serviceStatus>\n"
                + "<ns1:status>SUCCESS</ns1:status>\n"
                + "<ns1:statusDetail>\n"
                + "<ns1:errorCode>ESB-00000</ns1:errorCode>\n"
                + "<ns1:errorMSG>Query Successful</ns1:errorMSG>\n"
                + "</ns1:statusDetail>\n"
                + "</ns1:serviceStatus>\n"
                + "</ns1:header>\n"
                + "<ns0:ResponseBody>\n"
                + "<ns0:Result>0</ns0:Result>\n"
                + "<ns0:Message>Query Successful</ns0:Message>\n"
                + "<ns0:TransactionID>1712081908995</ns0:TransactionID>\n"
                + "<ns0:Fees>0.0E0</ns0:Fees>\n"
                + "<ns0:QueryTrxID>1046443</ns0:QueryTrxID>\n"
                + "<ns0:QueryTrxResult>0</ns0:QueryTrxResult>\n"
                + "<ns0:QueryTrxMessage>Cashout Request is waiting user confirmation</ns0:QueryTrxMessage>\n"
                + "<ns0:QueryTrxAmount>1.0E1</ns0:QueryTrxAmount>\n"
                + "<!--            <ns0:FeesDetails/>-->\n"
                + "\n"
                + "</ns0:ResponseBody>\n"
                + "</ns0:ExecuteServiceResponse>\n"
                + "</SOAP-ENV:Body>\n"
                + "</SOAP-ENV:Envelope>";
        }

        // Convert response XML to Source
        Source response = new javax.xml.transform.stream.StreamSource(new java.io.StringReader(responseXml));
        return response;
    }
}
