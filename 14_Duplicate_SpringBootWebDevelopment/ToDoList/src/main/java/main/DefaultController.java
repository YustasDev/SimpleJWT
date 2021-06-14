package main;

import java.util.ArrayList;
import java.util.List;
import main.model.CaseRepository;
import main.model.CriminalCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DefaultController {

  @Autowired
  private CaseRepository caseRepository;

  @Value("${someParameter}")
  private Integer someParameter;

  @RequestMapping(value = "/", method=RequestMethod.GET)
  public String index(Model model) {
    model.addAttribute("listOfCase", getListOfCase());
    model.addAttribute("caseCount", getListOfCase().size());
    return "index";
  }

  @RequestMapping(value = "/", method=RequestMethod.POST)
  public String sendingform(Model model, @RequestParam(name = "description") String description,
                      @RequestParam(name = "number") String number) {
    CriminalCase appendCriminalCase = new CriminalCase();
    if (description != null && number != null) {
      appendCriminalCase.setNumber(number);
      appendCriminalCase.setDescription(description);
    }
    CriminalCase newAddedCase = caseRepository.save(appendCriminalCase);
    model.addAttribute("listOfCase", getListOfCase());
    model.addAttribute("caseCount", getListOfCase().size());
    return "index";
  }

  public List<CriminalCase> getListOfCase() {
    Iterable<CriminalCase> criminalCaseIterable = caseRepository.findAll();
    List<CriminalCase> listOfCase = Streamable.of(criminalCaseIterable).toList();
    return listOfCase;
  }
}

