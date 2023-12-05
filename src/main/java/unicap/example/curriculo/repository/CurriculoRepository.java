package unicap.example.curriculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unicap.example.curriculo.domain.entity.Curriculo;

public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
}