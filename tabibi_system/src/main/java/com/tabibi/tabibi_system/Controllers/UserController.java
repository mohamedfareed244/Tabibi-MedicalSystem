
package com.tabibi.tabibi_system.Controllers;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;
import com.tabibi.tabibi_system.Repositories.*;
import com.tabibi.tabibi_system.Models.Clinic;
import com.tabibi.tabibi_system.Models.Doctor;
import com.tabibi.tabibi_system.Models.Patient;
import com.tabibi.tabibi_system.Models.User;
import com.tabibi.tabibi_system.Models.UserAcc;
import com.tabibi.tabibi_system.Repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import com.tabibi.tabibi_system.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserRepository UserRepository;
   private UserAccRepository UserAccRepository;
   private PatientRepository patientRepository;



    @GetMapping("/Registration")

    public ModelAndView addUser(@RequestParam("userType") String UserType)
    {
    ModelAndView mav=new ModelAndView("Registration.html");
    System.out.println("dakhal controller");

    UserAcc newUser=new UserAcc(); 
     mav.addObject("UserAcc", newUser);
if(UserType=="Patient")
 {
    Patient newpatient=new Patient();
    mav.addObject("Patient", newpatient);
    UserAcc.setUid('1');
  } 
  else if(UserType=="Doctor")
  {
    Doctor newDoctor=new Doctor();
    mav.addObject("Doctor", newDoctor);
    UserAcc.setUid('2');
    
  }
  else {
    Clinic newClinic=new Clinic();
    mav.addObject("Clinic", newClinic);
    UserAcc.setUid('3');
  }

      return mav;
    }

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute UserAcc user ,@ModelAttribute Patient patient)
     {
      String encoddedPassword=BCrypt.hashpw(user.getPass(), BCrypt.gensalt(12)) ;
      user.setPass(encoddedPassword);
      this.UserAccRepository.save(user);
      this.patientRepository.save(patient);
      return "ADDed ya basha to DataBAse";
     }


    @GetMapping("/AllUsers")
     public ModelAndView getUsers()
     {
         ModelAndView mav=new ModelAndView("search.html"); 
         List<User> users=this.UserRepository.findAll();
         mav.addObject("users", users);
         return mav;
     }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("name") String name, Model model) {
      List<User> users = UserRepository.findByName(name); 
       ModelAndView mag=new ModelAndView("searchResult.html");
        model.addAttribute("users", users);
      return mag;
    }

    
   
   @GetMapping("/navigation")
   public ModelAndView getnavigation() {
    ModelAndView mav=new ModelAndView("navigation.html");
       return mav;
   }
     
   @GetMapping("/account-settings")
   public ModelAndView getaccount_settings() {
    ModelAndView mav=new ModelAndView("AccountSettings.html");
       return mav;
   }
   @GetMapping("/footer")
   public ModelAndView getfooter() {
    ModelAndView mav=new ModelAndView("footer.html");
       return mav;
   }
}
