package com.vs.alafe.controller.entitys;

import com.vs.alafe.model.dto.ColoniaDTO;
import com.vs.alafe.model.entity.Colonia;
import com.vs.alafe.service.IColonia;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/alafe/v1")
public class ColoniaController {

    private final IColonia<Colonia> coloniaService;

    public ColoniaController(IColonia<Colonia> coloniaService) {
        this.coloniaService = coloniaService;
    }

    @GetMapping("colonia/{id}")
    public ResponseEntity<ColoniaDTO> showById(@PathVariable Integer id){
        Colonia colonia = coloniaService.findById(id);
        if (colonia == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ColoniaDTO(colonia));
    }

//    @GetMapping("colonia")
//    public ResponseEntity<List<ColoniaDTO>> getColonias(){
//        List<Colonia> colonias = coloniaService.findAll();
//        List<ColoniaDTO> dtos = colonias.stream().map(ColoniaDTO::new).toList();
//        return ResponseEntity.ok(dtos);
//    }}

    @GetMapping("main")
    public String listarColonias(Model model) {
        List<ColoniaDTO> dtos = coloniaService.findAll()
                .stream()
                .map(ColoniaDTO::new)
                .toList();
        model.addAttribute("colonias", dtos);
        return "mainApp/main";
    }
//@GetMapping("/form")
//public String form(Model model) {
//    model.addAttribute("colonias", coloniaService.findAll());
//    return "form"; // templates/form.html
//}


}
