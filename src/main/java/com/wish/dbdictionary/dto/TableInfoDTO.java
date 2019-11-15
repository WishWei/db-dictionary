package com.wish.dbdictionary.dto;

import lombok.Data;

import java.util.List;

@Data
public class TableInfoDTO {
	private String tname;
	private String comments;
	private List<ColumnInfoDTO> columns;
}
