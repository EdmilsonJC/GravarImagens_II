package br.com.GravaImagem_II.GravarImagem_II.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.GravaImagem_II.GravarImagem_II.model.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long>{

}
