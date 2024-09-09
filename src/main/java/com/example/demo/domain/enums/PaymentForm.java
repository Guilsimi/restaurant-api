package com.example.demo.domain.enums;

public enum PaymentForm {

    CREDITO(1),
    DEBITO(2),
    DINHEIRO(3),
    PIX(4);

    private int code;

    private PaymentForm(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PaymentForm valueOf(int code) {
        for(PaymentForm value : PaymentForm.values()) {
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid payment form stats code, try again!");
    }
}
