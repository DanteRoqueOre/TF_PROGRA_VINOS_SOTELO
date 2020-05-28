package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.service.IEventService;

import pe.edu.upc.spring.model.Event;


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
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("event", new Event());
		return "event";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Event objEvent, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			return "event";
		}
		else {
				boolean flag = eService.insertar(objEvent);
				if (flag) {
					return "redirect:/event/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/event/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Event objEvent, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/event/listar";
		}
		else {
			boolean flag = eService.modificar(objEvent);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/event/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/event/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Event> objEvent = eService.listarId(id);
		if (objEvent == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/event/listar";
		}
		else {
			model.addAttribute("event", objEvent);
			return "event";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				eService.eliminar(id);
				model.put("listaEventos", eService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaRazas", eService.listar());
		}
		return "listEvent";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEventos", eService.listar());
		return "listEvent";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Event event) 
	throws ParseException
	{
		eService.listarId(event.getIdEvent());
		return "listEvent";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Event event) 
			throws ParseException
	{
		List<Event> listaEventos;
		event.setNameEvent(event.getNameEvent());
		listaEventos = eService.buscarNombre(event.getNameEvent());
		
		if (listaEventos.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaEventos", listaEventos);
		return "buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("event", new Event());
		return "buscar";
	}
	

}
