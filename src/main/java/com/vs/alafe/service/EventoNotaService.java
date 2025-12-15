package com.vs.alafe.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ch.qos.logback.core.util.StringUtil;
import com.vs.alafe.model.dto.EventoNotaDTO;
import com.vs.alafe.model.entities.Evento;
import com.vs.alafe.model.entities.Usuario;
import com.vs.alafe.repository.EventoRepository;
import com.vs.alafe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vs.alafe.model.entities.EventoNota;
import com.vs.alafe.repository.EventoNotaRepository;
import org.thymeleaf.util.StringUtils;

@Service
public class EventoNotaService {

    @Autowired
    private EventoNotaRepository eventoNotaRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EventoRepository eventoRepository;

    @Transactional
    public EventoNota save(EventoNotaDTO eventoNotaDTO, Evento evento) {

        EventoNota eventoNota = new EventoNota();
        boolean notaExiste = false;
        String contenidoNota = StringUtils.trim(eventoNotaDTO.getNota());

        try {
            if (contenidoNota == null) {
                throw new IllegalArgumentException("Una nota no puede guardarse con el contenido vacio.");
            }
            if (eventoNotaDTO.getIdEventoNota() != null) {
                eventoNota = eventoNotaRepository.findById(eventoNotaDTO.getIdEventoNota())
                        .orElseThrow(() -> new IllegalArgumentException("ID evento nota no existe."));
            }

            notaExiste = eventoNota.getIdEventoNota() != null;
            if (notaExiste) {
                eventoNota.setNota(eventoNotaDTO.getNota());
                return  eventoNotaRepository.save(eventoNota);
            }

            if (eventoNotaDTO.getIdUsuarioIngreso() == null) {
                throw new IllegalArgumentException("El evento nota debe contar con usuario ingreso, contenido: " + eventoNotaDTO.getNota());
            }

            eventoNota.setNota(eventoNotaDTO.getNota());
            eventoNota.setFechaIngreso(eventoNotaDTO.getFechaIngreso() != null ? eventoNotaDTO.getFechaIngreso() : LocalDateTime.now());
            eventoNota.setEvento(evento);
            eventoNota.setUsuario(usuarioRepository.findById(eventoNotaDTO.getIdUsuarioIngreso()).get());
            return eventoNotaRepository.save(eventoNota);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());

        }
        return null;
    }



    @Transactional(readOnly = true)
    public Optional<EventoNota> findById(Integer id) {
        return eventoNotaRepository.findById(id);
    }

    @Transactional
    public void delete(EventoNota eventoNota) {
        eventoNotaRepository.delete(eventoNota);
    }

    @Transactional(readOnly = true)
    public List<EventoNota> findAll() {
        return findAll();
    }

    @Transactional(readOnly = true)
    public List<EventoNota> findByEvento_IdEvento(Integer idEvento) {
        return eventoNotaRepository.findByEvento_IdEvento(idEvento);
    }

//    public List<EventoNota> toEventoNotasListado(List<EventoNotaDTO> notasDTO, Evento evento) {
//        System.out.println(notasDTO);
//        List<EventoNota> notas = new ArrayList<>();
//
//        try {
//
//            notasDTO.forEach(nota -> {
//
//                String contenidoNota = StringUtils.trim(nota.getNota());
//
//                if (contenidoNota == null) {
//                    throw new IllegalArgumentException("Una nota no puede ");
//                }
//                EventoNota eventoNota;
//                if (nota.getIdEventoNota() != null) {
//                    eventoNota = eventoNotaRepository.findById(nota.getIdEventoNota())
//                            .orElse(new EventoNota());
//                } else {
//                    eventoNota = new EventoNota(); // nota nueva
//                }
//
//
//                boolean notaExiste = eventoNota.getIdEventoNota() != null;
//
//
//                if (nota.getIdUsuarioIngreso() == null) {
//                    throw new IllegalArgumentException("El evento nota debe contar con usuario ingreso, contenido: " + nota.getNota());
//                }
//
//                eventoNota.setNota(nota.getNota());
//                eventoNota.setFechaIngreso(nota.getFechaIngreso() == null ? nota.getFechaIngreso() : LocalDateTime.now());
//                eventoNota.setEvento(evento);
//                eventoNota.setUsuario(usuarioRepository.findById(nota.getIdUsuarioIngreso()).get());
//
//                if (nota.getFechaIngreso() == null) {
//                    eventoNota.setFechaIngreso(LocalDateTime.now());
//                }
//                System.out.println("/n--------------------------------------------------");
//                System.out.println(eventoNota.toString());
//                notas.add(eventoNota);
//                save(eventoNota);
//            });
//        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
//
//        }
//
//
//    }


}
