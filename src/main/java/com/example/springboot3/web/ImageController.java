package com.example.springboot3.web;

import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot3.service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/files")
@RequiredArgsConstructor
public class ImageController {

	private final ImageService imageService;

	@GetMapping
	public ResponseEntity<?> getFile(@RequestParam("fileId") int fileId) {

		Path filePath = imageService.getFilePath(fileId);
		Resource resource = null;
		try {
			resource = new UrlResource(filePath.toUri());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok()
							 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
							 .contentType(MediaType.IMAGE_JPEG)
							 .body(resource);
	}

}
