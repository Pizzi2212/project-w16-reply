package com.example.project_w16.prenotazione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteIdAndDataRichiesta(Long dipendenteId, String dataRichiesta);
}
