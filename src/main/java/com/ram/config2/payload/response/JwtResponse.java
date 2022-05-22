package com.ram.config2.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String fistName;
    private String lastName;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String fistName,String lastName, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.fistName = fistName ;
        this.lastName = lastName ;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public List<String> getRoles() {
        return roles;
    }
}