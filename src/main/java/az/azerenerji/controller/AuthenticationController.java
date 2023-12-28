package az.azerenerji.controller;

import az.azerenerji.dto.request.RegisterRequestDto;
import az.azerenerji.model.User;
import az.azerenerji.repository.UserRepository;
import az.azerenerji.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserRepository userRepository;
    private final MessageUtils messageUtils;
    @Value("${my.message.body}")
    private String messageBody;

    @Value("${my.message.subject}")
    private String messageSubject;


    @GetMapping(path = "/homepage")
    public String home() {
        return "homepage";

    }

    @GetMapping(path = "/register")
    public String registerPage() {
        return "registerpage";
    }

    @PostMapping(path = "/register")
    public String registerUser(RegisterRequestDto registerRequestDto, RedirectAttributes redirectAttributes, Model model) {
        String email = registerRequestDto.getEmail();
        User usersByEmail = userRepository.findUsersByEmail(email);
        if (usersByEmail != null) {
            redirectAttributes.addFlashAttribute("errore", "There is email already a email registered");
            model.addAttribute("user", null);
            return "redirect:/register";
        } else if (!registerRequestDto.getPassword().equals(registerRequestDto.getRepeatPassword())) {
            redirectAttributes.addFlashAttribute("errorp", "Password repeat is not same!");
            model.addAttribute("user", null);
            return "redirect:/register";
        }

        User user = new User();
        user.setExpiredDate(LocalDateTime.now().plusMinutes(5).toLocalDate());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(registerRequestDto.getPassword());
        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setActivationCode(UUID.randomUUID().toString());
        User saveduser = userRepository.save(user);
        messageUtils.sendEmail(saveduser.getEmail(), messageSubject, messageBody + "http://localhost:8080/register-confirm?code=" + saveduser.getActivationCode());


        redirectAttributes.addFlashAttribute("infos", "Your registration was succesfully!Pls check your email");
        model.addAttribute("user", null);
        return "redirect:/successful";
    }


    @GetMapping(path = "/successful")
    public String successfulPage() {
        return "successinfopage";
    }

    @GetMapping(path = "/register-config")
    public String registerConfig(@RequestParam(value = "code") String code, RedirectAttributes redirectAttributes) {

        if (Objects.isNull(code)) {
            redirectAttributes.addFlashAttribute("infoe", "Confirmation code is not correct");
            return "redirect:/error-info";
        } else {
            User user = userRepository.findUserByActivationCode(code);
            if (Objects.isNull(user)) {
                redirectAttributes.addFlashAttribute("infoe", "Confirmation code is not correct");
                return "redirect:/error_info";
            } else {
                LocalDateTime expiredData = user.getExpiredDate().atStartOfDay();
                LocalDateTime currentDate = LocalDateTime.now();
                if (expiredData.isBefore(currentDate)) {
                    redirectAttributes.addFlashAttribute("infoex", "Confirmation code is expired");
                    return "redirect:/error-info";
                }
            }
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("infos", "Your account is successfully confirmed");
            return "redirect:/successful";
        }

    }
}
