package unicap.example.curriculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unicap.example.curriculo.domain.entity.Experiencia;

public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {
}