# myproject
#Zuul 是暴露的路由
#eureka 是注册中心
#配置本地host

#注册中心
127.0.0.1 eureka1
127.0.0.1 eureka2
127.0.0.1 eureka3
#redis
192.168.30.75 redis.com
#activemq
192.168.30.75 activemq.com
#mysql
192.168.30.75 mysql.com
#kafka
192.168.30.75 kafka.com

#启动顺序
1、my-eureka （注册中心）
2、my-config  (配置中心)
3、my-sso-auth (授权服务)
4、其他随便顺序可以随便启动
