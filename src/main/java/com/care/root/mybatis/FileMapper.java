package com.care.root.mybatis;

import com.care.root.dto.ShoesDTO;


public interface FileMapper {
	public void saveData(ShoesDTO dto);
	public java.util.List<ShoesDTO> getShoesImage();
}
