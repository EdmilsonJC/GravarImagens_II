package br.com.GravaImagem_II.GravarImagem_II.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.GravaImagem_II.GravarImagem_II.model.ImageEntity;
import br.com.GravaImagem_II.GravarImagem_II.repository.ImageRepository;

@Service
public class ImageService {
	
	 @Autowired
	    private ImageRepository imageRepository;

	    /**
	     * Salva uma nova imagem ou atualiza uma imagem existente.
	     * @param imageData Dados da imagem em bytes.
	     * @return A entidade da imagem salva.
	     */
	    public ImageEntity saveImage(byte[] imageData) {
	        ImageEntity imageEntity = new ImageEntity();
	        imageEntity.setImageData(imageData);
	        return imageRepository.save(imageEntity);
	    }

	    /**
	     * Atualiza uma imagem existente.
	     * @param imageEntity A entidade da imagem a ser atualizada.
	     * @return A entidade da imagem atualizada.
	     */
	    public ImageEntity saveImage(ImageEntity imageEntity) {
	        return imageRepository.save(imageEntity);
	    }

	    /**
	     * Recupera uma imagem pelo ID.
	     * @param id ID da imagem.
	     * @return Entidade da imagem, se encontrada.
	     */
	    public Optional<ImageEntity> getImage(Long id) {
	        return imageRepository.findById(id);
	    }

	    /**
	     * Deleta uma imagem pelo ID.
	     * @param id ID da imagem a ser deletada.
	     */
	    public void deleteImage(Long id) {
	        imageRepository.deleteById(id);
	    }
	    
	    public List<ImageEntity> getAllImages() {
	        return imageRepository.findAll(); // Supondo que imageRepository é uma instância de um repositório Spring Data JPA
	    }

}
