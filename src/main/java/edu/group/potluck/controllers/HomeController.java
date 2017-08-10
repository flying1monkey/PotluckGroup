package edu.group.potluck.controllers;

import edu.group.potluck.Dishes;
import edu.group.potluck.repositories.PotluckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController
{

    @Autowired
    PotluckRepo potRepo;

    @GetMapping("/welcome")
    public String welcome()
    {
        return "welcome";
    }

    @GetMapping("/adddish")
    public String addDish(Model model)
    {
        model.addAttribute("adddish", new Dishes());
        return "adddish";
    }

    @PostMapping("/adddish")
    public String submitDish(@ModelAttribute("adddish") Dishes dishes)
    {
        potRepo.save(dishes);
        return "confirmation";

    }

    @GetMapping("/search")
    public String search(Model model)
    {
        //Iterable<Dishes> listToDisplay = potRepo.findAll();
        //model.addAttribute("listToDisplay", listToDisplay);
        model.addAttribute("searchTerm", new Dishes());

        return "search";
    }

    @PostMapping("/search")
    public String searchResults(@ModelAttribute("searchdish") Dishes dishes, Model model)
    {
        Iterable<Dishes> listToDisplay = potRepo.findAll();
        if(!dishes.getName().isEmpty())
        {
            listToDisplay = potRepo.findAllByNameContains(dishes.getName());
        }
        else if(!dishes.getDish().isEmpty())
        {
            listToDisplay = potRepo.findAllByDishContains(dishes.getDish());
        }
        model.addAttribute("listToDisplay", listToDisplay);
        return "results";
    }



}
