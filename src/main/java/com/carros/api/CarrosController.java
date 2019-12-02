package com.carros.api;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping
    public Iterable<Carro> get(){
        return service.getCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> getCarroById(@PathVariable("id") Long id){
        return service.getCarroById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo){
        return service.getCarrosByTipo(tipo);
    }

}
