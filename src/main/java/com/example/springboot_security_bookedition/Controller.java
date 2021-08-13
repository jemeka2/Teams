package com.example.springboot_security_bookedition;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Binding;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    UserRepo userRepo;

    @Autowired
    TeamRepo teamRepo;

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    CloudinaryConfig cloudc;


    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/processregister")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        if(result.hasErrors()){
            user.clearPassword();
            model.addAttribute("user", user);
            return "register";
        }else{
            model.addAttribute("user", user);
            model.addAttribute("message", "New user account created");
            user.setEnabled(true);
            userRepo.save(user);

            Role role = new Role(user.getUsername(), "ROLE_USER");
            roleRepo.save(role);
        }
        return "index";
    }


    @RequestMapping("/home")
    public String home(){
        return "index";
    }
    @RequestMapping("/teams")
    public String teams(Model model){
        model.addAttribute("teams", teamRepo.findAll());
        return "teams";
    }
    @RequestMapping("/players")
    public String players(Model model){
        model.addAttribute("team", new Team());
        model.addAttribute("players", playerRepo.findAll());
        model.addAttribute("teams", teamRepo.findAll());
        return "players";
    }


    @GetMapping("/addTeam")
    public String addTeam(Model model){
        model.addAttribute("team", new Team());
        return "teamForm";
    }

    @RequestMapping("/updateTeam/{id}")
    public String updateTeam(@PathVariable("id") long id, Model model){
        model.addAttribute("team", teamRepo.findById(id).get());
        return "teamForm";
    }
    @RequestMapping("/deleteTeam/{id}")
    public String deleteTeam(@PathVariable("id") long id){
        teamRepo.deleteById(id);
        return "redirect:/teams";
    }

    @PostMapping("/processTeam")
    public String processTeam(@Valid Team team, BindingResult result){
        if(result.hasErrors()){
            return "teamForm";
        }
        teamRepo.save(team);
        return "redirect:/teams";
    }


    @GetMapping("/addPlayer")
    public String addPlayer(Model model){
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamRepo.findAll());
        return "playerForm";
    }

    @RequestMapping("/updatePlayer/{id}")
    public String updatePlayer(@PathVariable("id") long id, Model model){
        model.addAttribute("teams", teamRepo.findAll());
        model.addAttribute("player", playerRepo.findById(id).get());
        return "playerForm";
    }
    @RequestMapping("/detailPlayer/{id}")
    public String detailPlayer(@PathVariable("id") long id, Model model){
        model.addAttribute("player", new Player());
        model.addAttribute("player", playerRepo.findById(id).get());
        return "processPlayer";
    }
    @RequestMapping("/deletePlayer/{id}")
    public String deletePlayer(@PathVariable("id") long id){
        playerRepo.deleteById(id);
        return "redirect:/players";
    }

    @PostMapping("processPlayer")
    public String processPlayer(@Valid Player player, BindingResult result, @RequestParam(name = "playerImg")MultipartFile file){
        if(result.hasErrors()){
            return "playerForm";
        }
        else{
            if(file.isEmpty() && (player.getPhoto() == null || player.getPhoto().isEmpty())){
                player.setPhoto("https://res.cloudinary.com/jabiremeka/image/upload/v1628774866/1200px-Question_Mark.svg_v72lrj.png");
            }
            else if(!file.isEmpty()){
                try{
                    Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourceType", "auto"));
                    player.setPhoto(uploadResult.get("url").toString());
                }catch(IOException e){
                    e.printStackTrace();
                    return "redirect:/addPlayer";
                }
            }
        }
        playerRepo.save(player);
        return "processPlayer";
    }


    @RequestMapping("/login")
    public String login(){return "login";}
    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout=true";
    }
}

