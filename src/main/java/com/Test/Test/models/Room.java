package com.Test.Test.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "room")
public class Room {
    
    @Id
    @Column(name = "room_id")
    private String roomId;
    
    @NotBlank(message = "Tên người thuê không được để trống")
    @Size(min = 5, max = 50, message = "Tên người thuê phải từ 5 đến 50 ký tự")
    @Pattern(regexp = "^[^<>%$#@!&*()]+$", message = "Tên không chứa ký tự đặc biệt")
    @Column(name = "tenant_name", nullable = false, length = 50)
    private String tenantName;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^[0-9]{10}$", message = "Số điện thoại phải có 10 chữ số")
    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;
    
    @NotNull(message = "Ngày bắt đầu thuê không được để trống")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @ManyToOne
    @NotNull(message = "Hình thức thanh toán không được để trống")
    @JoinColumn(name = "payment_type", nullable = false)
    private PaymentType hinhThuc;
    
    @Column(name = "notes")
    private String notes;
    
    // Constructors
    public Room() {
    }
    
    public Room(String roomId, String tenantName, String phoneNumber, LocalDate startDate, PaymentType hinhThuc, String notes) {
        this.roomId = roomId;
        this.tenantName = tenantName;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.hinhThuc = hinhThuc;
        this.notes = notes;
    }
    
    // Getters and Setters
    public String getRoomId() {
        return roomId;
    }
    
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    
    public String getTenantName() {
        return tenantName;
    }
    
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public PaymentType getHinhThuc() {
        return hinhThuc;
    }
    
    public void setHinhThuc(PaymentType hinhThuc) {
        this.hinhThuc = hinhThuc;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
