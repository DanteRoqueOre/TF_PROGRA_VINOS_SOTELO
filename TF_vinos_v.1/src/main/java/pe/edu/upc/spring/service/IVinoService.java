package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Vino;

public interface IVinoService {
	public boolean insertar(Vino vino);
	public boolean modificar(Vino vino);
	public void eliminar(int idVino);
	public Optional<Vino> listarId(int idVino);
	List<Vino> listar();
	List<Vino> buscarNombre(String nameVino);
}