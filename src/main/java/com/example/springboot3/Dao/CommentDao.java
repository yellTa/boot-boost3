package com.example.springboot3.Dao;

import static com.example.springboot3.Const.*;
import static com.example.springboot3.TableAlias.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import com.example.springboot3.Dto.UploadCommentDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentDao {

	private final DSLContext dsl;

	public int saveFile(String fileName, String fileType) {
		return dsl.insertInto(FI, FI.FILE_NAME, FI.SAVE_FILE_NAME, FI.CONTENT_TYPE, FI.DELETE_FLAG, FI.CREATE_DATE, FI.MODIFY_DATE)
				  .values(fileName, COMMENT_IMAGE_PATH + fileName, fileType, 0, LocalDateTime.now(), LocalDateTime.now())
				  .returning(FI.ID)
				  .fetchOne()
				  .getValue(FI.ID);
	}

	public void saveCommentImage(int infoId, int userCommentId, int fileId) {
		dsl.insertInto(RUCI, RUCI.RESERVATION_INFO_ID, RUCI.RESERVATION_USER_COMMENT_ID, RUCI.FILE_ID)
		   .values(infoId, userCommentId, fileId)
		   .execute();
	}

	public void saveComment(UploadCommentDTO upload, int productId) {
		dsl.insertInto(RUC, RUC.PRODUCT_ID, RUC.RESERVATION_INFO_ID, RUC.SCORE, RUC.COMMENT, RUC.CREATE_DATE, RUC.MODIFY_DATE)
		   .values(productId, upload.getReservationInfoId(), BigDecimal.valueOf(upload.getScore()), upload.getComment(), LocalDateTime.now(),
			   LocalDateTime.now())
		   .execute();
	}

}
