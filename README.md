#MariaDB
The easiest way to get a running MariaDB instance is to use docker with the following command
```shell

docker run -p 3306:3306 --name mariadbtest -e MYSQL_ROOT_PASSWORD=root -d mariadb
sudo docker exec -it mariadbtest bash
mysql -u root -p
create database elasticsearch_admin
```
