package com.xuanc.chg.control;

import com.xuanc.chg.model.Performance;
import com.xuanc.chg.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

/**
 * ClassName    chg-PerformanceControl
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/23 下午8:26
 * @version     1.0
 */
@Controller
@RequestMapping("/performance")
public class PerformanceControl {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/admin/add")
    public String addPerformance(RedirectAttributesModelMap modelMap,
                                 @ModelAttribute("performance") Performance per) {
        String msg = null;

        try {
            performanceService.addPerformance(per);
            msg = "添加演出厅成功";
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            msg = "添加失败！演出厅名字+地点发生冲突！";
        }

        modelMap.addFlashAttribute("msg", msg);

        return "redirect:/admin/per-add";
    }

    @GetMapping("/admin/del/{id}")
    public String delPerformance(@PathVariable("id") int id, RedirectAttributesModelMap modelMap) {

        String msg =  performanceService.delPerformance(id);
        modelMap.addFlashAttribute("msg", msg);

        System.out.println("DEBUG:: msg => " + msg);

        return "redirect:/admin/per-show";
    }

    /**
     * 跳转到修改页面
     */
    @GetMapping("/admin/mod/{id}")
    public String modPerformance(@PathVariable("id") int id, Model model, RedirectAttributesModelMap modelMap) {
        String msg = null;
        Performance performance = performanceService.getPerformance(id);
        if (performance == null) {
            msg = "演出厅不存在！";
            modelMap.addFlashAttribute("msg", msg);
            return "redirect:/admin/per-show";
        }
        model.addAttribute("performance", performance);
        return "admin/per-mod";
    }

    /**
     * 修改逻辑
     */
    @PostMapping("/admin/modify")
    public String modifyPerformance(@ModelAttribute("performance") Performance performance,
                                    RedirectAttributesModelMap modelMap) {
        String msg = null;
        try {
            if (performanceService.updatePerformance(performance)) {
                msg = "修改成功";
            } else {
                msg = "演出厅不存在";
            }
            modelMap.addFlashAttribute("msg", msg);
            return "redirect:/admin/per-show";
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            msg = "修改失败！演出厅名字+区域发生冲突!";
            modelMap.addFlashAttribute("msg", msg);
            return "redirect:/performance/admin/mod/" + performance.getPerformanceId();
        }

    }

}
