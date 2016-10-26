package ar.edu.undav.semillero.controller;

import ar.edu.undav.semillero.domain.entity.Graduated;
import ar.edu.undav.semillero.service.GraduatedService;
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
@RequestMapping("/graduated")
public class GraduatedController {

    @Autowired 
    private GraduatedService graduatedService;

    //Guardar un graduado
	@RequestMapping(value="",method=RequestMethod.POST)
    public String saveGraduated(@RequestParam(value="name") String name) {
	    	Graduated graduated = new Graduated(name);
	        graduatedService.save(graduated);
			return "Se agrego el graduado " + name + "con el ID:" + graduated.getId();
	}

	//Obtener graduados. Si no se pasan parametros, se obtienen todos. Si se especifica parametro, se busca segun ese criterio (id, fecha, nodo)
    @RequestMapping(value="", method = RequestMethod.GET)
    public Collection<Graduated> getGraduated(@RequestParam(value="id",defaultValue="-1") Long id,@RequestParam(value="date",defaultValue="null") String date,@RequestParam(value="node",defaultValue="null") String node) {
        if(id!=-1){
        	Collection<Graduated> graduated = graduatedService.findById(id);
            return graduated;
        }
        else{
        	Collection<Graduated> graduateds = graduatedService.findAll();
            return graduateds;
        }

    }
}