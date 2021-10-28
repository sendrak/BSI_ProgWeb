
package br.com.cadastroEmbarcacoes.controller;

import br.com.cadastroEmbarcacoes.model.Administrador;
import br.com.cadastroEmbarcacoes.service.AdministradorService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apirest/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService service;
    
    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size){
            
        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id")Long id){
        return ResponseEntity.ok(service.findById(id));  
    }
    
    @PostMapping()
    public ResponseEntity save(@Valid @RequestBody Administrador administrador){
        administrador.setIdUsuario(Long.MIN_VALUE);
        return ResponseEntity.status(HttpStatus.CREATED).body(administrador);
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Administrador administrador){
        administrador.setIdUsuario(id);
        service.update(administrador, "", "", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
