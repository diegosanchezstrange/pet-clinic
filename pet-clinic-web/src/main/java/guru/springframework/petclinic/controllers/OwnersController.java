package guru.springframework.petclinic.controllers;

import guru.springframework.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnersController
{
	private final OwnerService	ownerService;

	public OwnersController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String	index(Model model)
	{
		model.addAttribute("owners", ownerService.findAll());
		return ("owners/index");
	}
}
