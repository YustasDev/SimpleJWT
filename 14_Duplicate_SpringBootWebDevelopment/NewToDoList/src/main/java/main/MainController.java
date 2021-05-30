package main;

import java.util.ArrayList;
import java.util.List;
import main.model.CaseForm;
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
  public class MainController {

  @Autowired
  private CaseRepository caseRepository;

    Iterable<CriminalCase> criminalCaseIterable = caseRepository.findAll();
    List<CriminalCase> listOfCase = Streamable.of(criminalCaseIterable).toList();

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
      model.addAttribute("message", message);
      return "index";
    }

    @RequestMapping(value = { "/caseList" }, method = RequestMethod.GET)
    public String caseList(Model model) {
      model.addAttribute("cases", listOfCase);
      return "caseList";
    }

    @RequestMapping(value = { "/addCase" }, method = RequestMethod.GET)
    public String showAddCasePage(Model model) {

      CaseForm caseForm = new CaseForm();
      model.addAttribute("caseForm", caseForm);
      return "addCase";
    }

    @RequestMapping(value = { "/addCase" }, method = RequestMethod.POST)
    public String saveCase(Model model, //
        @ModelAttribute("caseForm") CaseForm caseForm) {

      Integer numberCase  = caseForm.getNumberCase();
      String descriptionCase = caseForm.getDescriptionCase();

      if (numberCase != null && numberCase > 0 //
          && descriptionCase != null && descriptionCase.length() > 0) {
        CriminalCase newCriminalCase = new CriminalCase(numberCase, descriptionCase);
        CriminalCase addedCase = caseRepository.save(newCriminalCase);
        return "redirect:/caseList";
      }
      model.addAttribute("errorMessage", errorMessage);
      return "addCase";
    }
  }
