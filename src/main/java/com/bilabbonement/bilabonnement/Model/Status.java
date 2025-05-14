package com.bilabbonement.bilabonnement.Model;

public class Status {

    private String status;
    private Long antal;

    public Status() {
    }

    public Status(String status, Long antal) {
        this.status = status;
        this.antal = antal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAntal() {
        return antal;
    }

    public void setAntal(Long antal) {
        this.antal = antal;
    }
}