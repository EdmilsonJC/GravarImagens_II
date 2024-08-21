package br.com.GravaImagem_II.GravarImagem_II.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.GravaImagem_II.GravarImagem_II.model.ImageEntity;
import br.com.GravaImagem_II.GravarImagem_II.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {
	
	 @Autowired
	    private ImageService imageService;

	 @PostMapping("/gravar")
	    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
	        try {
	            byte[] imageData = file.getBytes();
	            ImageEntity imageEntity = imageService.saveImage(imageData);
	            return ResponseEntity.status(HttpStatus.CREATED).body(imageEntity);
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving image");
	        }
	    }

	  // Endpoint para obter uma lista de imagens
	 @GetMapping("/listar")
	 public ResponseEntity<List<ImageEntity>> getAllImages() {
	     List<ImageEntity> images = imageService.getAllImages();
	     
	     if (images.isEmpty()) {
	         return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // No Content (204) se não houver imagens
	     }
	     
	     return ResponseEntity.ok(images);
	 }

	    // Endpoint para atualizar uma imagem existente pelo ID
	    @PutMapping("/{id}")
	    public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
	        Optional<ImageEntity> existingImage = imageService.getImage(id);
	        if (existingImage.isPresent()) {
	            try {
	                byte[] imageData = file.getBytes();
	                ImageEntity imageEntity = existingImage.get();
	                imageEntity.setImageData(imageData);
	                imageService.saveImage(imageEntity);
	                return ResponseEntity.ok(imageEntity);
	            } catch (IOException e) {
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating image");
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
	        }
	    }

	    // Endpoint para deletar uma imagem pelo ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
	        Optional<ImageEntity> existingImage = imageService.getImage(id);
	        if (existingImage.isPresent()) {
	            imageService.deleteImage(id);
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
	        }
	    }
}
