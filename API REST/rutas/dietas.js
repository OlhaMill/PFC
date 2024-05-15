const express = require('express');
const router = express.Router();
const Dieta = require('../models/Dieta');

// Obtener todas las dietas
router.get('/', async (req, res) => {
  try {
    const dietas = await Dieta.find().populate('clientes').populate('recetas.desayuno recetas.almuerzo recetas.comida recetas.merienda recetas.cena');
    res.json(dietas);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Crear una nueva dieta
router.post('/', async (req, res) => {
  const dieta = new Dieta(req.body);
  try {
    const nuevaDieta = await dieta.save();
    res.status(201).json(nuevaDieta);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Obtener una dieta por ID
router.get('/:id', getDieta, (req, res) => {
  res.json(res.dieta);
});

// Actualizar una dieta
router.put('/:id', getDieta, async (req, res) => {
  Object.assign(res.dieta, req.body);
  try {
    const dietaActualizada = await res.dieta.save();
    res.json(dietaActualizada);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Eliminar una dieta
router.delete('/:id', getDieta, async (req, res) => {
  try {
    await res.dieta.remove();
    res.json({ message: 'Dieta eliminada' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Middleware para obtener una dieta por ID
async function getDieta(req, res, next) {
  let dieta;
  try {
    dieta = await Dieta.findById(req.params.id).populate('clientes').populate('recetas.desayuno recetas.almuerzo recetas.comida recetas.merienda recetas.cena');
    if (!dieta) return res.status(404).json({ message: 'Dieta no encontrada' });
  } catch (err) {
    return res.status(500).json({ message: err.message });
  }
  res.dieta = dieta;
  next();
}

module.exports = router;
