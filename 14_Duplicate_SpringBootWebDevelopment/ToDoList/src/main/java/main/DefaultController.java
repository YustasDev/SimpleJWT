package main;

import java.util.List;

import main.model.CaseRepository;
import main.model.CriminalCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

  @Autowired
  private CaseRepository caseRepository;

  @Value("${someParameter}")
  private Integer someParameter;

  @RequestMapping(value = "/", method=RequestMethod.GET)
  public String index(Model model) {
    Iterable<CriminalCase> criminalCaseIterable = caseRepository.findAll();
    List<CriminalCase> listOfCase = Streamable.of(criminalCaseIterable).toList();
    model.addAttribute("listOfCase", listOfCase);
    model.addAttribute("caseCount", listOfCase.size());
    model.addAttribute("someParameter", someParameter);
    return "index";
  }


    /*
    @RequestMapping(value = "/", method=RequestMethod.POST)
    public String add(Model model, @ModelAttribute(value="criminalCase") CriminalCase criminalCase) {
    CriminalCase newCriminalCase = new CriminalCase();
    newCriminalCase.setNumber(Integer.parseInt("number"));
    newCriminalCase.setDescription("description");
    model.addAttribute("newCriminalCase", newCriminalCase);
    CriminalCase addedCase = caseRepository.save(newCriminalCase);
        return "index";
    }*/
}

