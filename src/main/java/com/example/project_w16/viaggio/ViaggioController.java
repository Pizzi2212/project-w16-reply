package com.example.project_w16.viaggio;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaggi")
@RequiredArgsConstructor
public class ViaggioController {

    @Autowired
    private final ViaggioService viaggioService;

    @GetMapping
    public Page<Viaggio> getAllViaggi(Pageable pageable) {
        return viaggioService.getAllViaggi(pageable);
    }

    @PostMapping
    public Viaggio createViaggio(@RequestBody ViaggioRequest viaggioRequest) {
        return viaggioService.saveViaggio(viaggioRequest);
    }

    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable Long id, @RequestBody Viaggio viaggio) {
        viaggio.setId(id);
        return viaggioService.saveViaggio(viaggio);
    }

    @DeleteMapping("/{id}")
    public void deleteViaggio(@PathVariable Long id) {
        viaggioService.deleteViaggio(id);
    }

    @PutMapping("/{id}/stato")
    public Viaggio updateStatoViaggio(@PathVariable Long id, @RequestParam String stato) {
        return viaggioService.updateStatoViaggio(id, stato);
    }
}
