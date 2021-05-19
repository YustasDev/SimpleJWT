package main;

import java.util.Date;
import java.util.List;

import main.model.CaseRepository;
import main.model.CriminalCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DefaultController {

  @Autowired
  CaseRepository caseRepository;

  @RequestMapping("/")
  public String index(Model model) {
    Iterable<CriminalCase> criminalCaseIterable = caseRepository.findAll();
    List<CriminalCase> listOfCase = Streamable.of(criminalCaseIterable).toList();
    model.addAttribute("listOfCase", listOfCase);
    model.addAttribute("СaseCount", listOfCase.size());
    return "index";
  }
}

