
'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var OrderSchema = new Schema({
  email: {
    type: String,
    required: 'Enter the name of the product'
  },
  products: {
    type: String,
    default: '[]'
  },
  pharmacy: {
    type: String,
    required: 'Enter the price of the product'
  },
  status: {
    type: [{
      type: String,
      enum: ['pending', 'ordered', 'completed']
    }],
    default: ['pending']
  }
});

module.exports = mongoose.model('Orders', OrderSchema);

