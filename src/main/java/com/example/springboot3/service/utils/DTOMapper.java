package com.example.springboot3.service.utils;

import static com.example.springboot3.Const.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.springboot3.Dao.ReservationDao;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoDTO;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoPriceDTO;
import com.example.springboot3.Dto.JPAEntity.UserReservationDTO;
import com.example.springboot3.Dto.PricesRequestDTO;
import com.example.springboot3.Dto.ReservationInfosRequestDTO;
import com.example.springboot3.Dto.SavedReservationInfoDTO;
import com.example.springboot3.Dto.SavedReservationInfoWithPriceDTO;
import com.example.springboot3.Dto.SavedReservationPriceDTO;
import com.example.springboot3.Dto.UserDTO;
import com.example.springboot3.Dto.response.ReservationInfoResponseDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DTOMapper {
	private final ReservationDao reservationDao;

	public ReservationInfoResponseDTO createReservationInfoResponse(ReservationInfoDTO savedReservationInfo,
		ReservationInfoPriceDTO savedReservationInfoPrice, int userId) {
		List<ReservationInfoPriceDTO> priceList = new ArrayList<>();
		priceList.add(savedReservationInfoPrice);

		ModelMapper mapper = new ModelMapper();

		mapper.createTypeMap(ReservationInfoDTO.class, ReservationInfoResponseDTO.class)
			  .setConverter(context -> {
				  ReservationInfoDTO src = context.getSource();
				  return ReservationInfoResponseDTO.builder()
												   .id(src.getId())
												   .productId(src.getProductId())
												   .cancelFlag(src.getCancelFlag())
												   .displayInfoId(src.getDisplayInfoId())
												   .userId(userId)
												   .reservationDate(Timestamp.valueOf(src.getReservationDate()))
												   .createDate(Timestamp.valueOf(src.getCreateDate()))
												   .modifyDate(Timestamp.valueOf(src.getModifyDate()))
												   .build();

			  });

		ReservationInfoResponseDTO reservationResult = mapper.map(savedReservationInfo, ReservationInfoResponseDTO.class);

		reservationResult.setPrices(priceList);

		return reservationResult;
	}

	public UserReservationDTO createUserReservationInfo(ReservationInfosRequestDTO reservationInfosRequestDTO, int savedReservationInfoId) {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.typeMap(ReservationInfosRequestDTO.class, UserReservationDTO.class)
				   .setProvider(request -> {
					   ReservationInfosRequestDTO src = (ReservationInfosRequestDTO)request.getSource();

					   return UserReservationDTO.builder()
												.userId(src.getUserId())
												.reservationInfoId(savedReservationInfoId)
												.build();

				   });
		return modelMapper.map(reservationInfosRequestDTO, UserReservationDTO.class);
	}

	public ReservationInfoPriceDTO createReservationInfoPrice(ReservationInfosRequestDTO reservationInfosRequestDTO, int savedReservationInfoId) {

		PricesRequestDTO price = reservationInfosRequestDTO.getPrices()
														   .getFirst();
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.typeMap(PricesRequestDTO.class, ReservationInfoPriceDTO.class)
				   .setProvider(request -> {
					   PricesRequestDTO src = (PricesRequestDTO)request.getSource();

					   return ReservationInfoPriceDTO.builder()
													 .reservationInfoId(savedReservationInfoId)
													 .productPriceId(src.getProductPriceId())
													 .count(src.getCount())
													 .build();
				   });

		return modelMapper.map(price, ReservationInfoPriceDTO.class);
	}

	public ReservationInfoDTO createReservationInfoDTO(ReservationInfosRequestDTO reservationInfosRequest) {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.typeMap(ReservationInfosRequestDTO.class, ReservationInfoDTO.class)
				   .setProvider(request -> {
					   ReservationInfosRequestDTO src = (ReservationInfosRequestDTO)request.getSource();

					   UserDTO reservedUser = reservationDao.getReservedUserInfo(reservationInfosRequest.getUserId());

					   return ReservationInfoDTO.builder()
												.productId(src.getProductId())
												.displayInfoId(src.getDisplayInfo())
												.reservationName(reservedUser.getName())
												.reservationTel(reservedUser.getTel())
												.reservationEmail(reservedUser.getEmail())
												.reservationDate(LocalDateTime.of(src.getReservationYearMonthDay(), LocalTime.now()))
												.createDate(LocalDateTime.of(LocalDate.now(), LocalTime.now()))
												.modifyDate(LocalDateTime.of(LocalDate.now(), LocalTime.now()))
												.build();
				   });

		return modelMapper.map(reservationInfosRequest, ReservationInfoDTO.class);
	}

	public ReservationInfoDTO createCancelledReservationInfo(ReservationInfoDTO reservationInfo) {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.typeMap(ReservationInfosRequestDTO.class, ReservationInfoDTO.class)
				   .setProvider(request -> {
					   ReservationInfosRequestDTO src = (ReservationInfosRequestDTO)request.getSource();

					   return ReservationInfoDTO.builder()
												.productId(src.getProductId())
												.displayInfoId(src.getDisplayInfo())
												.reservationName(reservationInfo.getReservationName())
												.reservationTel(reservationInfo.getReservationTel())
												.reservationEmail(reservationInfo.getReservationEmail())
												.reservationDate(reservationInfo.getReservationDate())
												.cancelFlag(CANCELLED)
												.createDate(reservationInfo.getCreateDate())
												.modifyDate(reservationInfo.getModifyDate())
												.build();
				   });

		return modelMapper.map(reservationInfo, ReservationInfoDTO.class);
	}

	public SavedReservationInfoWithPriceDTO createSavedReservationInfo(SavedReservationInfoDTO reservationInfo,
		SavedReservationPriceDTO reservationInfoPrice, int userId) {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.typeMap(SavedReservationInfoDTO.class, SavedReservationInfoWithPriceDTO.class)
				   .setProvider(request -> {
					   SavedReservationInfoDTO src = (SavedReservationInfoDTO)request.getSource();

					   return SavedReservationInfoWithPriceDTO.builder()
															  .id(reservationInfo.getReservationId())
															  .productId(reservationInfo.getProductId())
															  .displayInfoId(reservationInfo.getDisplayInfoId())
															  .cancelFlag(reservationInfo.getCancelFlag())
															  .productDescription(reservationInfo.getProductDescription())
															  .productContent(reservationInfo.getProductContent())
															  .userId(userId)
															  .reservationDate(reservationInfo.getReservationDate())
															  .createDate(reservationInfo.getCreateDate())
															  .modifyDate(reservationInfo.getModifyDate())
															  .build();
				   });

		SavedReservationInfoWithPriceDTO initResult = modelMapper.map(reservationInfo, SavedReservationInfoWithPriceDTO.class);
		return initResult.toBuilder()
						 .sumPrice(reservationInfoPrice.getCount() * reservationInfoPrice.getPrice())
						 .build();

	}
}
