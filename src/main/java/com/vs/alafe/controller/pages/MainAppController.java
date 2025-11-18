
package com.vs.alafe.controller.pages;
import com.vs.alafe.model.entity.Evento;
import com.vs.alafe.service.IEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainAppController {

    @Autowired
    private IEntity<Evento> eventoService;

    @GetMapping
    public String mainApp(Model model) {


        return "mainApp/main";
    }
}