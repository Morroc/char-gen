package web;

import entity.Flaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import services.FlawService;

/**
 * User: artemk
 * Date: 8/18/14
 * Time: 1:07 PM
 */
@Controller
@SessionAttributes
public class FlawManagerController {
    @Autowired
    private FlawService flawService;

    @RequestMapping("/flawManager")
    public String pageModel(Model model) {

        model.addAttribute("flaw", new Flaw());
        model.addAttribute("flawsList", flawService.getAllFlaws());

        return "flaw_manager";
    }

    @RequestMapping(value = "/addFlaw", method = RequestMethod.POST)
    public String addFlaw(@ModelAttribute("flaw") Flaw flaw,
                           BindingResult result) {

        flawService.addFlaw(flaw);

        return "redirect:/flawManager";
    }

    @RequestMapping("/deleteFlaw/{flawId}")
    public String deleteFlaw(@PathVariable("flawId") Integer flawId) {

        flawService.deleteFlawById(flawId);

        return "redirect:/flawManager";
    }
}
