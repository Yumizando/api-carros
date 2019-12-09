package com.carros.domain;

import com.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> getCarros(){
        return carroRepository.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());
    }

    public Optional<CarroDTO> getCarroById(Long id) {
        return carroRepository.findById(id).map(CarroDTO::new);
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return carroRepository.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());
    }

    public Optional<CarroDTO> save(Carro carro) {
        return Optional.of(carroRepository.save(carro)).map(CarroDTO::new);
    }

    public CarroDTO update(Long id, Carro carro) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Carro> optional = carroRepository.findById(id);
        if(optional.isPresent()) {
            Carro db = optional.get();
            // Copiar as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            // Atualiza o carro
            carroRepository.save(db);

            return new CarroDTO(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        if (getCarroById(id).isPresent()){
            carroRepository.deleteById(id);
        }
    }
}
