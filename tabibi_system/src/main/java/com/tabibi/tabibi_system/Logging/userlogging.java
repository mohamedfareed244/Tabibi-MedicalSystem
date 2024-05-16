package com.tabibi.tabibi_system.Logging;

import java.sql.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

import com.tabibi.tabibi_system.Models.UserLog;
import com.tabibi.tabibi_system.Repositories.UserLogRepository;
import com.tabibi.tabibi_system.Repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class userlogging {
    @Pointcut("execution(* com.tabibi.tabibi_system.Controllers.UserController.loginprocess(..))")
    public void UserLoginPointCut(){

    }
    @AfterReturning(
        pointcut = "UserLoginPointCut()",
        returning = "redirectView")
        public void logSuccessfulLogin(JoinPoint joinPoint, RedirectView redirectView) {
    // Check if redirection is to DoctorHomePage
    if (redirectView != null && ( "/User/DoctorHomePage".equals(redirectView.getUrl()) || "/User/clinicHomepage".equals(redirectView.getUrl()) || "/User/patientHomepage".equals(redirectView.getUrl()) ) ) {
       
        // String userId = (String) session.getAttribute("uid");
     
    //    UserLogRepository userlog;
    //    Date currentDate = new Date(System.currentTimeMillis());
    //    UserLog NewLog=new UserLog();
    Object[] args = joinPoint.getArgs();
    String email = (String) args[0];
    System.out.println("login catched "+email);
    }
 
}
}
