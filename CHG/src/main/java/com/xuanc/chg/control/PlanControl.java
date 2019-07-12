package com.xuanc.chg.control;

import com.xuanc.chg.model.Plan;
import com.xuanc.chg.service.PlanService;
import com.xuanc.chg.service.TicketService;
import com.xuanc.chg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;

/**
 * ClassName    chg-PlanControl
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/24 下午9:29
 * @version     1.0
 */
@Controller
@RequestMapping("/plan")
public class PlanControl {

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @PostMapping("/admin/add")
    public String addPlan(@ModelAttribute("plan") Plan plan,
                          RedirectAttributesModelMap modelMap) {
        String msg = null;
        try {
            if (planService.addPlan(plan)) {
                msg = "添加成功";
                modelMap.addFlashAttribute("msg", msg);
                return "redirect:/admin/plan-show";
            } else {
                msg = "时间冲突！";
                modelMap.addFlashAttribute("msg", msg);
                return "redirect:/admin/plan-add";
            }
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            msg = "无效的演出厅 id";
            modelMap.addFlashAttribute("msg", msg);
            return "redirect:/admin/plan-add";
        }
    }

    @GetMapping("/admin/del/{id}")
    public String delPlan(@PathVariable("id") int id, RedirectAttributesModelMap modelMap) {
        String msg = planService.delPlan(id);
        modelMap.addFlashAttribute("msg", msg);

        return "redirect:/admin/plan-show";
    }

    @GetMapping("/order/{id}")
    public String orderPlan(@PathVariable("id") int planId, HttpSession session,
                            RedirectAttributesModelMap modelMap) {
        int userId = userService.getUserId((String)session.getAttribute("user"));
        String msg = ticketService.orderPlan(userId, planId);
        modelMap.addFlashAttribute("msg", msg);
        return "redirect:/order";
    }

}
