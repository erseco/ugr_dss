'use strict';
module.exports = function(app) {
  var todoList = require('../controllers/todoListController');

  // todoList Routes
  app.route('/tasks')
    .get(todoList.list_all_tasks)
    .post(todoList.create_a_task);


  app.route('/tasks/:taskId')
    .get(todoList.read_a_task)
    .put(todoList.update_a_task)
    .delete(todoList.delete_a_task);

  var product = require('../controllers/productController');

  // product Routes
  app.route('/products')
    .get(product.list_all_products)
    .post(product.create_a_product);


  app.route('/products/:productId')
    .get(product.read_a_product)
    .put(product.update_a_product)
    .delete(product.delete_a_product);


};
