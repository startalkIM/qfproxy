# qfproxy
qfproxy 是即时通讯软件startalk的文件、图片发送的后台JAVA服务程序。基于servlet实现的文件传输
发送文件的客户端会调用后台的uploadV2Servlet的服务实现文件上传至服务器，后台服务将文件存储至磁盘后
将下载链接返给端客户端。客户端请求下载链接会请求到服务的download接口，该接口实现将文件传输给客户端。
详情请查询 detail.md

* [配置信息](doc/config.md)
* [详情信息](doc/detailExplain.md)
