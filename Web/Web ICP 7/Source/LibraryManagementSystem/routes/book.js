var express = require('express');
var router = express.Router();
var Book = require('../models/Book.js');

/* GET ALL BOOKS */
router.get('/', function (req, res, next) {
  Book.find(function (err, products) {
    if (err) return next(err);
    res.json(products);
  });
});

/* GET SINGLE BOOK BY ID */
router.get('/:id', function (req, res, next) {
  Book.findById(req.params.id, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

/* SAVE BOOK */
router.post('/', function (req, res, next) {
  Book.create(req.body, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

/* UPDATE BOOK */
router.put('/:id', (req, res, next) => {
  console.log(req.params.id);
  Book.findByIdAndUpdate(req.params.id, req.body, (err, book) => {
    if (err) return next(err);
    res.json(book);
  });
});


/* DELETE BOOK */
router.delete('/:id',function(req, res, next){
  Book.remove({"_id": req.params.id},function(err, post){//removes book using remove method
    if (err) return next(err);
    res.json(200);
  });
});

module.exports = router;
