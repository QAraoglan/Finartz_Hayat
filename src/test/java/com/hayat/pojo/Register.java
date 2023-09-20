package com.hayat.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Register {

    private String transactionId;
    private String verificationId;
    private String otp;
}
