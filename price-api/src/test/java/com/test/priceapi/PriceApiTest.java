package com.test.priceapi;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.priceapi.application.port.PriceService;
import com.test.priceapi.domain.model.Price;
import com.test.priceapi.domain.port.out.PriceRepository;
import com.test.priceapi.domain.service.PriceDomainService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.priceapi.application.dto.PriceDto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
class PriceApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(Constants.TEST_1)
    void test1() throws Exception{
        assertPriceAt("2020-06-14 10:00:00", 35.50F);
    }

    @Test
    @DisplayName(Constants.TEST_2)
    void test2() throws Exception{
        assertPriceAt("2020-06-14 16:00:00", 25.45F);
    }

    @Test
    @DisplayName(Constants.TEST_3)
    void test3() throws Exception{
        assertPriceAt("2020-06-14 21:00:00", 35.50F);
    }

    @Test
    @DisplayName(Constants.TEST_4)
    void test4() throws Exception{
        assertPriceAt("2020-06-15 10:00:00", 30.50F);
    }

    @Test
    @DisplayName(Constants.TEST_5)
    void test5() throws Exception {
        assertPriceAt("2020-06-15 21:00:00", 38.95F);
    }

    @Test
    @DisplayName(Constants.TEST_6)
    void test6() {

        List<Price> prices = List.of(
                new Price.Builder().activePrice(500F).priority(4).build(),
                new Price.Builder().activePrice(400F).priority(3).build(),
                new Price.Builder().activePrice(300F).priority(10).build(), //The highest priority should be the one
                new Price.Builder().activePrice(200F).priority(2).build(),
                new Price.Builder().activePrice(100F).priority(1).build()
        );

        PriceDomainService priceDomainService = new PriceDomainService(null);

        Price highestPriorityPrice = priceDomainService.getTheActivePrice(prices);

        assertNotNull(highestPriorityPrice);
        assertEquals(10, highestPriorityPrice.getPriority());
        assertEquals(300F, highestPriorityPrice.getActivePrice());

    }

    private void assertPriceAt(String dateString, float expectedPrice) throws Exception {

        // Realiza la solicitud con MockMvc
        String responseString = mockMvc.perform(get("/api/v1/activePrice")
                .param("priceStartDate", dateString)
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Usa ObjectMapper para deserializar el JSON en un PriceDto
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss"));

        PriceDto priceDto = objectMapper.readValue(responseString, PriceDto.class);

        // Verifica que el precio sea el esperado
        assertEquals(expectedPrice, priceDto.getPrice(), 0.00);
    }

}
