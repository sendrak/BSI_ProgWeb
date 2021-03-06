package br.com.cadastroEmbarcacoes.controller.apirest;

import br.com.cadastroEmbarcacoes.model.Embarcacao;
import br.com.cadastroEmbarcacoes.service.EmbarcacaoService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apirest/embarcacoes")
public class EmbarcacaoController {
    @Autowired
    private EmbarcacaoService service;
    
    @GetMapping
    public ResponseEntity getAll(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page, 
        @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id")Long id){
        return ResponseEntity.ok(service.findById(id));  
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Embarcacao embarcacao){
        embarcacao.setId(null);
        service.save(embarcacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(embarcacao);
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Embarcacao embarcacao){
        embarcacao.setId(id);
        service.update(embarcacao);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
}