package eci.edu.dows.profesorSuperO.controller;

import eci.edu.dows.profesorSuperO.model.Grupo;
import eci.edu.dows.profesorSuperO.service.ReporteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
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
    public List<Grupo> getGruposMasSolicitados(@RequestParam(defaultValue = "3") int cantidad) {
        return reporteService.gruposMasSolicitados(cantidad);
    }
}

