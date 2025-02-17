package com.example.project_w16.viaggio;

import lombok.Data;

@Data
class ViaggioRequest {
    private String destinazione;
    private String data;
    private String stato;
}