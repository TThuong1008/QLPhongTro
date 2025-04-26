package com.Test.Test.models;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_payment", nullable = false, unique = true)
    private String namePayment;  // Sửa tên trường thành namePayment (đúng quy tắc Java)

    // Getter và Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePayment() {
        return namePayment;
    }

    public void setNamePayment(String namePayment) {
        this.namePayment = namePayment;
    }
}