package com.example.mspl_connect.DispatchController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mspl_connect.DispatchEntity.Dispatch;
import com.example.mspl_connect.DispatchEntity.DispatchEvent;
import com.example.mspl_connect.DispatchRepo.DispatchEventRepository;
import com.example.mspl_connect.DispatchRepo.DispatchRepository;
import com.example.mspl_connect.DispatchService.CustomerService;
import com.example.mspl_connect.DispatchService.DispatchEventService;
import com.example.mspl_connect.DispatchService.ProductService;
import com.example.mspl_connect.Sales_Service.DeliveryChallanService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DispatchDashboardController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DispatchEventRepository eventRepository;
	
	@Autowired
	private DispatchRepository dispatchRepository;
	
	@Autowired
	private DeliveryChallanService dispatchService;
	
	 private final DispatchEventService eventService;

	    public DispatchDashboardController(DispatchEventService eventService) {
	        this.eventService = eventService;
	    }
	
	    @GetMapping("/dashboard")
	    public String showDashboard(Model model, HttpSession session) {
	    	if (session.getAttribute("email") == null) {
		        return "redirect:/logout";
		    }
	        
	    	String username = (String) session.getAttribute("username"); 
	        model.addAttribute("username", username); 

	        long totalCustomers = customerService.getTotalCustomers();
	        long totalProducts = productService.getTotalProducts();
	        long totalEvents = eventService.getTotalEvents();
	        long todayEvents = eventService.getTodayEventsCount();
	        long totalModules = dispatchService.getTotalDispatches();
	        long totalModulesReturned = dispatchService.getTotalReturned();

	        List<DispatchEvent> events = eventRepository.findAll();
	        model.addAttribute("events", events);

	        List<DispatchEvent> todaysEvents = eventService.getTodaysEvents(); 
	        model.addAttribute("events", todaysEvents);

	        List<Dispatch> recentDispatches = dispatchRepository.findTop5ByOrderByIdDesc();
	        model.addAttribute("recentDispatches", recentDispatches);

	        List<Dispatch> dispatchList = dispatchRepository.findAll();
	        Map<String, Integer> quantityByDate = new TreeMap<>();

	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
	        LocalDate today = LocalDate.now();
	        LocalDate sevenDaysAgo = today.minusDays(6);

	        for (Dispatch d : dispatchList) {
	            try {
	                LocalDate dispatchDate = LocalDate.parse(d.getDate(), inputFormatter);

	                if (!dispatchDate.isBefore(sevenDaysAgo) && !dispatchDate.isAfter(today)) {
	                    int quantity = 0;
	                    try {
	                        quantity = Integer.parseInt(d.getQuantity());
	                    } catch (NumberFormatException e) {
	                        quantity = 0;
	                    }

	                    String formattedDate = dispatchDate.format(outputFormatter);
	                    quantityByDate.put(formattedDate, quantityByDate.getOrDefault(formattedDate, 0) + quantity);
	                }
	            } catch (DateTimeParseException e) {

	                e.printStackTrace();
	            }
	        }

	        model.addAttribute("totalCustomers", totalCustomers);
	        model.addAttribute("totalProducts", totalProducts);
	        model.addAttribute("totalEvents", totalEvents);
	        model.addAttribute("todayEvents", todayEvents);
	        model.addAttribute("totalModules", totalModules);
	        model.addAttribute("totalModulesReturned", totalModulesReturned);
	        
	        model.addAttribute("chartLabels", new ArrayList<>(quantityByDate.keySet()));
	        model.addAttribute("chartData", new ArrayList<>(quantityByDate.values()));

	        return "dispatch/dashboard";
	    }

}
