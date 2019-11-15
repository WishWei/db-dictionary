package com.wish.dbdictionary.dto;

import lombok.Data;

@Data
public class ColumnInfoDTO {
	private String columnName;
	private String type;
	private long length;
	private String nullable;
	private String comments;
}
