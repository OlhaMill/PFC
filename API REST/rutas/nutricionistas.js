const express = require('express');
const router = express.Router();
const Nutricionista = require('../models/Nutricionista');

// Obtener todos los nutricionistas
router.get('/', async (req, res) => {
  try {
    const nutricionistas = await Nutricionista.find();
    res.json(nutricionistas);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Crear un nuevo nutricionista
router.post('/', async (req, res) => {
  const nutricionista = new Nutricionista(req.body);
  try {
    const nuevoNutricionista = await nutricionista.save();
    res.status(201).json(nuevoNutricionista);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Obtener un nutricionista por ID
router.get('/:id', getNutricionista, (req, res) => {
  res.json(res.nutricionista);
});

// Actualizar un nutricionista
router.put('/:id', getNutricionista, async (req, res) => {
  Object.assign(res.nutricionista, req.body);
  try {
    const nutricionistaActualizado = await res.nutricionista.save();
    res.json(nutricionistaActualizado);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Eliminar un nutricionista
router.delete('/:id', getNutricionista, async (req, res) => {
  try {
    await res.nutricionista.remove();
    res.json({ message: 'Nutricionista eliminado' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Middleware para obtener un nutricionista por ID
async function getNutricionista(req, res, next) {
  let nutricionista;
  try {
    nutricionista = await Nutricionista.findById(req.params.id);
    if (!nutricionista) return res.status(404).json({ message: 'Nutricionista no encontrado' });
  } catch (err) {
    return res.status(500).json({ message: err.message });
  }
  res.nutricionista = nutricionista;
  next();
}

module.exports = router;
