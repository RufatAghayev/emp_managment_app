package az.azerenerji.controller;

import az.azerenerji.dto.request.RegisterRequestDto;
import az.azerenerji.model.User;
import az.azerenerji.repository.UserRepository;
import az.azerenerji.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserRepository userRepository;
    private final MessageUtils messageUtils;


    @GetMapping(path = "/homepage")
    public String home(){
       return "homepage";

    }
    @GetMapping(path = "/register")
    public String registerPage(){
       return "registerpage";
    }

    @PostMapping(path = "/register")
    public String registerUser(RegisterRequestDto registerRequestDto, RedirectAttributes redirectAttributes, Model model) {
        String email = registerRequestDto.getEmail();
        User usersByEmail = userRepository.findUsersByEmail(email);
        if(usersByEmail!=null){
        redirectAttributes.addFlashAttribute("errore","There is email already a email registered");
                model.addAttribute("user",null);
                return "redirect:/register";
        }else if(!registerRequestDto.getPassword().equals(registerRequestDto.getRepeatPassword())){
            redirectAttributes.addFlashAttribute("errorp","Password repeat is not same!");
             model.addAttribute("user",null);
            return "redirect:/register";
        }

        User user=new User();
        user.setExpiredData(LocalDateTime.now().plusMinutes(5));
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(registerRequestDto.getPassword());
        user.setFirstname(registerRequestDto.getFirstName());
        user.setLastname(registerRequestDto.getLastName());
        User saveduser = userRepository.save(user);
        messageUtils.sendEmail(saveduser.getEmail());


        redirectAttributes.addFlashAttribute("infos","Your registration was succesfully!Pls check your email");
        model.addAttribute("user",null);
        return  "redirect:/successful";
      }


       @GetMapping(path = "/successful")
       public String successfulPage(){
       return "successinfopage";
      }

      @GetMapping(path="/register-config")
      public String registerConfig(@RequestParam(value = "code")String code,RedirectAttributes redirectAttributes){

      }
}
