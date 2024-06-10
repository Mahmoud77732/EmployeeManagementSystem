/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.billpayment.rest_controller;

import com.oneteam.billpayment.entity.Bill;
import com.oneteam.billpayment.service.BillService;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mm887 
 */
@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Optional<Bill> optionalBill = billService.getBillById(id);
        // String[] descritpions = {"discription 1", "discription 2", "discription 3", "discription 4", "discription 5"};
        // Random random = new Random();
        // int randomIndex = random.nextInt(descritpions.length);
        // String randomDescritpion = descritpions[randomIndex];
        // optionalBill.get().setDescription(randomDescritpion);
        return optionalBill.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }
    
    

    @RequestMapping(value = "/xmlReq", 
                    method = RequestMethod.POST, 
                    consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}, 
                    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})
    public ResponseEntity<String> processXML(@RequestBody String xmlData) {
        // Log the received XML data for debugging
        System.out.println("Received XML data: " + xmlData);

        // Process the XML data here
        String responseXml = "<response>Hello from Spring Boot! You sent: " + xmlData + "</response>";

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
        
        // Return the response XML
        return ResponseEntity.ok(responseXml);
    }
    
//    @PostMapping
//    public ResponseEntity<Bill> saveBill(@RequestBody Bill bill) {
//        Bill savedBill = billService.saveBill(bill);
//        return new ResponseEntity<>(savedBill, HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        Bill updatedBill = billService.updateBill(id, bill);
        if (updatedBill != null) {
            return ResponseEntity.ok(updatedBill);
        }
        return ResponseEntity.notFound().build();
    }
}
