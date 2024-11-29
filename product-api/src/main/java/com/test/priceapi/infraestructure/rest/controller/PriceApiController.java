package com.test.priceapi.infraestructure.rest.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.test.priceapi.application.ports.PriceService;
import com.test.priceapi.application.dto.PriceDto;

import com.test.priceapi.infraestructure.rest.api.PriceApi;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@Log4j2
public class PriceApiController implements PriceApi {

    private final PriceService priceService;

    @Override
    public ResponseEntity<PriceDto> getActivePrice(LocalDateTime priceStartDate, Integer productId, Integer brandId) {
        try {

            var priceDto = priceService.findActivePrice(priceStartDate, productId, brandId);

            if (priceDto == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(priceDto);

        } catch (Exception e) {

            log.error("Error al obtener el precio", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
