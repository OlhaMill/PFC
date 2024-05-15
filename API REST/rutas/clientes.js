const express = require('express');
const router = express.Router();
const Cliente = require('../models/Cliente');

// Obtener todos los clientes
router.get('/', async (req, res) => {
  try {
    const clientes = await Cliente.find();
    res.json(clientes);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Crear un nuevo cliente
router.post('/', async (req, res) => {
  const cliente = new Cliente(req.body);
  try {
    const nuevoCliente = await cliente.save();
    res.status(201).json(nuevoCliente);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Obtener un cliente por ID
router.get('/:id', getCliente, (req, res) => {
  res.json(res.cliente);
});

// Actualizar un cliente
router.put('/:id', getCliente, async (req, res) => {
  Object.assign(res.cliente, req.body);
  try {
    const clienteActualizado = await res.cliente.save();
    res.json(clienteActualizado);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Eliminar un cliente
router.delete('/:id', getCliente, async (req, res) => {
  try {
    await res.cliente.remove();
    res.json({ message: 'Cliente eliminado' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Middleware para obtener un cliente por ID
async function getCliente(req, res, next) {
  let cliente;
  try {
    cliente = await Cliente.findById(req.params.id);
    if (!cliente) return res.status(404).json({ message: 'Cliente no encontrado' });
  } catch (err) {
    return res.status(500).json({ message: err.message });
  }
  res.cliente = cliente;
  next();
}

module.exports = router;
