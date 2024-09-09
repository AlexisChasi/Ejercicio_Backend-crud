package com.example.crud.pruebaTec.service;

import com.example.crud.pruebaTec.exeption.ResourceConflictException;
import com.example.crud.pruebaTec.exeption.ResourceNotFoundException;
import com.example.crud.pruebaTec.model.Cliente;
import com.example.crud.pruebaTec.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private HashMap<String, Object> dataCliente;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    //CREAR Y ACTUALIZAR
    public ResponseEntity<Object> newCliente(Cliente cliente) {
        Optional<Cliente> res = clienteRepository.findByClienteid(cliente.getClienteid());
        dataCliente = new HashMap<>();

        if (res.isPresent() && cliente.getId() == null) {
            throw new ResourceConflictException("Ya existe un cliente con ese ID");
        }

        boolean isUpdate = cliente.getId() != null && clienteRepository.existsById(cliente.getId());

        // Guardar el cliente en el repositorio
        clienteRepository.save(cliente);

        // Preparar los datos de respuesta
        dataCliente.put("Datos", cliente);

        // Determinar si estamos actualizando o creando un nuevo cliente
        if (isUpdate) {
            dataCliente.put("message", "Cliente actualizado con éxito");
        } else {
            dataCliente.put("message", "Cliente agregado con éxito");
        }

        return ResponseEntity.ok(dataCliente);
    }

    public ResponseEntity<Object> deleteCliente(Long id) {
        dataCliente = new HashMap<>();
        boolean exist = clienteRepository.existsById(id);
        if (!exist) {
            throw new ResourceNotFoundException("No existe un cliente con ese ID");
        }
        clienteRepository.deleteById(id);
        dataCliente.put("message", "Cliente eliminado");
        return ResponseEntity.ok(dataCliente);
    }
}
