const express = require('express');
const router = express.Router();
const Consulta = require('../models/Consulta');

// Obtener todas las consultas
router.get('/', async (req, res) => {
  try {
    const consultas = await Consulta.find().populate('clienteId').populate('usuarioId');
    res.json(consultas);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Crear una nueva consulta
router.post('/', async (req, res) => {
  const consulta = new Consulta(req.body);
  try {
    const nuevaConsulta = await consulta.save();
    res.status(201).json(nuevaConsulta);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Obtener una consulta por ID
router.get('/:id', getConsulta, (req, res) => {
  res.json(res.consulta);
});

// Actualizar una consulta
router.put('/:id', getConsulta, async (req, res) => {
  Object.assign(res.consulta, req.body);
  try {
    const consultaActualizada = await res.consulta.save();
    res.json(consultaActualizada);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Eliminar una consulta
router.delete('/:id', getConsulta, async (req, res) => {
  try {
    await res.consulta.remove();
    res.json({ message: 'Consulta eliminada' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Middleware para obtener una consulta por ID
async function getConsulta(req, res, next) {
  let consulta;
  try {
    consulta = await Consulta.findById(req.params.id).populate('clienteId').populate('usuarioId');
    if (!consulta) return res.status(404).json({ message: 'Consulta no encontrada' });
  } catch (err) {
    return res.status(500).json({ message: err.message });
  }
  res.consulta = consulta;
  next();
}

module.exports = router;
