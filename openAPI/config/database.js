var mysql = require('mysql');
var myDatabase = {
  host: 'localhost',
  port: '21',
  user: '',
  password: '',
  database: 'DISASTERMESSAGE',
};

module.exports = {
  init: function () {
    return mysql.createConnection(myDatabase);
  },
  connect: function (connect) {
    connect.connect(function (err) {
      if (err) console.log('error!');
      else console.log('mysql is connected successfully!!!');
    });
  },
};
