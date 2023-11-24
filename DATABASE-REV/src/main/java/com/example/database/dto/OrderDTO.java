package com.example.database.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {
  private List<Long> products;
}
