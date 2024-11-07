package com.example.springboot3.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.springboot3.Dto.DisplayInfoItemDTO;
import com.example.springboot3.Dto.response.DisplayInfoResponseDTO;
import com.example.springboot3.service.ReservationService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController)
            .build();
    }

    @Test
    @DisplayName("displayInfo Test")
    void testDisplayInfos_withCategoryIdAndStart() {
        // Given
        Integer categoryId = 3;
        int start = 14;
        int totalCount = 16;

        List<DisplayInfoItemDTO> displayInfoItemList = Arrays.asList(
            new DisplayInfoItemDTO(
                "35",
                3,
                35,
                "콘서트",
                "2017버즈전국투어콘서트 'JUST ONE'",
                """
                    버즈와 함께하는 단 하나의 특별한 시간 JUST ONE
                    2017년 연말을 더욱 따뜻한 이야기로 그려낼 JUST ONE
                    가장 멋진 내일이 시작될 순간 JUST ONE
                    2017 BUZZ CONCERT JUST ONE
                            
                    버즈 전국투어 콘서트 [JUST ONE]이 시작됩니다.
                    미니앨범 BE ONE 발매와 함께 많은 사랑을 받은 버즈가
                    전국 투어 콘서트로 여러분을 찾아갑니다.
                            
                    ‘사랑하지 않은 것처럼’을 비롯한 미니앨범의 신곡들은 물론.
                    남자를 몰라,겁쟁이, 나에게로 떠나는 여행등
                    노래방 인기순위 랭킹의 곡들을 버즈와 함께 떼창 하는 시간!
                    넘치는 흥을 주체할 수 없도록 신나게 달릴 메들리 곡들까지 만나볼 수 있는 다채로운 콘서트!
                            
                    2017년, 여러분의 연말을 가장 특별하게 마무리 할 단 하나의 공연 JUST ONE
                    Band 버즈의 빛나는 모습으로 가득할 공연장으로 여러분을 초대합니다.""",
                null,
                "12월 17일(일) PM5",
                "KBS부산홀",
                "부산광역시 수영구 남천동 63",
                "부산광역시 수영구 수영로 429 KBS부산방송총국",
                null,
                "http://www.sjticket.com",
                null,
                LocalDateTime.parse("2024-10-23T23:36:05.000"),
                LocalDateTime.parse("2024-10-23T23:36:05.000"),
                131 // fileId
            ),
            new DisplayInfoItemDTO(
                "36",
                3,
                36,
                "콘서트",
                "2017버즈전국투어콘서트 'JUST ONE'",
                """
                    버즈와 함께하는 단 하나의 특별한 시간 JUST ONE
                    2017년 연말을 더욱 따뜻한 이야기로 그려낼 JUST ONE
                    가장 멋진 내일이 시작될 순간 JUST ONE
                    2017 BUZZ CONCERT JUST ONE
                            
                    버즈 전국투어 콘서트 [JUST ONE]이 시작됩니다.
                    미니앨범 BE ONE 발매와 함께 많은 사랑을 받은 버즈가
                    전국 투어 콘서트로 여러분을 찾아갑니다.
                            
                    ‘사랑하지 않은 것처럼’을 비롯한 미니앨범의 신곡들은 물론.
                    남자를 몰라,겁쟁이, 나에게로 떠나는 여행등
                    노래방 인기순위 랭킹의 곡들을 버즈와 함께 떼창 하는 시간!
                    넘치는 흥을 주체할 수 없도록 신나게 달릴 메들리 곡들까지 만나볼 수 있는 다채로운 콘서트!
                            
                    2017년, 여러분의 연말을 가장 특별하게 마무리 할 단 하나의 공연 JUST ONE
                    Band 버즈의 빛나는 모습으로 가득할 공연장으로 여러분을 초대합니다.""",
                null,
                "12월 23일(토) PM7 , 24일(일) PM5",
                "경북대학교 대강당",
                "대구광역시 북구 산격동 1370-1",
                "대구광역시 북구 대학로 80 경북대학교",
                null,
                "http://www.sjticket.com",
                null,
                LocalDateTime.parse("2024-10-23T23:36:05.000"),
                LocalDateTime.parse("2024-10-23T23:36:05.000"),
                131
            )
        );

        when(reservationService.getAllDisplayInfoCount(categoryId)).thenReturn(totalCount);
        when(reservationService.getDisplayInfoWithStartNumber(categoryId, start)).thenReturn(displayInfoItemList);

        DisplayInfoResponseDTO responseDTO = mainController.displayInfos(categoryId, start);

        assertEquals(totalCount, responseDTO.getTotalCount());
        assertEquals(displayInfoItemList.size(), responseDTO.getProductCount());
        assertEquals(displayInfoItemList, responseDTO.getDisplayInfoItemList());
    }

}