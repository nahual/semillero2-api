package ar.edu.undav.semillero.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Node;
import ar.edu.undav.semillero.service.GraduatedService;
import ar.edu.undav.semillero.service.NodeService;
import ar.edu.undav.semillero.view.View;

@RestController
@RequestMapping("/graduated")
public class GraduatedController {

	@Autowired
	private GraduatedService graduatedService;
	@Autowired
	private NodeService nodeService;

	// Guardar un graduado
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Graduated saveGraduated(@RequestParam(value = "name") String name,
			@RequestParam(value = "node") long nodeId) {
		Node node = nodeService.findById(nodeId);
		Graduated graduated = new Graduated(name, node, new Date());
		graduatedService.save(graduated);
		return graduated;
	}

	// Obtener graduados por ID
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Graduated> getGraduated(@PathVariable Long id) {
		Graduated graduated = graduatedService.findById(id);
		if(graduated == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}else{
			return ResponseEntity.ok(graduated);
		}
	}





	// Eliminar un graduado
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Graduated deleteGraduated(@PathVariable Long id) {
		Graduated graduated = graduatedService.findById(id);
		graduated.setDeleted(true);
		graduatedService.save(graduated);
		return graduated;
	}

	// Obtener todos los graduados
	@JsonView(View.Summary.class)
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Graduated> getGraduated(@RequestParam(value = "año", defaultValue = "-1") int año,
			@RequestParam(value = "mes", defaultValue = "-1") int mes,
			@RequestParam(value = "dia", defaultValue = "-1") int dia,
			@RequestParam(value = "node", defaultValue = "-1") long nodeId) throws ParseException {
		if (nodeId != -1) {
			Node node = nodeService.findById(nodeId);
			Collection<Graduated> graduateds = graduatedService.findByNode(node);
			return graduateds;
		} else if (dia != -1) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			Date fecha = df.parse(año + "/" + mes + "/" + dia + "/");
			Collection<Graduated> graduateds = graduatedService.findByDate(fecha);
			return graduateds;
		} else {
			Collection<Graduated> graduateds = graduatedService.findAll();
			return graduateds;
		}
	}

}
