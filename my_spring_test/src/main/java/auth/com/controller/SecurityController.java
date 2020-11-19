package auth.com.controller;

import java.util.Calendar;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import auth.com.model.SiteUser;
import auth.com.repository.SiteUserRepository;
import auth.com.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    private final SiteUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String showList(Authentication loginUser, Model model) {
        model.addAttribute("username", loginUser.getName());
        model.addAttribute("role", loginUser.getAuthorities());
        return "user";
    }
		
		
		@GetMapping("/inout")
		public String showinout(Authentication loginUser,Model model) {
	        return "inout";
		}
		
		
	    @PostMapping("/in")
	    public String clickedin(Authentication loginUser,Model model) {
	    	Calendar HM =  Calendar.getInstance();
	        model.addAttribute("in", HM.get(Calendar.HOUR_OF_DAY) + ":" +HM.get(Calendar.MINUTE)  + "出勤打刻しました");
	        return "inout";
	    }

	    @PostMapping("/out")
	    public String clickedout(Authentication loginUser,Model model) {
	    	Calendar HM =  Calendar.getInstance();
	        model.addAttribute("out", HM.get(Calendar.HOUR_OF_DAY) + ":" +HM.get(Calendar.MINUTE)  + "退勤打刻しました");
	        return "inout";
	    }
	    
	    
		
		@GetMapping("/admin/list")
		public String showAdminList(Model model) {
			model.addAttribute("users",userRepository.findAll());
			return "list";
		}
		
		@GetMapping("/register")
		public String register(@ModelAttribute("user") SiteUser user) {
			return "register";
		}
		
		@PostMapping("/register")
		public String process(@Validated @ModelAttribute("user") SiteUser user,BindingResult result) {
			if(result.hasErrors()) {
				return "register";
			}
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			if(user.isAdmin()) {
				user.setRole(Role.ADMIN.name());
			} else {
				user.setRole(Role.USER.name());
			}
			userRepository.save(user);
			
		return "redirect:login?register";
		
		}
			
			
	}
	



