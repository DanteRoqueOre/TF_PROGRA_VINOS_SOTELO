package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.service.IEventService;

@Controller
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private IEventService eService;
	
	@RequestMapping("/bienvenido")
	public String irEventBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irEvent(Map<String, Object>model) {
		model.put("listaEventos",eService.listar());
		return "listEvent";
	}
	
	@RequestMapping("/listar")
	public String irListar(Map<String, Object>model) {
		model.put("listaEventos",eService.listar());
		return "listEvent";
	}
	

}
