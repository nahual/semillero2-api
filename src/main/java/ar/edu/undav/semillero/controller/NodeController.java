package ar.edu.undav.semillero.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

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

import ar.edu.undav.semillero.domain.entity.Node;
import ar.edu.undav.semillero.service.NodeService;
import ar.edu.undav.semillero.view.View;

@RestController
@RequestMapping("/node")
public class NodeController {

	@Autowired
	private NodeService nodeService;

	// Guardar un nodo
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Node saveNode(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address) {
		Node node = new Node(name, address);
		nodeService.save(node);
		return node;
	}

	// Obtener nodos por ID
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Node> getNode(@PathVariable Long id) {
		Node node = nodeService.findById(id);
		if(node == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}else{
			return ResponseEntity.ok(node);
		}

	}

	// Obtener todos los nodos
	@JsonView(View.Summary.class)
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Collection<Node>> getNode(@RequestParam(value = "date", defaultValue = "null") String date,
			@RequestParam(value = "node", defaultValue = "null") String node) {
		Collection<Node> nodes = nodeService.findAll();
		if(node == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}else{
			return ResponseEntity.ok(nodes);
		}
	}

}