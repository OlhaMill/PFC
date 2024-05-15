const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const NutricionistaSchema = new Schema({
  nombre: { type: String, required: true },
  email: { type: String, required: true },
  telefono: { type: Number, required: true },
  preferenciasDashboard: {
    idioma: { type: String, required: true },
    zonaHoraria: { type: String, required: true },
    temaInterfaz: { type: String, required: true },
    undMedida: { type: String, required: true },
    componenteDashboard: [String] 
  }
});

module.exports = mongoose.model('Nutricionista', NutricionistaSchema);
