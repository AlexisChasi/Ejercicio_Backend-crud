package com.example.crud.pruebaTec.service;

import com.example.crud.pruebaTec.exeption.ResourceNotFoundException;
import com.example.crud.pruebaTec.model.Cuenta;
import com.example.crud.pruebaTec.model.Movimiento;
import com.example.crud.pruebaTec.repository.CuentaRepository;
import com.example.crud.pruebaTec.repository.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientosService {

    private final MovimientosRepository movimientosRepository;
    private final CuentaRepository cuentaRepository;
    private HashMap<String, Object> dataMovimiento;

    @Autowired
    public MovimientosService(MovimientosRepository movimientosRepository, CuentaRepository cuentaRepository) {
        this.movimientosRepository = movimientosRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public List<Movimiento> getMovimientos() {
        return movimientosRepository.findAll();
    }

    public ResponseEntity<Object> newMovimiento(Movimiento movimiento) {
        dataMovimiento = new HashMap<>();

        // Verificar si existe la cuenta relacionada
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(movimiento.getId()); // Asumimos que el ID de cuenta está en el movimiento
        if (!cuentaOpt.isPresent()) {
            throw new ResourceNotFoundException("Cuenta no encontrada");
        }

        Cuenta cuenta = cuentaOpt.get();
        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
        if (nuevoSaldo < 0) {
            return ResponseEntity.badRequest().body("Saldo no disponible");
        }

        // Actualizar saldo de la cuenta
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        // Guardar movimiento
        movimientosRepository.save(movimiento);

        // Preparar datos de respuesta
        dataMovimiento.put("Datos", movimiento);
        dataMovimiento.put("message", "Movimiento registrado con éxito");

        return ResponseEntity.ok(dataMovimiento);
    }

    public ResponseEntity<Object> deleteMovimiento(Long id) {
        dataMovimiento = new HashMap<>();
        if (!movimientosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Movimiento no encontrado");
        }
        movimientosRepository.deleteById(id);
        dataMovimiento.put("message", "Movimiento eliminado");
        return ResponseEntity.ok(dataMovimiento);
    }
    // Nuevo método para obtener movimientos por tipo de movimiento (Corriente o Ahorro)
    public List<Movimiento> getMovimientosByTipoMovimiento(String tipoMovimiento) {
        return movimientosRepository.findByTipoMovimiento(tipoMovimiento);
    }
}