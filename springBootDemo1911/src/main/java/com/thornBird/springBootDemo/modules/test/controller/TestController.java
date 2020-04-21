package com.thornBird.springBootDemo.modules.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thornBird.springBootDemo.modules.test.entity.City;
import com.thornBird.springBootDemo.modules.test.entity.Country;
import com.thornBird.springBootDemo.modules.test.service.CountryService;
import com.thornBird.springBootDemo.modules.test.vo.ConfigBean;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private CountryService countryService;
	
	@Value("${server.port}")
	private int port;
	@Value("${com.hqyj.name}")
	private String name;
	@Value("${com.hqyj.age}")
	private int age;
	@Value("${com.hqyj.desc}")
	private String desc;
	@Value("${com.hqyj.random}")
	private String random;
	
	@Autowired
	private ConfigBean configBean;
	
	@RequestMapping("/download")
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
		try {
			Resource resource = new UrlResource(
					Paths.get(String.format("D:\\upload\\%s", fileName)).toUri());
			if (resource.exists() && resource.isReadable()) {
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
						.header(HttpHeaders.CONTENT_DISPOSITION, 
								String.format("attachment; filename=\"%s\"", resource.getFilename()))
						.body(resource);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
	 */
	@RequestMapping("/download1")
	public void downloadFile1(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);
		
		if (downloadFile.exists()) {
			response.setContentType("application/octet-stream");
			response.setContentLength((int)downloadFile.length());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
					String.format("attachment; filename=\"%s\"", fileName));
			
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(downloadFile);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e2) {
					LOGGER.debug(e2.getMessage());
					e2.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 以包装类 IOUtils 输出文件
	 */
	@RequestMapping("/download2")
	public void downloadFile2(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);
		
		try {
			if (downloadFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int)downloadFile.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
						String.format("attachment; filename=\"%s\"", fileName));
				
				InputStream is = new FileInputStream(downloadFile);
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@PostMapping(value="/uploadBatchFile", consumes="multipart/form-data")
	public String uploadBatchFile(@RequestParam MultipartFile[] files, 
			RedirectAttributes redirectAttributes) {
		
		boolean isEmpty = true;
		try {
			for (MultipartFile file : files) {
				if (file.isEmpty()) {
					continue;
				}
				
				String fileName = file.getOriginalFilename();
				File destFile = new File(String.format("D:\\upload\\%s", fileName));
				file.transferTo(destFile);
				
				isEmpty = false;
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "upload failed.");
			return "redirect:/test/index";
		}
		
		if (isEmpty) {
			redirectAttributes.addFlashAttribute("message", "Please select file.");
		} else {
			redirectAttributes.addFlashAttribute("message", "upload success.");
		}
		
		
		return "redirect:/test/index";
	}
	
	@PostMapping(value="/upload", consumes="multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select file.");
			return "redirect:/test/index";
		}
		
		String fileName = file.getOriginalFilename();
		
		File destFile = new File(String.format("D:\\upload\\%s", fileName));
		try {
			file.transferTo(destFile);
			redirectAttributes.addFlashAttribute("message", "upload success.");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
			redirectAttributes.addFlashAttribute("message", "upload failed.");
		}
		
		return "redirect:/test/index";
	}
	
	@RequestMapping("/index")
	public String testIndexPage(ModelMap modelMap) {
		int countryId = 522;
		List<City> cities = countryService.getCitiesByCountryId(countryId)
				.stream().limit(10).collect(Collectors.toList());
		Country country = countryService.getCountryById(countryId);
		
		modelMap.addAttribute("thymeleafTitle", "Spring thymeleaf test page");
		modelMap.addAttribute("checked", true);
		modelMap.addAttribute("currentNumber", 99);
		modelMap.addAttribute("changeType", "checkbox");
		modelMap.addAttribute("baiduUrl", "http://www.baidu.com");
		modelMap.addAttribute("shopLogo", 
				"http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
		modelMap.addAttribute("city", cities.get(0));
		modelMap.addAttribute("country", country);
		modelMap.addAttribute("updateCityUri", "/city");
		modelMap.addAttribute("cities", cities);
//		modelMap.addAttribute("template", "test/index");
		return "index";
	}
	
	@RequestMapping("/log")
	@ResponseBody
	public String logTest() {
		LOGGER.trace("This is trace log.");
		LOGGER.debug("This is debug log.");
		LOGGER.info("This is info log.1111111111111");
		LOGGER.warn("This is warn log.");
		LOGGER.error("This is error log.");
		return "This is log test.";
	}
	
	@RequestMapping("/config")
	@ResponseBody
	public String configTest() {
		StringBuffer sb = new StringBuffer();
		sb.append(port).append("--")
			.append(name).append("--")
			.append(age).append("--")
			.append(desc).append("--")
			.append(random).append("</br>");
		sb.append(configBean.getName()).append("--")
			.append(configBean.getAge()).append("--")
			.append(configBean.getDesc()).append("--")
			.append(configBean.getRandom()).append("</br>");
		return sb.toString();
	}
	

	@RequestMapping("/demoInfo")
	@ResponseBody
	public String demoDesc(HttpServletRequest request, @RequestParam String key) {
		String parameter = request.getParameter("key");
		return "This is a Spring Boot demo." + parameter + "--" + key;
	}
}
