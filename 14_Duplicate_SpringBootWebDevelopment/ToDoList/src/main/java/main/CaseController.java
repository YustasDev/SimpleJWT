package main;

import java.util.ArrayList;
import java.util.List;

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
        CriminalCase criminalCase = Storage.getCase(id);
        if (criminalCase == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(criminalCase, HttpStatus.OK);
    }

    @PutMapping("/cases/")
    public int replaceCase(CriminalCase newCase) {
        return Storage.replaceCase(newCase);
    }

    @DeleteMapping("/cases/{id}")
    public ResponseEntity deleteCase(@PathVariable int id) {
        CriminalCase criminalCase = Storage.deleteCase(id);
        if (criminalCase == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(criminalCase, HttpStatus.OK);
    }

    @DeleteMapping("/cases/")
    public ResponseEntity deleteAll()
    {
        List<CriminalCase> deleteAllCase = Storage.deleteAllCase();
        if (deleteAllCase == null) {
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(null);
        }
        return new ResponseEntity(deleteAllCase, HttpStatus.OK);
    }
  }

