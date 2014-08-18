package web;

import entity.Merit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.MeritService;

/**
 * User: artemk
 * Date: 8/17/14
 * Time: 9:00 PM
 */
@Controller
@SessionAttributes
public class MeritManagerController {
    @Autowired
    private MeritService meritService;

    @RequestMapping("/meritManager")
    public String pageModel(Model model) {

        model.addAttribute("merit", new Merit());
        model.addAttribute("meritsList", meritService.getAllMerits());

        return "merit_manager";
    }

    @RequestMapping(value = "/addMerit", method = RequestMethod.POST)
    public String addMerit(@ModelAttribute("merit") Merit merit,
                                   BindingResult result) {

        meritService.addMerit(merit);

        return "redirect:/meritManager";
    }

    @RequestMapping("/deleteMerit/{meritId}")
    public String deleteMerit(@PathVariable("meritId") Integer meritId) {

        meritService.deleteMeritById(meritId);

        return "redirect:/meritManager";
    }
}
