package main;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import model.CriminalCase;

@RestController
public class CaseController {

    @GetMapping("/cases/")
    public List<CriminalCase> list() {
        return Storage.getAllCases();
    }

    @PostMapping("/cases/")
    public int add(CriminalCase criminalCase) {
        return Storage.addCase(criminalCase);
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

