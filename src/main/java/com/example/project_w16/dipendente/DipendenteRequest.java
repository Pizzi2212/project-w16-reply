package com.example.project_w16.dipendente;

import lombok.Data;

@Data
class DipendenteRequest {
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String immagineProfilo;
}
