package com.example.project_w16.viaggio;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ViaggioService {
    @Autowired
    private final ViaggioRepository viaggioRepository;

    public Page<Viaggio> getAllViaggi(Pageable pageable) {
        return viaggioRepository.findAll(pageable);
    }

    public Viaggio saveViaggio(ViaggioRequest viaggioRequest) {
        Viaggio viaggio = new Viaggio(null, viaggioRequest.getDestinazione(), viaggioRequest.getData(), viaggioRequest.getStato());
        return viaggioRepository.save(viaggio);
    }

    public Viaggio saveViaggio(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }



    public void deleteViaggio(Long id) {
        viaggioRepository.deleteById(id);
    }

    public Viaggio updateStatoViaggio(Long id, String stato) {
        Viaggio viaggio = viaggioRepository.findById(id).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
        viaggio.setStato(stato);
        return viaggioRepository.save(viaggio);
    }
}