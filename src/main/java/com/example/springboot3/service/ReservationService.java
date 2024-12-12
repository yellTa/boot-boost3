package com.example.springboot3.service;

import static com.example.springboot3.Const.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot3.Dao.ReservationDao;
import com.example.springboot3.Dao.repository.ReservationInfoPriceRepository;
import com.example.springboot3.Dto.CategoryItemDTO;
import com.example.springboot3.Dto.DisplayInfoImageDTO;
import com.example.springboot3.Dto.DisplayInfoItemDTO;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoDTO;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoPriceDTO;
import com.example.springboot3.Dto.JPAEntity.UserReservationDTO;
import com.example.springboot3.Dto.ProductImageDTO;
import com.example.springboot3.Dto.ProductPriceDTO;
import com.example.springboot3.Dto.PromotionItemDTO;
import com.example.springboot3.Dto.ReservationUserCommentDTO;
import com.example.springboot3.Dto.ReservationUserCommentImageDTO;
import com.example.springboot3.customException.SaveReservationPriceAndUserReservationException;
import com.example.springboot3.service.utils.DTOMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

	private final ReservationDao reservationDao;
	private final ReservationInfoPriceRepository reservationInfoPriceRepository;
	private final DTOMapper dtoMapper;

	public List<CategoryItemDTO> getCategoryInfoWithProductCount() {
		return reservationDao.getCategoryWithProductCount();
	}

	public List<DisplayInfoItemDTO> getDisplayInfoWithStartNumber(int categoryId, int start) {

		List<DisplayInfoItemDTO> result = new ArrayList<>();
		if (categoryId == NO_CATEGORY) {
			result = reservationDao.getAllDisplayItemInfo();
		}
		if (categoryId != NO_CATEGORY) {
			result = reservationDao.getDisplayItemInfo(categoryId, start);
		}
		return result;
	}

	public int getAllDisplayInfoCount(int categoryId) {
		int result = NO_CATEGORY;
		if (categoryId == NO_CATEGORY) {
			result = reservationDao.getAllDisplayItemInfoCount();
		}
		if (categoryId != NO_CATEGORY) {
			result = reservationDao.getCategoryDisplayItemInfoCount(categoryId);
		}
		return result;
	}

	public List<PromotionItemDTO> getPromotionItems() {
		return reservationDao.getPromotionItemInfo();
	}

	public DisplayInfoItemDTO getDisplayInfoWithDisplayId(int displayId) {
		return reservationDao.getDisplayInfoItemWithDisplayId(displayId);
	}

	public List<ProductImageDTO> getProductImageWithDisplayId(int displayId) {
		return reservationDao.getProductImageWithDisplayId(displayId);
	}

	public List<DisplayInfoImageDTO> getDisplayInfoImageWithDisplayId(int displayId) {
		return reservationDao.getDisplayInfoImages(displayId);
	}

	public int getAvgScoreWithDisplayId(int displayId) {
		return reservationDao.getAvgScoreOfReservationUserComment(displayId);
	}

	public List<ProductPriceDTO> getProductPriceWithDisplayId(int displayId) {
		return reservationDao.getProductPricesOrderByRateDesc(displayId);
	}

	public int getTotalCommentCount() {
		return reservationDao.getCommentTotalCount();
	}

	public List<ReservationUserCommentDTO> getReservationUserCommentWithProductId(int productId, int start) {
		List<ReservationUserCommentDTO> reservationUserComments = reservationDao.getReservationUserCommentWithProductId(
			productId, start);

		reservationUserComments.forEach(reservationUserComment -> {
			int reservationInfoId = reservationUserComment.getReservationInfoId();
			List<ReservationUserCommentImageDTO> reservationUserCommentImages = reservationDao.getReservationUserCommentImageWithReservationInfoId(
				reservationInfoId);
			reservationUserComment.setReservationUserCommentImages(reservationUserCommentImages);
		});

		return reservationUserComments;
	}

	@Transactional
	public ReservationInfoDTO saveReservationInfo(ReservationInfoDTO reservationInfo) {
		try {
			return reservationDao.saveReservationInfo(reservationInfo);
		} catch (Exception e) {
			log.info("save error! ={}", e);
			throw e;
		}
	}

	@Transactional
	public void saveReservationInfoPriceAndUserReservation(ReservationInfoPriceDTO reservationInfoPrice, UserReservationDTO userReservationInfo) {
		try {
			reservationDao.saveReservationInfoPrice(reservationInfoPrice);
			reservationDao.saveUserReservationInfo(userReservationInfo);
		} catch (SaveReservationPriceAndUserReservationException e) {
			log.info("save error!= {}", e);
			throw new SaveReservationPriceAndUserReservationException(e.getMessage());
		}
	}

	public ReservationInfoPriceDTO getSavedReservationInfoPrice(int savedReservationInfoId) {
		return reservationDao.getReservationPriceInfo(savedReservationInfoId);

	}

	public void deleteReservationInfo(ReservationInfoDTO savedReservationInfo) {
		reservationDao.deleteReservationInfo(savedReservationInfo);
	}

	@Transactional
	public void cancelReservation(int reservationId) {
		ReservationInfoDTO reservationInfo = reservationDao.getReservationInfo(reservationId);
		ReservationInfoDTO cancelledReservationInfo = dtoMapper.createCancelledReservationInfo(reservationInfo);
		reservationDao.updateCancelFlag(cancelledReservationInfo);
	}
}
