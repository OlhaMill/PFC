const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const AlimentoSchema = new Schema({
  nombre: { type: String, required: true },
  creado: { type: Schema.Types.ObjectId, ref: 'Nutricionista', required: true }
});

module.exports = mongoose.model('Alimento', AlimentoSchema);
