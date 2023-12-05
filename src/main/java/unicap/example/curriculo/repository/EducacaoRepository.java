package unicap.example.curriculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unicap.example.curriculo.domain.entity.Educacao;

public interface EducacaoRepository extends JpaRepository<Educacao, Long> {
}