package com.example.springboot3.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot3.Dao.CommentDao;
import com.example.springboot3.Dao.ReservationDao;
import com.example.springboot3.Dto.UploadCommentDTO;
import com.example.springboot3.customException.CommentSaveException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

	private final CommentDao commentDao;
	private final ReservationDao reservationDao;

	@Transactional
	public int saveComment(UploadCommentDTO uploadCommentDTO) {
		try {
			String fileName = uploadCommentDTO.getFile()
											  .getOriginalFilename();
			String fileType = uploadCommentDTO.getFile()
											  .getContentType();

			int fileId = commentDao.saveFile(fileName, fileType);
			int productId = reservationDao.getProductId(uploadCommentDTO.getReservationInfoId());
			commentDao.saveCommentImage(uploadCommentDTO.getReservationInfoId(), uploadCommentDTO.getUserId(), fileId);
			commentDao.saveComment(uploadCommentDTO, productId);

			return productId;
		} catch (Exception e) {
			throw new CommentSaveException();
		}
	}

}
