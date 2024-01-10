package com.hanwha.robotics.user.common.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageResponse {
	List<?> contents = new ArrayList<>();
	PageRequest pageRequest = new PageRequest();
	int totalElements = 0;
	int totalPages = 0;
	int page = 1;
	int size = 0;

	public PageResponse(List<?> contents, int totalElements, int page, int size) {
		this.size = size;
		this.page = page;
		this.totalElements = totalElements;
		this.contents = contents;
		this.totalPages = (int)Math.ceil((double)totalElements / (double)this.size);
	}

	public PageResponse(List<?> contents, int totalElements, PageRequest pageRequest) {
		this.size = pageRequest.getSize();
		this.page = pageRequest.getPage();
		this.pageRequest = pageRequest;
		this.totalElements = totalElements;
		this.contents = contents;
		this.totalPages = (int)Math.ceil((double)totalElements / (double)this.size);
	}
}
