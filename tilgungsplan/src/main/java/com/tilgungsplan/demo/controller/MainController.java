package com.tilgungsplan.demo.controller;

import com.tilgungsplan.demo.dataAccessObject.RepaymentDAO;
import com.tilgungsplan.demo.entity.RepaymentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private RepaymentDAO repaymentDAO;

    @ResponseBody
    @RequestMapping("/")
    public String index(){
        Iterable<RepaymentDO> all = repaymentDAO.findAll();
        StringBuilder output = new StringBuilder();
        all.forEach(p -> output.append(p.getDate() + "<br>"));
        return output.toString();
    }
}
