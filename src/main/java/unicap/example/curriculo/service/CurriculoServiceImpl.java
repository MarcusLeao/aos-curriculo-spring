package unicap.example.curriculo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unicap.example.curriculo.domain.dto.CurriculoDTO;
import unicap.example.curriculo.domain.dto.EducacaoDTO;
import unicap.example.curriculo.domain.dto.ExperienciaDTO;
import unicap.example.curriculo.domain.entity.Curriculo;
import unicap.example.curriculo.domain.entity.Educacao;
import unicap.example.curriculo.domain.entity.Experiencia;
import unicap.example.curriculo.repository.CurriculoRepository;
import unicap.example.curriculo.repository.EducacaoRepository;
import unicap.example.curriculo.repository.ExperienciaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurriculoServiceImpl implements CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private ExperienciaRepository experienciaRepository;

    @Autowired
    private EducacaoRepository educacaoRepository;

    @Override
    public List<CurriculoDTO> getAllCurriculos() {
        return curriculoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CurriculoDTO> getCurriculoById(Long id) {
        return curriculoRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public CurriculoDTO createCurriculo(CurriculoDTO curriculoDTO) {
        Curriculo curriculo = convertToEntity(curriculoDTO);
        Curriculo savedCurriculo = curriculoRepository.save(curriculo);
        return convertToDTO(savedCurriculo);
    }

    @Override
    public Optional<CurriculoDTO> updateCurriculo(Long id, CurriculoDTO curriculoDTO) {
        Optional<Curriculo> existingCurriculoOptional = curriculoRepository.findById(id);

        if (existingCurriculoOptional.isPresent()) {
            Curriculo existingCurriculo = existingCurriculoOptional.get();
            existingCurriculo.setNome(curriculoDTO.getNome());

            Curriculo updatedCurriculo = curriculoRepository.save(existingCurriculo);
            return Optional.of(convertToDTO(updatedCurriculo));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteCurriculo(Long id) {
        curriculoRepository.deleteById(id);
    }

    private CurriculoDTO convertToDTO(Curriculo curriculo) {
        CurriculoDTO curriculoDTO = new CurriculoDTO();
        curriculoDTO.setId(curriculo.getId());
        curriculoDTO.setNome(curriculo.getNome());

        List<ExperienciaDTO> experienciaDTOList = curriculo.getExperiencias().stream()
                .map(this::convertExperienciaToDTO)
                .collect(Collectors.toList());
        curriculoDTO.setExperiencias(experienciaDTOList);

        List<EducacaoDTO> educacaoDTOList = curriculo.getEducacoes().stream()
                .map(this::convertEducacaoToDTO)
                .collect(Collectors.toList());
        curriculoDTO.setEducacoes(educacaoDTOList);



        return curriculoDTO;
    }

    private ExperienciaDTO convertExperienciaToDTO(Experiencia experiencia) {
        ExperienciaDTO experienciaDTO = new ExperienciaDTO();
        experienciaDTO.setId(experiencia.getId());
        experienciaDTO.setCargo(experiencia.getCargo());
        experienciaDTO.setEmpresa(experiencia.getEmpresa());
        experienciaDTO.setDataInicio(experiencia.getDataInicio());
        experienciaDTO.setDataFim(experiencia.getDataFim());

        return experienciaDTO;
    }

    private EducacaoDTO convertEducacaoToDTO(Educacao educacao) {
        EducacaoDTO educacaoDTO = new EducacaoDTO();
        educacaoDTO.setId(educacao.getId());
        educacaoDTO.setInstituicao(educacao.getInstituicao());
        educacaoDTO.setCurso(educacao.getCurso());
        educacaoDTO.setDataInicio(educacao.getDataInicio());
        educacaoDTO.setDataFim(educacao.getDataFim());

        return educacaoDTO;
    }

    private Curriculo convertToEntity(CurriculoDTO curriculoDTO) {
        Curriculo curriculo = new Curriculo();
        curriculo.setId(curriculoDTO.getId());
        curriculo.setNome(curriculoDTO.getNome());

        List<Experiencia> experienciaList = curriculoDTO.getExperiencias().stream()
                .map(this::convertExperienciaDTOToEntity)
                .collect(Collectors.toList());
        curriculo.setExperiencias(experienciaList);

        List<Educacao> educacaoList = curriculoDTO.getEducacoes().stream()
                .map(this::convertEducacaoDTOToEntity)
                .collect(Collectors.toList());
        curriculo.setEducacoes(educacaoList);


        return curriculo;
    }

    private Experiencia convertExperienciaDTOToEntity(ExperienciaDTO experienciaDTO) {
        Experiencia experiencia = new Experiencia();
        experiencia.setId(experienciaDTO.getId());
        experiencia.setCargo(experienciaDTO.getCargo());
        experiencia.setEmpresa(experienciaDTO.getEmpresa());
        experiencia.setDataInicio(experienciaDTO.getDataInicio());
        experiencia.setDataFim(experienciaDTO.getDataFim());

        return experiencia;
    }

    private Educacao convertEducacaoDTOToEntity(EducacaoDTO educacaoDTO) {
        Educacao educacao = new Educacao();
        educacao.setId(educacaoDTO.getId());
        educacao.setInstituicao(educacaoDTO.getInstituicao());
        educacao.setCurso(educacaoDTO.getCurso());
        educacao.setDataInicio(educacaoDTO.getDataInicio());
        educacao.setDataFim(educacaoDTO.getDataFim());

        return educacao;
    }
}