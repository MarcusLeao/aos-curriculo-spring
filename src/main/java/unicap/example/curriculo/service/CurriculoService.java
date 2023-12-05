package unicap.example.curriculo.service;

import java.util.List;
import java.util.Optional;

import unicap.example.curriculo.domain.dto.CurriculoDTO;

public interface CurriculoService {
    List<CurriculoDTO> getAllCurriculos();
    Optional<CurriculoDTO> getCurriculoById(Long id);
    CurriculoDTO createCurriculo(CurriculoDTO curriculoDTO);
    Optional<CurriculoDTO> updateCurriculo(Long id, CurriculoDTO curriculoDTO);
    void deleteCurriculo(Long id);
}