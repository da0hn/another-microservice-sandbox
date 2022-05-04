package br.com.gabriel.product.application.rest.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorDetailResponse(
  int status,
  String message,
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  LocalDateTime instant
) {
}
