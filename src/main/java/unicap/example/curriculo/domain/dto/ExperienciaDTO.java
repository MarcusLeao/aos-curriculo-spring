package unicap.example.curriculo.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExperienciaDTO {
    
    private Long id;
    private String cargo;
    private String empresa;
    private Date dataInicio;
    private Date dataFim;
}
