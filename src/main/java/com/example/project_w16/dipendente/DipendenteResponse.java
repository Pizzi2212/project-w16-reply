package com.example.project_w16.dipendente;

import com.example.project_w16.viaggio.Viaggio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteResponse {
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String immagineProfilo;
    private Set<Viaggio> viaggi;
}
