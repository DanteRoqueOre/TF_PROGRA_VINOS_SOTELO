<<<<<<< HEAD
package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Vino;
import pe.edu.upc.spring.repository.IVinoRepository;
import pe.edu.upc.spring.service.IVinoService;

@Service
public class VinoServiceImpl implements IVinoService{
	@Autowired
	private IVinoRepository dVino;
	
	@Override
	@Transactional
	public boolean insertar(Vino vino) {
		Vino objVino=dVino.save(vino);
		if(objVino==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Vino vino) {
		boolean flag=false;
		try {
			dVino.save(vino);
			flag=true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un error..");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idVino) {
		dVino.deleteById(idVino);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Vino>listarId(int idVino){
		return dVino.findById(idVino);
	}

	@Override
	@Transactional
	public List<Vino>listar(){
		return dVino.findAll();
	}

	@Override
	@Transactional
	public List<Vino>buscarNombre(String nameVino){
		return dVino.buscarNombre(nameVino);
	}
}
=======
package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Vino;
import pe.edu.upc.spring.repository.IVinoRepository;
import pe.edu.upc.spring.service.IVinoService;

@Service
public class VinoServiceImpl implements IVinoService{
	@Autowired
	private IVinoRepository dVino;
	
	@Override
	@Transactional
	public boolean insertar(Vino vino) {
		Vino objVino=dVino.save(vino);
		if(objVino==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Vino vino) {
		boolean flag=false;
		try {
			dVino.save(vino);
			flag=true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un error..");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idVino) {
		dVino.deleteById(idVino);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Vino>listarId(int idVino){
		return dVino.findById(idVino);
	}

	@Override
	@Transactional
	public List<Vino>listar(){
		return dVino.findAll();
	}

	@Override
	@Transactional
	public List<Vino>buscarNombre(String nameVino){
		return dVino.buscarNombre(nameVino);
	}
}
>>>>>>> branch 'master' of https://github.com/Thales-cod/TF_PROGRA_VINOS_SOTELO.git
