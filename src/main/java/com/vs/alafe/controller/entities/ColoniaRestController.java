package com.vs.alafe.controller.entities;

import com.vs.alafe.model.dto.ColoniaDTO;
import com.vs.alafe.model.entities.Colonia;
import com.vs.alafe.service.ColoniaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/alafe/v1/colonia")
public class ColoniaRestController {

    private final ColoniaService coloniaService;

    public ColoniaRestController(ColoniaService coloniaService) {
        this.coloniaService = coloniaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColoniaDTO> showById(@PathVariable Integer id){
        Optional<Colonia> colonia = coloniaService.findById(id);
        if (colonia == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ColoniaDTO(colonia.orElseThrow()));
    }

//    @GetMapping("colonia")
//    public ResponseEntity<List<ColoniaDTO>> getColonias(){
//        List<Colonia> colonias = coloniaService.findAll();
//        List<ColoniaDTO> dtos = colonias.stream().map(ColoniaDTO::new).toList();
//        return ResponseEntity.ok(dtos);
//    }}

//    @GetMapping("main")
//    public String listarColonias(Model model) {
//        List<ColoniaDTO> dtos = coloniaService.findAll()
//                .stream()
//                .map(ColoniaDTO::new)
//                .toList();
//        model.addAttribute("colonias", dtos);
//        return "mainApp/main";
//    }
//@GetMapping("/form")
//public String form(Model model) {
//    model.addAttribute("colonias", coloniaService.findAll());
//    return "form"; // templates/form.html
//}


}
