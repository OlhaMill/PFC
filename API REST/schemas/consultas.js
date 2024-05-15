const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const ConsultaSchema = new Schema({
  clienteId: { type: Schema.Types.ObjectId, ref: 'Cliente', required: true },
  usuarioId: { type: Schema.Types.ObjectId, ref: 'Nutricionista', required: true },
  fechaHora: { type: Date, required: true },
  notas: { type: String, required: true }
});

module.exports = mongoose.model('Consulta', ConsultaSchema);
