const express = require('express');
const router = express.Router();
const Receta = require('../models/Receta');

// Obtener todas las recetas
router.get('/', async (req, res) => {
  try {
    const recetas = await Receta.find().populate('ingredientes.id').populate('creado');
    res.json(recetas);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Crear una nueva receta
router.post('/', async (req, res) => {
  const receta = new Receta(req.body);
  try {
    const nuevaReceta = await receta.save();
    res.status(201).json(nuevaReceta);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Obtener una receta por ID
router.get('/:id', getReceta, (req, res) => {
  res.json(res.receta);
});

// Actualizar una receta
router.put('/:id', getReceta, async (req, res) => {
  Object.assign(res.receta, req.body);
  try {
    const recetaActualizada = await res.receta.save();
    res.json(recetaActualizada);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Eliminar una receta
router.delete('/:id', getReceta, async (req, res) => {
  try {
    await res.receta.remove();
    res.json({ message: 'Receta eliminada' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Middleware para obtener una receta por ID
async function getReceta(req, res, next) {
  let receta;
  try {
    receta = await Receta.findById(req.params.id).populate('ingredientes.id').populate('creado');
    if (!receta) return res.status(404).json({ message: 'Receta no encontrada' });
  } catch (err) {
    return res.status(500).json({ message: err.message });
  }
  res.receta = receta;
  next();
}

module.exports = router;
