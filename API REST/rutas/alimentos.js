const express = require('express');
const router = express.Router();
const Alimento = require('../models/Alimento');

// Obtener todos los alimentos
router.get('/', async (req, res) => {
  try {
    const alimentos = await Alimento.find();
    res.json(alimentos);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Crear un nuevo alimento
router.post('/', async (req, res) => {
  const alimento = new Alimento(req.body);
  try {
    const nuevoAlimento = await alimento.save();
    res.status(201).json(nuevoAlimento);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Obtener un alimento por ID
router.get('/:id', getAlimento, (req, res) => {
  res.json(res.alimento);
});

// Actualizar un alimento
router.put('/:id', getAlimento, async (req, res) => {
  Object.assign(res.alimento, req.body);
  try {
    const alimentoActualizado = await res.alimento.save();
    res.json(alimentoActualizado);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Eliminar un alimento
router.delete('/:id', getAlimento, async (req, res) => {
  try {
    await res.alimento.remove();
    res.json({ message: 'Alimento eliminado' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Middleware para obtener un alimento por ID
async function getAlimento(req, res, next) {
  let alimento;
  try {
    alimento = await Alimento.findById(req.params.id);
    if (!alimento) return res.status(404).json({ message: 'Alimento no encontrado' });
  } catch (err) {
    return res.status(500).json({ message: err.message });
  }
  res.alimento = alimento;
  next();
}

module.exports = router;
