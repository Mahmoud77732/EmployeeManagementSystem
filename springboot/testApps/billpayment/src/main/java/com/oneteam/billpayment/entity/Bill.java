/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.billpayment.entity;

import jakarta.persistence.*;

/**
 *
 * @author mm887
 */

@Entity
@Table(name="bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="indexid")
    private Long index_id;

    @Column(name="amount", nullable = false)
    private int amount;
    
    @Column(name="description")
    private String description;
    
    @Column(name="message", length = 10000)
    private String message;
    
    @Column(name="msg_id", length = 10000)
    private String msg_id;

    public Bill() {
    }

    
    public Bill(int amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public Long getIndex_id() {
        return index_id;
    }

    public void setIndex_id(Long index_id) {
        this.index_id = index_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Bill{" + "index_id=" + index_id + ", amount=" + amount + ", description=" + description + ", message=" + message + ", msg_id=" + msg_id + '}';
    }

   

    
    
}
