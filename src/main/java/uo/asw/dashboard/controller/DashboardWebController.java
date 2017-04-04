package uo.asw.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class DashboardWebController {

    @RequestMapping("/dashboard")
    public String enterDashboard(){
        return "dashboard/login";
    }

    @RequestMapping(value = "/dashboard/login", method = RequestMethod.POST)
    public String doLogin(HttpSession session, @RequestParam String user, @RequestParam String password, Model model){
        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "dashboard/login";
    }

}
