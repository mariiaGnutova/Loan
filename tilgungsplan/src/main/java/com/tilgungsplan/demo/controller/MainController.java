package com.tilgungsplan.demo.controller;

import com.tilgungsplan.demo.dataAccessObject.TilgungsDAO;
import com.tilgungsplan.demo.entity.TilgungDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private TilgungsDAO tilgungsDAO;

    @ResponseBody
    @RequestMapping("/")
    public String index(){
        Iterable<TilgungDO> all = tilgungsDAO.findAll();
        StringBuilder output = new StringBuilder();
        all.forEach(p -> output.append(p.getDatum() + "<br>"));
        return output.toString();
    }
}
