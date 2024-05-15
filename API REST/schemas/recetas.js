const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const IngredienteSchema = new Schema({
  id: { type: Schema.Types.ObjectId, ref: 'Alimento', required: true },
  cantidad: { type: Number, required: true }
});

const ValorNutricionalSchema = new Schema({
  calorias: { type: Number, required: true },
  proteina: { type: Number, required: true },
  grasa: { type: Number, required: true },
  carbohidratos: { type: Number, required: true },
  sal: { type: Number, required: true },
  azucar: { type: Number, required: true }
});

const RecetaSchema = new Schema({
  nombre: { type: String, required: true },
  ingredientes: [IngredienteSchema],
  instrucciones: [{ type: String, required: true }],
  valorNutricional: ValorNutricionalSchema,
  creado: { type: Schema.Types.ObjectId, ref: 'Nutricionista', required: true },
  foto: { type: String, required: true },
  dificultad: { type: Number, required: true },
  tiempoPreparacion: { type: Number, required: true }
});

module.exports = mongoose.model('Receta', RecetaSchema);
