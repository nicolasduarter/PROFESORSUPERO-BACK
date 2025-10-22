package eci.edu.dows.profesorSuperO.service.Interfaces;

import eci.edu.dows.profesorSuperO.model.DTOS.GrupoDTO;

import java.util.List;

public interface ReporteService {

    float tasaAprovacionSolicitudes();

    float tasaRechazoSolicitudes();

    List<GrupoDTO> gruposMasSolicitados(int grupos);
}