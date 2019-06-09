mac电脑docker 安装 及使用

## 1.安装

### 第一步，去这个地址https://docs.docker.com/docker-for-mac/install下载，下载之前需要注册docker账号



### 第二步，打开下载文件，拖到application中，安装完成



## 2.命定使用

### 2.1安装好之后查看docker版本号

```bash
docker --version
docker-compose --version
docker-machine --version
```

### 2.2 docker 运行helloworld

```bash
docker run hello-world
```

运行nginx程序以下命定，可以正常访问Nginx

```bash
docker run --detach --publish=80:80 --name=webserver nginx
```



查看容器运行详细信息

```bash
zhongzuomingdeMacBook-Pro:~ zhongzuoming$ docker container ls
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                NAMES
b6dc74573cdb        nginx               "nginx -g 'daemon of…"   11 seconds ago      Up 10 seconds       0.0.0.0:80->80/tcp   webserver
```

