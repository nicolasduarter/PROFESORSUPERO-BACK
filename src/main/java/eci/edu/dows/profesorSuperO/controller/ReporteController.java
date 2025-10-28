package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.DTOS.Request.GrupoDTO;
import eci.edu.dows.profesorSuperO.service.Interfaces.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    @Autowired
    public ReporteController(ReporteService reporteServiceImpl) {
        this.reporteService = reporteServiceImpl;
    }

    @GetMapping("/tasa-aprobacion")
    public float getTasaAprobacion() {
        return reporteService.tasaAprovacionSolicitudes();
    }

    @GetMapping("/tasa-rechazo")
    public float getTasaRechazo() {
        return reporteService.tasaRechazoSolicitudes();
    }

    @GetMapping("/grupos-mas-solicitados")
    public List<GrupoDTO> getGruposMasSolicitados(@RequestParam(defaultValue = "3") int cantidad) {
        return reporteService.gruposMasSolicitados(cantidad);
    }
}

