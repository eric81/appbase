package com.eudemon.taurus.app.action;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eudemon.taurus.app.entity.User;
import com.eudemon.taurus.app.service.UserService;
import com.eudemon.taurus.app.util.RequestUtils;

/**
 * 用户管理Action
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/user")
public class UserAction {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public String query(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String name = request.getParameter("name");
		
		User user = null;
		try {
			user = this.userService.getUserByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("user", user);
		model.addAttribute("userinfo", user == null ? "" : user.toString());
		return "index";
	}
	
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		int id = RequestUtils.getParameterAsInt(request, "id", 0);
		
		boolean rs = this.userService.delete(id);
		if(rs){
			return "success"; 
		}else{
			return "fail";
		}
	}
	
	@RequestMapping(value = "uploadPhoto")
	public String uploadPhoto(HttpServletRequest request, @RequestParam("userfile") MultipartFile file, ModelMap model)
			throws Exception {
		int id = RequestUtils.getParameterAsInt(request, "id", 0);
		String name = request.getParameter("name");
		
		userService.updatePhoto(id, file);
		
		return "forward:/user/query?name=" + name;
	}
	
	@RequestMapping(value = "viewPhoto")
	public void viewPhoto(HttpServletRequest request, OutputStream responseBodyOut)
			throws Exception {
		int id = RequestUtils.getParameterAsInt(request, "id", 0);
		
		try {
			byte[] photo = userService.getPhoto(id);
			if(photo == null){
				photo = new byte[]{};
			}
			responseBodyOut.write(photo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
