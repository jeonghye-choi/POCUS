var request = require('request');
const { resolve } = require('uri-js');
const { connect } = require('./config/database');

const serviceKey = '';
const url = 'http://apis.data.go.kr/1741000/DisasterMsg2/getDisasterMsgList?ServiceKey=';
var queryUrl = url + serviceKey + '&type=json&pageNo=1&numOfRows=10&flag=Y';

var db_config = require(__dirname + '/config/database.js');
var conn = db_config.init();

new Promise((resolve, rejects) => {
  request(queryUrl, function (error, response, body) {
    if (error) console.log('error' + error);
    var DisasterMsg = JSON.parse(body);
    console.log(DisasterMsg);
  });
})
  .then(() => {
    (req, res) => {
      var body = req.body;
      const field = 'number, location_name, message, send_platform';

      var sql = 'INSERT INTO DISASTERMESSAGE(' + field + ') VALUES(1,"부산","테스트메세지","fromwhere")';
      var params = [body.number, body.location_name, body.message, body.send_platform];
      console.log(sql);
      conn.query(sql, params, (err) => {
        if (error) console.log('query is not excuted. Insert fail\n');
        else console.lof('query excuted successfully!!!!\n');
      });
    };
  })
  .catch(() => {
    console.log('Error!!');
  });
