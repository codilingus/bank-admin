package com.example.bank.account;

public class Transfer {

    private int transferFrom;
    private int transferTo;
    private String transferAmount;

    public Transfer(int transferFrom, int transferTo, String transferAmount) {
        this.transferFrom = transferFrom;
        this.transferTo = transferTo;
        this.transferAmount = transferAmount;
    }

    public Transfer() {
    }

    public int getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(int transferFrom) {
        this.transferFrom = transferFrom;
    }

    public int getTransferTo() {
        return transferTo;
    }

    public void setTransferTo(int transferTo) {
        this.transferTo = transferTo;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }
}
