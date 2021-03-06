package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class InstitutionController {

    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/admin/institution")
    public String showAll(Model model){
        List<Institution> institutions = institutionService.getAllInst();
        model.addAttribute("institution", institutions );
        return "institution/institution";

    }
    @RequestMapping("/institution/add")
    public  String addUser(Model model){
        model.addAttribute("inst", new Institution());
        return "institution/add";
    }

    //    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PostMapping("/institution/add")
    public String saveAddUser(@Valid Institution institution , BindingResult result) {
        if (result.hasErrors()) {
            return "institution/add";
        }

        institutionService.add(institution);
        return "redirect:/admin/institution";
    }

    @RequestMapping(value = "/admin/institution/edit/{id}")
    public String editUser (Model model, @PathVariable Long id){
        model.addAttribute("inst", institutionService.get(id));
        return "institution/editInst";
    }
    @RequestMapping(value = "/admin/institution/edit/{id}", method = RequestMethod.POST)
    public String saveEditUser (@Valid @ModelAttribute ("institution") Institution institution,
                                @PathVariable Long id, BindingResult result){
        if(result.hasErrors()){
            return "institution/editInst";
        }
        institutionService.add(institution);
        return "redirect:/admin/institution";
    }


    @RequestMapping("/admin/institution/delete/{id}")
    public String deleteInst(@PathVariable long id){
        institutionService.delete(id);
        return "redirect:/admin/institution";
    }
}
