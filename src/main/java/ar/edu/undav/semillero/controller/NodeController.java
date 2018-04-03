package ar.edu.undav.semillero.controller;

import ar.edu.undav.semillero.domain.entity.Node;
import ar.edu.undav.semillero.service.NodeService;
import ar.edu.undav.semillero.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/node")
@CrossOrigin
public class NodeController {

    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    // Guardar un nodo
    @PostMapping("")
    public Node saveNode(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address) {
        Node node = new Node(name, address);
        nodeService.save(node);
        return node;
    }

    // Obtener nodos por ID
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Node> getNode(@PathVariable Long id) {
        return nodeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Obtener todos los nodos
    @JsonView(View.Summary.class)
    @GetMapping("")
    public ResponseEntity<Collection<Node>> getNode() {
        Collection<Node> nodes = nodeService.findAll();
            return ResponseEntity.ok(nodes);
        }
    }
