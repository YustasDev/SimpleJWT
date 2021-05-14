package main;

import java.util.ArrayList;
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
        List<CriminalCase> criminalCaseList = new ArrayList<>();
        criminalCaseList = Streamable.of(criminalCaseIterable).toList();
        return criminalCaseList;
    }

    @PostMapping("/cases/")
    public int add(CriminalCase criminalCase) {
        CriminalCase addedCase = caseRepository.save(criminalCase);
        return addedCase.getId();
    }

    @GetMapping("/cases/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<CriminalCase> optionalCriminalCase = caseRepository.findById(id);
        if (!optionalCriminalCase.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalCriminalCase.get(), HttpStatus.OK);
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
    public ResponseEntity deleteAll()
    {
        Iterable<CriminalCase> deleteAllCase = caseRepository.findAll();
        if (deleteAllCase == null) {
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(null);
        }
        List<CriminalCase> deleteAllCaseList = new ArrayList<>();
        deleteAllCaseList = Streamable.of(deleteAllCase).toList();
        caseRepository.deleteAll();
        return new ResponseEntity(deleteAllCaseList, HttpStatus.OK);
    }
  }

