
package com.vs.alafe.controller.pages;
import com.vs.alafe.model.dto.ClienteDTO;
import com.vs.alafe.model.dto.EventoDTO;
import com.vs.alafe.model.entities.Cliente;
import com.vs.alafe.model.entities.Colonia;
import com.vs.alafe.model.entities.Evento;
import com.vs.alafe.service.ColoniaService;
import com.vs.alafe.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/alafe/v1/main")
public class MainAppController {

    @Autowired
    private ColoniaService coloniaService;
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public String mainApp(Model model)
    {
        List<Colonia> colonias = coloniaService.findAll();
        model.addAttribute("colonias", colonias);
        return "mainApp/main";
    }


}