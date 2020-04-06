package co.edu.icesi.ci.thymeval.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.jni.BIOCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.model.User1;
import co.edu.icesi.ci.thymeval.model.User2;
import co.edu.icesi.ci.thymeval.service.UserService;
import lombok.var;

@Controller
public class UserController {

	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
		;
	}
	
	
	@GetMapping("/")
	public String home() {
		return "index"
;	}
	
	@GetMapping("/login")
	public String login() {
//		model.addAttribute("user","");
		return "/login";
	}
	
//	@PostMapping("/login")
//	public String loginPost(@RequestParam(value = "action", required = true) String action, @Validated(User1.class) UserApp user,
//			BindingResult bindingResult, Model model) {
//		if(bindingResult.hasErrors()) {
//			model
//		}else {
//			
//			return "/index";
//		}
//	}

	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	
	
	@GetMapping("/users/add1")
	public String addUser1(Model model) {
		model.addAttribute("user", new UserApp());
		return "users/add-user1";
	}
	
	@PostMapping("users/add1")
	public String saveUser1(@RequestParam(value = "action", required = true) String action, @Validated(User1.class) UserApp user,
			BindingResult bindingResult, Model model)
	{
		if(!action.equals("Cancel"))
		{
			if(bindingResult.hasErrors())
			{
				return "users/add-user1";
			}else
			{
				System.out.println(user);
				userService.save(user);
				System.out.println(user);
				model.addAttribute("user",user);
				model.addAttribute("genders", userService.getGenders());
				model.addAttribute("types", userService.getTypes());
				return "users/add-user2";	
			}
		}else {
			return "users/index";
		}
		
			
	}

	

	
	//EN ESTA SE NECESITA EL ID, CAMPO NO TEXTO, QUE NO SE CAMBIE Y QUE NO SEA VISIBLE
/**
		@GetMapping("/users/add2/{id}")
		public String addUser2(@PathVariable("id") long id, Model model) {
			Optional<User> user = userService.findById(id);
			if (user == null)
				throw new IllegalArgumentException("Invalid user Id:" + id);
			model.addAttribute(user);
			model.addAttribute("genders", userService.getGenders());
			model.addAttribute("types", userService.getTypes());
			return "users/add-user2";
		}**/
	//EN ESTA SE NECESITA EL ID

	@PostMapping("/users/add2/")
	public String saveUser(@RequestParam(value = "action", required = true) String action, @Validated(User2.class) UserApp user,
			BindingResult bindingResult, Model model) {
		System.out.println(user);
		if (user == null)
			throw new IllegalArgumentException("Invalid User");
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("genders", userService.getGenders());
				model.addAttribute("types", userService.getTypes());
				return "/users/add-user2";
			} else {
				var userSaved=userService.findById(user.getId()).get();
				user.setUsername(userSaved.getUsername());
				user.setPassword(userSaved.getPassword());
				userService.save(user);
			}
		}
		return "redirect:/users/";
	}

	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<UserApp> user = userService.findById(id);
		if (user == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("user", user.get());
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		return "users/update-user";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@Validated @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, UserApp user, BindingResult bindingResult) {
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "/users/edit/";
			} else {
				userService.save(user);

			}
		}
		return "redirect:/users/";
	}

	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		UserApp user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.delete(user);
		return "redirect:/users/";
	}
}
