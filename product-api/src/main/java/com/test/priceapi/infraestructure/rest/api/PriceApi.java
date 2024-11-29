package com.test.priceapi.infraestructure.rest.api;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.priceapi.application.dto.PriceDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;


@Tag(name = "Prices", description = "API para obtener la lista de precios")
@RequestMapping("/api/v1")
public interface PriceApi {

    @Operation(summary = "Recupera la lista de precios", description = "Realiza una búsqueda de precios con los filtros proporcionados por el usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de precios disponible",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceDto.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")})
    @GetMapping(value = "/activePrice", produces = {"application/json"})
    ResponseEntity<PriceDto> getActivePrice(
            @Parameter(in = ParameterIn.QUERY, description = "Fecha de inicio de la búsqueda de precios (formato: yyyy-MM-dd' 'HH:mm:ss)", schema = @Schema(type = "string", format = "date-time"))
            @RequestParam(value = "priceStartDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss") LocalDateTime priceStartDate,

            @Parameter(in = ParameterIn.QUERY, description = "ID del producto para filtrar los precios", schema = @Schema(type = "integer"))
            @RequestParam(value = "productId", required = false) Integer productId,

            @Parameter(in = ParameterIn.QUERY, description = "ID de la marca para filtrar los precios", schema = @Schema(type = "integer"))
            @RequestParam(value = "brandId", required = false) Integer brandId);
}
