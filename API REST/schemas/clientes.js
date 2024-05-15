const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const ClienteSchema = new Schema({
  nombre: { type: String, required: true },
  email: { type: String, required: true },
  telefono: { type: Number, required: true },
  fechaNacimiento: { type: Date, required: true },
  genero: { type: String, required: true },
  nutricionista: { type: Schema.Types.ObjectId, ref: 'Nutricionista', required: true },
  valorNutricional: {
    calorias: Number,
    proteina: Number,
    grasa: Number,
    carbohidratos: Number,
    sal: Number,
    azucar: Number
  },
  preferenciasDashboard: {
    idioma: String,
    zonaHoraria: String,
    temaInterfaz: String,
    undMedida: String,
    componenteDashboard: [String]
  },
  fotos: [String],
  progreso: [
    {
      fecha: Date,
      peso: Number,
      medidas: { cintura: Number, cadera: Number },
      actividadFisica: [String],
      objetivo: String,
      notas: [String]
    }
  ]
});

module.exports = mongoose.model('Cliente', ClienteSchema);
