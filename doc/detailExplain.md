English version:  

constants：static constants of setting parameters

    CodeConstants: static constants of error codes.
 
    Config: configuration class of reading files.
 
    HttpConstants: some static constants of http.
 
    QMonitorConstants: constants of monitor that is used for logging client's type.
 
    StorageConfig: configuration class of saving, it's maily used for reading storage path and initialziing storage of file.

controller：interfaces

    NewDownloadController：The interface of downloading which a key, the unique identifier of the file, is required to request it.  The transmission of download is based on blocks with size of 64k.
 
    UploadController: It's the interface for client to make sure whether file exists in server, this interface is called before uploading file.
 
    resolveException: The class of global exception resolve.
 
MonitorInterceptor：Monitors for logging client's type.
 
model: Common tools

servlet: Class of servlet
    
    UploadV2Servlet：The class of interfaces of uploading which a unique identification of file, usually the md5 result of the file's name, is required to request it.

utils: Common utils.

    DownloadUtils：It's for making download link of files and defining the link's format.
    
    InputStreamWrapper：It's for customizing of pictures' inputstream, mainly used for adding buffer.
    
    ImgTypeUtils: It's for getting real type of pictures, you can get it from the first 12 bytes of inputStreamWrapper.


中文版本:  

constants：提供常静态常量配置参数

    CodeConstants:静态常量 主要是错误码
 
    Config:配置文件读取类
 
    HttpConstants: 关于http的一些静态常量
 
    QMonitorConstants: 监控的常量，用于客户端类型的记录
 
    StorageConfig:存储的配置类，主要功读取文件存储位置的配置参数以及初始化文件存储位置

controller：接口

    NewDownloadController：文件下载接口，必传的请求参数为文件的key，文件的唯一标识。采取分块传输,块大小64K
 
    UploadController: 客户端查询文件是否存在接口，上传时会先验证文件是否存在。
 
    resolveException:全局异常解析类
 
MonitorInterceptor：拦截器记录客户端类型
 
model: 常用工具类

servlet:servlet类
    
    UploadV2Servlet：文件上传的servlet接口类，必传参数key，文件的唯一标识。一般是客户端将文件进行MD5后的值防止文件key重名导致的覆盖

utils:常用工具类

    DownloadUtils：定义下载链接的格式，获取到文件的下载链接。可自定义
    
    InputStreamWrapper：自定义图片inputstream,主要是增加buffer
    
    ImgTypeUtils:获取图片的真是类型，通过inputStreamWrapper的前12个字节即可确认真实类型
