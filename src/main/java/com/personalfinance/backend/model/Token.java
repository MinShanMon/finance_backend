package com.personalfinance.backend.model;

import lombok.Data;

@Data
public class Token {
    String access_token;
    String refresh_token;
}
