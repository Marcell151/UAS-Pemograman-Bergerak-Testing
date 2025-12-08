package com.example.kantinkampus;

public class Order {
    private int id;
    private int userId;
    private int standId;
    private int total;
    private String status; // 'pending', 'processing', 'ready', 'completed', 'cancelled'
    private String paymentMethod;
    private String paymentStatus; // 'unpaid', 'paid'
    private String notes;
    private String createdAt;
    private String updatedAt;

    // Additional fields from JOIN queries
    private String standName;
    private String userName;

    public Order() {}

    public Order(int id, int userId, int standId, int total, String status,
                 String paymentMethod, String paymentStatus, String notes,
                 String createdAt, String updatedAt) {
        this.id = id;
        this.userId = userId;
        this.standId = standId;
        this.total = total;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStandId() {
        return standId;
    }

    public void setStandId(int standId) {
        this.standId = standId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Helper methods
    public boolean isPending() {
        return "pending".equals(status);
    }

    public boolean isProcessing() {
        return "processing".equals(status);
    }

    public boolean isReady() {
        return "ready".equals(status);
    }

    public boolean isCompleted() {
        return "completed".equals(status);
    }

    public boolean isCancelled() {
        return "cancelled".equals(status);
    }

    public String getStatusText() {
        switch (status) {
            case "pending":
                return "Menunggu Konfirmasi";
            case "processing":
                return "Sedang Diproses";
            case "ready":
                return "Siap Diambil";
            case "completed":
                return "Selesai";
            case "cancelled":
                return "Dibatalkan";
            default:
                return "Unknown";
        }
    }

    public String getStatusEmoji() {
        switch (status) {
            case "pending":
                return "⏳";
            case "processing":
                return "👨‍🍳";
            case "ready":
                return "✅";
            case "completed":
                return "🎉";
            case "cancelled":
                return "❌";
            default:
                return "❓";
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", standId=" + standId +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}