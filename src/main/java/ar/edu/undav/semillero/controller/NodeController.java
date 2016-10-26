package ar.edu.undav.semillero.controller;

import ar.edu.undav.semillero.domain.entity.Company;
import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.domain.entity.Node;
import ar.edu.undav.semillero.service.GraduatedService;
import ar.edu.undav.semillero.service.NodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/******************************************************
 * Al runnear esto, si entro a localhost:8080/saveGraduated guardo un graduado en la DB semillero
 *****************************************************/

@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired 
    private NodeService nodeService;

    //Guardar un nodo
	@RequestMapping(value="",method=RequestMethod.POST)
    public String saveNode(@RequestParam(value="name") String name,@RequestParam(value="address") String address) {
	    	Node node= new Node(name,address);
	        nodeService.save(node);
			return "Se agrego el nodo " + name + " satisfactoriamente" ;
	}

	//Obtener nodos. Si no se pasan parametros, se obtienen todos. Si se especifica parametro, se busca segun ese criterio (id, fecha, nodo)
    @RequestMapping(value="", method = RequestMethod.GET)
    public Collection<Node> getNode(@RequestParam(value="id",defaultValue="-1") Long id,@RequestParam(value="name",defaultValue="null") String name) {
        if(id!=-1){
        	Collection<Node> node = nodeService.findById(id);
            return node;
        }
        else if(!name.equals("null")){
        	Collection<Node> node = nodeService.findByName(name);
            return node;        	
        }
        else{
        	Collection<Node> nodes = nodeService.findAll();
            return nodes;
        }

    }
}