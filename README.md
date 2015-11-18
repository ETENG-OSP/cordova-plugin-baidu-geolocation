Baidu Geolocation for Cordova
======================

Baidu 定位 SDK 版本：6.1.3

Cordova 版本：5.0.0

Cordova 百度定位插件，兼容 W3C 的 geolocation 标准，解决中国大陆手机无法定位的问题。

如果需要同时在 iOS 里和 Android 里使用，请在 `config.xml` 里分别配置：

```xml
...
  <platform name="android">
    <plugin name="cordova-plugin-baidu-geolocation" spec="https://github.com/ETENG-OSP/cordova-plugin-baidu-geolocation">
      <variable name="API_KEY" value="百度分配的AK" />
    </plugin>
  </platform>
  <platform name="ios">
    <plugin name="cordova-plugin-geolocation" spec="~1.0.0" />
  </platform>
...
```

安装方法
-------

```
cordova plugin add https://github.com/ETENG-OSP/cordova-plugin-baidu-geolocation.git --variable API_KEY=百度分配的AK --save
```

关于 API_KEY
--------

使用前需要在百度申请应用，获取 API_KEY。填错了的话仅能使用 GPS 定位，无法使用基站与 WIFI 定位。


使用方法
--------

### navigator.geolocation.getCurrentPosition(success, [error], [options]);
获取当前位置
```
var options = {
  enableHighAccuracy: true,  // 是否使用 GPS
  maximumAge: 30000,         // 缓存时间
  timeout: 27000,            // 超时时间
  coorType: 'bd09ll'         // 默认是 gcj02，可填 bd09ll 以获取百度经纬度用于访问百度 API
}
```

succes 原型：
```
function success(position, extra) {
}
```

position 定义：
```
{
  "coords": {
    "latitude": "number",
    "longitude": "number",
    "altitude": "number",
    "accuracy": "number",
    "altitudeAccuracy": "number",
    "heading": "string",
    "speed": "number"
  },
  "timestamp": "number"
}
```

extra 定义：
```
{
  "type": "string"
}
```

### navigator.geolocation.watchPosition(success, [error], [options]);
持续追踪位置变更

### navigator.geolocation.clearWatch(watchId);
清除位置追踪

## 关于坐标系


