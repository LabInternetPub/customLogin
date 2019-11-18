package cat.teconcampus.customlogin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Josep");
        return "hello";
    }

    @GetMapping("myLogin")
    public String myLogin() {
        return "myLogin";
    }
}
