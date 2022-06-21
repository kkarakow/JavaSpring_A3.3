package ca.sheridancollege.karakow.controllers;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ca.sheridancollege.karakow.beans.Department;
import ca.sheridancollege.karakow.beans.Employee;
import ca.sheridancollege.karakow.database.DatabaseAccess;

@Controller
public class HomeController {

	
	@Autowired
	DatabaseAccess da;
	
	@GetMapping("/")
	public String goHome(Model model) {		
		return "index";
	}
	
	@GetMapping("/secure")
	public String secureIndex(Model model) {
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		model.addAttribute("department", new Department());
		model.addAttribute("departmentList", da.getDepartments());
		
		return "/secure/index"; 
	}
	
	@GetMapping("/login") 
	public String login() {
		return "login";
	} 
	
	@GetMapping("/permission-denied") 
	public String permissionDenied() {
		return "/error/permission-denied"; 
	}
	
//	@GetMapping("/insert")
//	public String goInsert(Model model) {		
//		return "insert";
//	}
	
	@GetMapping("/secure/insert")
	public String insertSecure(Model model) {
	
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		model.addAttribute("departmentList", da.getDepartments());
		
		return "/secure/insert";
	}
	
//	@GetMapping("/update")
//	public String goUpdate(Model model) {
//		return "update";
//	}
	
	@GetMapping("/secure/update")
	public String updateSecure(Model model) {
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
	
		return "/secure/update";
	}
	
	@PostMapping("/updateEmployee")
	public String updateEmployee(Model model,
			@RequestParam Long EMPLOYEE_ID,
			@RequestParam String MANAGER_ID) {
		
		da.updateEmployee(EMPLOYEE_ID, Long.parseLong(MANAGER_ID));
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
	
		return "/secure/update";
	}
	
//	@GetMapping("/delete")
//	public String goDelete(Model model) {
//		return "delete";
//	}
	
	@GetMapping("/secure/delete")
	public String deleteSecure(Model model) {
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		
		return "/secure/delete";
	}
	
	@PostMapping("/deleteEmployee")
	public String deleteEmployee(Model model,
			@RequestParam Long EMPLOYEE_ID) {
		
		da.deleteEmployee(EMPLOYEE_ID);
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		
		return "/secure/delete";
	}
	
//	@GetMapping("/view")
//	public String viewEmployees(Model model) {
//		return "view";
//	}
	
	@GetMapping("/secure/view")
	public String viewSecure(Model model) {
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		
		return "/secure/view";
	}
	
	@PostMapping("/processForm")
	public String insertEmployee(Model model,
			@RequestParam Long EMPLOYEE_ID, 
			@RequestParam String FIRST_NAME, 
			@RequestParam String LAST_NAME, 
			@RequestParam String EMAIL, 
			@RequestParam String PHONE_NUMBER,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date HIRE_DATE, 
			@RequestParam String JOB_ID, 
			@RequestParam Double SALARY, 
			@RequestParam Double COMMISSION_PCT, 
			@RequestParam Long MANAGER_ID, 
			@RequestParam Long DEPARTMENT_ID,
			@ModelAttribute Employee employee) {
		
		
		da.insertEmployee(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, 
				EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, 
				COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID);
		
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", da.getEmployees());
		model.addAttribute("departmentList", da.getDepartments());
		
		return "/secure/insert";
	}
	
	
	
	
}
