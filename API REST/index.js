const express = require('express');
const mongoose = require('mongoose');
const app = express();
const port = process.env.PORT || 3000;

// Middleware para parsear JSON
app.use(express.json());

// Conexión a MongoDB
mongoose.connect('mongodb://localhost:27017/nutribalance', {
  useNewUrlParser: true,
  useUnifiedTopology: true
}).then(() => {
  console.log('Conectado a MongoDB');
}).catch(err => {          
  console.error('Error conectando a MongoDB', err);
});

// Rutas
const alimentosRouter = require('./routes/alimentos');
const clientesRouter = require('./routes/clientes');
const consultasRouter = require('./routes/consultas');
const dietasRouter = require('./routes/dietas');
const nutricionistasRouter = require('./routes/nutricionistas');
const recetasRouter = require('./routes/recetas');
app.use('/alimentos', alimentosRouter);
app.use('/clientes', clientesRouter);
app.use('/consultas', consultasRouter);
app.use('/dietas', dietasRouter);
app.use('/nutricionistas', nutricionistasRouter);
app.use('/recetas', recetasRouter);

// Ruta básica
app.get('/', (req, res) => {
  res.send('API REST con Express.js');
});

// Iniciar el servidor
app.listen(port, () => {
  console.log(`Servidor escuchando en http://localhost:${port}`);
});
nb