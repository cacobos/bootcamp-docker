package com.bootcamp.visit.controller;

import com.bootcamp.visit.service.VisitService;
import model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VisitController {

    @Autowired
    VisitService visitService;

    @GetMapping("visits")
    public ResponseEntity<List<Visit>> listAll(){
        return ResponseEntity.ok(visitService.findAll());
    }

    @GetMapping("visits/unbilled")
    public ResponseEntity<List<Visit>> listAllUnbilled(){
        return ResponseEntity.ok(visitService.listAllUnbilled());
    }

    @GetMapping("visits/unbilled/{id}")
    public ResponseEntity<List<Visit>> listUnbilled(@PathVariable("id")String customerId){
        return ResponseEntity.ok(visitService.listUnbilled(customerId));
    }

    @PostMapping("/visits")
    public ResponseEntity<Visit> insert(@RequestBody @Valid Visit visit){
        if(visit.getId()==null){
            return ResponseEntity.ok(visitService.insert(visit));
        }
        return ResponseEntity.badRequest().build();

    }

    @PutMapping("visits/{id}")
    public ResponseEntity<Visit> update(@RequestBody @Valid Visit visit,
                                        @PathVariable("id") Long id){
        if(visitService.findById(id)!=null){
            visit.setId(id);
            return ResponseEntity.ok(visitService.update(visit));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("visits/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        if(visitService.findById(id)!=null) {
            return ResponseEntity.ok("Factura borrada con éxito");
        }
        return ResponseEntity.ok("No existe factura con ese id");
    }

}
