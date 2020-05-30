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

import pe.edu.upc.spring.model.Vino;
import pe.edu.upc.spring.service.IVinoService;

@Controller
@RequestMapping("/vino")
public class VinoController {
	
	@Autowired
	private IVinoService vService;
	
	@RequestMapping("/bienvenido")
	public String irVinoBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irVino(Map<String, Object>model) {
		model.put("listaVinos",vService.listar());
		return "listVino";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("vino", new Vino());
		return "Vino";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Vino objVino, BindingResult binRes,Model model)
	      throws ParseException
	{
		if(binRes.hasErrors()) {
			return "vino";
		}
		else {
			boolean flag=vService.insertar(objVino);
			if(flag) {
				return "redirect:/vino/listar";
			}
			else {
				model.addAttribute("mensaje","Ocurrio un roche");
				return "redirect:/vino/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Vino objVino, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/vino/listar";
		}
		else {
			boolean flag = vService.modificar(objVino);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/vino/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/vino/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Vino> objVino = vService.listarId(id);
		if (objVino == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/vino/listar";
		}
		else {
			model.addAttribute("vino", objVino);
			return "vino";
		}
	}
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				vService.eliminar(id);
				model.put("listaVinos", vService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaVinos", vService.listar());
		}
		return "listVino";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaVinos", vService.listar());
		return "listVino";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Vino vino) 
	throws ParseException
	{
		vService.listarId(vino.getIdVino());
		return "listVino";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Vino vino) 
			throws ParseException
	{
		List<Vino> listaVinos;
		vino.setNameVino(vino.getNameVino());
		listaVinos= vService.buscarNombre(vino.getNameVino());
		
		if (listaVinos.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaVinos", listaVinos);
		return "buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("vino", new Vino());
		return "buscar";
	}
}