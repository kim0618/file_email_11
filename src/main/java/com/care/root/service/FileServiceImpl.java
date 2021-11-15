package com.care.root.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.dto.ShoesDTO;
import com.care.root.mybatis.FileMapper;


@Service
public class FileServiceImpl implements FileService{

	@Autowired FileMapper fm;
	public void fileProcess(MultipartHttpServletRequest mul) {
		ShoesDTO dto = new ShoesDTO();
		dto.setId(mul.getParameter("id"));
		dto.setName(mul.getParameter("name"));
		
		MultipartFile file = mul.getFile("file");
		if(file.getSize() != 0) { // !file.isEmpth() == 비어있지않으면 실행
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar calendar = Calendar.getInstance();
			String sysFileName = format.format(calendar.getTime());
			sysFileName += file.getOriginalFilename();
			
			dto.setImgName(sysFileName);
			
		java.io.File saveFile = new java.io.File(IMAGE_REPO+"/"+sysFileName);
		
		try {
			file.transferTo(saveFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else {
		dto.setImgName("nan");
	}
		System.out.println(dto.getId());
		System.out.println(dto.getName());
		System.out.println(dto.getImgName());
		fm.saveData(dto);
	}
	

	public void getShoesImage(Model model) {
		model.addAttribute("list",fm.getShoesImage());
	}
}




















