const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const RecetaDiaSchema = new Schema({
  dia: { type: String, required: true },
  desayuno: { type: Schema.Types.ObjectId, ref: 'Receta', required: true },
  almuerzo: { type: Schema.Types.ObjectId, ref: 'Receta', required: true },
  comida: { type: Schema.Types.ObjectId, ref: 'Receta', required: true },
  merienda: { type: Schema.Types.ObjectId, ref: 'Receta', required: true },
  cena: { type: Schema.Types.ObjectId, ref: 'Receta', required: true }
});

const DietaSchema = new Schema({
  nombre: { type: String, required: true },
  recetas: [RecetaDiaSchema],
  descripcion: { type: String, required: true },
  clientes: [{ type: Schema.Types.ObjectId, ref: 'Cliente', required: true }],
  creado: { type: Schema.Types.ObjectId, ref: 'Nutricionista', required: true }
});

module.exports = mongoose.model('Dieta', DietaSchema);
