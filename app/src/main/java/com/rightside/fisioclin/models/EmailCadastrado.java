package com.rightside.fisioclin.models;

import java.util.HashMap;

public class EmailCadastrado {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, Object> returnEmailCadastrado() {
        HashMap<String, Object> emailCadastrado = new HashMap<>();
        emailCadastrado.put("email", getEmail());
        return emailCadastrado;
    }
}
