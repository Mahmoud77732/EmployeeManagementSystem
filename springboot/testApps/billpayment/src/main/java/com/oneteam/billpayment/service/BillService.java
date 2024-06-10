/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.billpayment.service;

import com.oneteam.billpayment.entity.Bill;
import com.oneteam.billpayment.repo.BillRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mm887
 */
@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    public Bill updateBill(Long id, Bill bill) {
        if (billRepository.existsById(id)) {
            bill.setIndex_id(id);
            return billRepository.save(bill);
        }
        return null; // Handle update failure
    }
}
