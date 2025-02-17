package com.example.project_w16.prenotazione;

import lombok.Data;

@Data
class PrenotazioneRequest {
    private Long viaggioId;
    private Long dipendenteId;
    private String dataRichiesta;
    private String note;
}

