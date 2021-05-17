package main;

import java.util.List;
import java.util.Optional;

import main.model.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.CriminalCase;

@RestController
public class CaseController {

    @Autowired
    private CaseRepository caseRepository;

    @GetMapping("/cases/")
    public List<CriminalCase> list() {
        Iterable<CriminalCase> criminalCaseIterable = caseRepository.findAll();
        return Streamable.of(criminalCaseIterable).toList();
    }

    @GetMapping("/cases/{id}")
    public ResponseEntity get(@PathVariable int id) {
        return caseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/cases/", params = "description")
    public List<CriminalCase> getFindByDescription(@RequestParam String description) {
        Iterable<CriminalCase> criminalCaseIterable = caseRepository.findByDescription(description);
        return Streamable.of(criminalCaseIterable).toList();
    }


    @PostMapping("/cases/")
    public int add(CriminalCase criminalCase) {
        CriminalCase addedCase = caseRepository.save(criminalCase);
        return addedCase.getId();
    }

    @PutMapping("/cases/")
    public int replaceCase(CriminalCase newCase) {
        CriminalCase updatedCase = caseRepository.save(newCase);
        return updatedCase.getId();
    }

    @DeleteMapping("/cases/{id}")
    public ResponseEntity deleteCase(@PathVariable int id) {
        Optional<CriminalCase> deleteCase = caseRepository.findById(id);
        if (!deleteCase.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        caseRepository.deleteById(id);
        return new ResponseEntity(deleteCase.get(), HttpStatus.OK);
    }

    @DeleteMapping("/cases/")
    public ResponseEntity deleteAll() {
        List<CriminalCase> deleteAllCase = Streamable.of(caseRepository.findAll()).toList();
        if (deleteAllCase.isEmpty()) {
            String answer = "The list of Criminal Case is missing";
            return new ResponseEntity<>(answer, HttpStatus.RESET_CONTENT);
        }
        caseRepository.deleteAll();
        return new ResponseEntity<>(deleteAllCase, HttpStatus.OK);
        }
    }


