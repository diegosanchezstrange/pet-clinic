package guru.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnersController
{
	@RequestMapping({"", "/", "/index", "/index.html"})
	public String	index()
	{
		return ("owners/index");
	}
}
