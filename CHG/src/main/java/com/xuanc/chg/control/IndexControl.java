package com.xuanc.chg.control;

import com.alibaba.fastjson.JSON;
import com.xuanc.chg.model.Performance;
import com.xuanc.chg.model.Plan;
import com.xuanc.chg.model.Ticket;
import com.xuanc.chg.model.User;
import com.xuanc.chg.service.PerformanceService;
import com.xuanc.chg.service.PlanService;
import com.xuanc.chg.service.TicketService;
import com.xuanc.chg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * ClassName    chg-HelloController
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/21 下午10:27
 * @version     1.0
 */
@Controller
public class IndexControl {

    @Autowired
    private UserService userService;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private PlanService planService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/index")
    public String hello() {
        return "index";
    }

    @GetMapping("/introduce")
    public String introduce() {
        return "introduce";
    }

    @GetMapping("/login")
    public String login(Model model) {
        System.out.println("DEBUG:: /login..");
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        System.out.println("DEBUG:: /register..");
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/plot")
    public String plot() {
        return "plot";
    }

    @GetMapping("/seat")
    public String seat() {
        return "seat";
    }

    @GetMapping("/transportation")
    public String transportation() {
        return "transportation";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<User> userList =  userService.getAllUser();
        model.addAttribute("userList", userList);

        System.out.println("DEBUG:: userList = > " + JSON.toJSONString(userList));
        return "admin/index";
    }

    @GetMapping("/admin/reset")
    public String admin() {
        return "admin/reset";
    }

    @GetMapping("/admin/per-add")
    public String addPerformance(Model model) {
        model.addAttribute("performance", new Performance());
        return "admin/per-add";
    }

    @GetMapping("/admin/per-show")
    public String perDetailsShow(Model model) {
        List<Performance> performances = performanceService.getAllPerformance();
        model.addAttribute("perList", performances);

        System.out.println("DEBUG:: perList => " + performances);
        return "admin/per-show";
    }

    @GetMapping("/admin/plan-add")
    public String pageAddPlan(Model model) {
        model.addAttribute("plan", new Plan());
        return "admin/plan-add";
    }

    @GetMapping("/admin/plan-show")
    public String pageShowPlan(Model model) {
        Map<Plan, Performance> map = planService.getAllPlan();
        model.addAttribute("map", map);
        System.out.println("DEBUG:: map => " + map);
        return "admin/plan-show";
    }

    @GetMapping("/order")
    public String pageOrder(Model model) {
        Map<Plan, Performance> map = planService.getAvaPlans();
        model.addAttribute("map", map);
        System.out.println("DEBUG:: map => " + map);
        return "order";
    }

    @GetMapping("/user/user-order")
    public String pageUserOrders(Model model, HttpSession session) {
        int userId = userService.getUserId((String)session.getAttribute("user"));
        List<Ticket> tickets = ticketService.getOrderedTickets(userId);
        model.addAttribute("tickets", tickets);
        return "user/user-order";
    }

}
