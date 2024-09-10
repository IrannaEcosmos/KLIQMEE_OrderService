package com.example.Test.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class EncodingUtil {

    public String generateQRCodeId(String orderId) {
        if (orderId == null || orderId.isEmpty()) {
            return null;
        }
        
        // Using StringBuffer to build the QR code ID
        StringBuffer stringBuffer = new StringBuffer();
        
        // Example of adding a prefix and suffix
        stringBuffer.append("QR-");
        stringBuffer.append(orderId);
        stringBuffer.append("-ID");
        
        // Convert StringBuffer to String
        String qrCodeId = stringBuffer.toString();
        
        // Encode the QR code ID to Base64
        return encodeToBase64(qrCodeId);
    }
    
    private String encodeToBase64(String data) {
        if (data == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(data.getBytes());
    }
}
