// baidu geolocation

var cordova = require('cordova');

var SERVICE_NAME = 'BaiduGeolocation';
var ACTION_GET_CURRENT_POSITION = 'getCurrentPosition';
var ACTION_WATCH_POSITION = 'watchPosition';
var ACTION_CLEAR_WATCH = 'clearWatch';

var geolocation = module.exports = {};

geolocation.getCurrentPosition = function (success, error, options) {
  cordova.exec(success, error, SERVICE_NAME, ACTION_GET_CURRENT_OPTION, [options]);
};

geolocation.watchPosition = function (success, error, options) {
  cordova.exec(success, error, SERVICE_NAME, ACTION_WATCH_POSITION, [options]);
};

geolocation.clearWatch = function (watchId) {
  cordova.exec(function () {
    alert(JSON.stringify(arguments));
  }, function () {
    alert('fail');
  }, SERVICE_NAME, ACTION_CLEAR_WATCH, [watchId]);
};