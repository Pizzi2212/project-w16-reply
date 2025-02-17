package com.example.project_w16.prenotazione;

import com.example.project_w16.dipendente.Dipendente;
import com.example.project_w16.dipendente.DipendenteRepository;
import com.example.project_w16.viaggio.Viaggio;
import com.example.project_w16.viaggio.ViaggioRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PrenotazioneService {
    @Autowired
    private final PrenotazioneRepository prenotazioneRepository;
    private final ViaggioRepository viaggioRepository;
    private final DipendenteRepository dipendenteRepository;

    public Page<Prenotazione> getAllPrenotazioni(Pageable pageable) {
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione savePrenotazione(PrenotazioneRequest prenotazioneRequest) {
        Viaggio viaggio = viaggioRepository.findById(prenotazioneRequest.getViaggioId()).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        Dipendente dipendente = dipendenteRepository.findById(prenotazioneRequest.getDipendenteId()).orElseThrow(() -> new RuntimeException("Dipendente non trovato"));

        if (prenotazioneRepository.existsByDipendenteIdAndDataRichiesta(dipendente.getId(), prenotazioneRequest.getDataRichiesta())) {
            throw new RuntimeException("Il dipendente ha già una prenotazione per questa data");
        }

        Prenotazione prenotazione = new Prenotazione(null, viaggio, dipendente, prenotazioneRequest.getDataRichiesta(), prenotazioneRequest.getNote());
        return prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazione(Long id) {
        prenotazioneRepository.deleteById(id);
    }

}