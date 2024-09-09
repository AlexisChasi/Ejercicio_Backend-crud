package com.example.crud.pruebaTec.service;

import com.example.crud.pruebaTec.exeption.ResourceConflictException;
import com.example.crud.pruebaTec.exeption.ResourceNotFoundException;
import com.example.crud.pruebaTec.model.Cuenta;
import com.example.crud.pruebaTec.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private HashMap<String, Object> dataCuenta;

    @Autowired
    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public List<Cuenta> getCuentas() {
        return this.cuentaRepository.findAll();
    }

    public ResponseEntity<Object> newCuenta(Cuenta cuenta) {
        Optional<Cuenta> res = cuentaRepository.findCuentaByNumeroCuenta(cuenta.getNumeroCuenta());
        dataCuenta = new HashMap<>();

        if (res.isPresent() && cuenta.getId() == null) {
            throw new ResourceConflictException("Ya existe una cuenta con ese número de cuenta");
        }

        boolean isUpdate = cuenta.getId() != null && cuentaRepository.existsById(cuenta.getId());

        cuentaRepository.save(cuenta);

        dataCuenta.put("Datos", cuenta);

        if (isUpdate) {
            dataCuenta.put("message", "Cuenta actualizada con éxito");
        } else {
            dataCuenta.put("message", "Cuenta agregada con éxito");
        }

        return ResponseEntity.ok(dataCuenta);
    }

    public ResponseEntity<Object> deleteCuenta(Long id) {
        dataCuenta = new HashMap<>();
        boolean exist = this.cuentaRepository.existsById(id);
        if (!exist) {
            throw new ResourceNotFoundException("No existe una cuenta con ese id");
        }
        cuentaRepository.deleteById(id);
        dataCuenta.put("message", "Cuenta eliminada");
        return ResponseEntity.ok(dataCuenta);
    }
}
