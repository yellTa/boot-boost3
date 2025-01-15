package com.example.springboot3.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot3.Dto.UploadCommentDTO;
import com.example.springboot3.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<?> commentRegister(@ModelAttribute UploadCommentDTO uploadCommentDTO) {

		int productId = commentService.saveComment(uploadCommentDTO);

		Map<String, Object> response = new HashMap<>();
		response.put("result", "success");
		response.put("productId", productId);

		return ResponseEntity.ok(response);
	}

}
