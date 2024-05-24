package com.neo.hello.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neo.hello.model.User;
import com.neo.hello.param.UserParam;
import com.neo.hello.repository.UserRepository;

@Controller
public class HelloController {

	@Autowired
	private UserRepository ur;
	
    @RequestMapping("/hello")
    public String hello(String name) {
    	User u = new User();
    	
        return "hello world,"+name;
    }
    @RequestMapping("/add")
    public String add(@Valid UserParam userParam,BindingResult result,Model model) {
    	String msg="";
    	//1 判断数据校验是否合法
    	if(result.hasErrors()) {
    		List<ObjectError> list = result.getAllErrors();
    		for(ObjectError error:list) {
    			msg = msg+error.getCode()+"-"+error.getDefaultMessage()+";";
    		}
    		model.addAttribute("errorMsg", msg);
    		return "adduser";
    	}
    	//2 判断数据库是否已经存在对应用户
    	
    	User u = ur.findByUserName(userParam.getUserName());
    	if(u!=null) {
    		model.addAttribute("errorMsg", "用户名已存在");
    		return "adduser";   
    	}
    	//3 前两步没有问题的话，添加进数据库
    	User user = new User();
    	BeanUtils.copyProperties(userParam, user);
    	ur.save(user);
    	model.addAttribute("errorMsg", "添加成功");
    	return "adduser";
    }
    
    @RequestMapping("/list")
    public String list(Model model,
    		@RequestParam(value="page",defaultValue = "0") Integer page,
    		@RequestParam(value="size",defaultValue = "10") Integer size) {
    	Sort sort = new Sort(Sort.Direction.ASC,"id");
    	Pageable pageable = PageRequest.of(page, size, sort);
    	Page<User> p = ur.findList(pageable);
    	model.addAttribute("users", p);
    	return "list";
    }
    @RequestMapping("/toDelete")
    public String delete(Long id) {
    	ur.deleteById(id);
    	return "redirect:/list";
    }
    @RequestMapping("/toEdit")
    public String toEdit(Long id,Model model) {
    	Optional<User> u=ur.findById(id);
    	User user=u.get();
    	model.addAttribute("user",user);
    	return "edit";
    }
    @RequestMapping("/edit")
    public String edit(@Valid UserParam userParam,BindingResult result,Model model) {
    	String msg="";
    	//1 判断数据校验是否合法
    	if(result.hasErrors()) {
    		List<ObjectError> list = result.getAllErrors();
    		for(ObjectError error:list) {
    			msg = msg+error.getCode()+"-"+error.getDefaultMessage()+";";
    		}
    		model.addAttribute("errorMsg", msg);
    		model.addAttribute("user",userParam);
    		return "edit";
    	}
    	
    	User user = new User();
    	BeanUtils.copyProperties(userParam, user);
    	ur.save(user);
    	return "redirect:/list";
    }
}