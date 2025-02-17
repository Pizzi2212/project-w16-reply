package com.example.project_w16.dipendente;

import com.example.project_w16.email.EmailService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
class DipendenteService {
    @Autowired

    private static final Logger LOGGER = Logger.getLogger(DipendenteService.class.getName());

    private final DipendenteRepository dipendenteRepository;
    private final EmailService emailService;

    @Value("${messages.new.dipendente.subject:Benvenuto nel sistema}")
    private String subject;

    @Value("${messages.new.dipendente.body:Grazie per esserti registrato, la tua email è}")
    private String body;

    public Page<Dipendente> findAll(Pageable pageable) {
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autore non trovato con ID: " + id));
    }


    public Page<Dipendente> getAllDipendenti(Pageable pageable) {
        return dipendenteRepository.findAll(pageable);
    }

    @Transactional
    public Dipendente saveDipendente(Dipendente dipendente) {
        Dipendente dipendente1 = new Dipendente();
        dipendente1.setUsername(dipendente.getUsername());
        dipendente1.setNome(dipendente.getNome());
        dipendente1.setCognome(dipendente.getCognome());
        dipendente1.setEmail(dipendente.getEmail());
        dipendente1.setImmagineProfilo(dipendente.getImmagineProfilo());
        dipendente1 = dipendenteRepository.save(dipendente1);
        try {
            emailService.sendEmail(dipendente1.getEmail(), subject, body + " " + dipendente1.getEmail());
        } catch (MessagingException e) {
            LOGGER.warning("Errore nell'invio dell'email a " + dipendente1.getEmail() + ": " + e.getMessage());
        }
        return dipendente1;
    }

    public Dipendente modifyDipendente(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    public void deleteDipendente(Long id) {
        dipendenteRepository.deleteById(id);
    }

    public Dipendente updateImmagineProfilo(Long id, String filePath) {
        Dipendente dipendente = dipendenteRepository.findById(id).orElseThrow();
        dipendente.setImmagineProfilo(filePath);
        return dipendenteRepository.save(dipendente);
    }
}




