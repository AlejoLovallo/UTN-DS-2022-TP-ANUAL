# RUN MYSQL CONTAINER WITH LOCAL VOLUME

```bash
docker run --name mysql_tp_db -v ~/Desktop/GDD/DSTP:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=dike2361 -p 3306:3306 -d mysql
```

* Root user name : root

* Connection

```bash 
mysql -h 127.0.0.1 -P 3306 --protocol=tcp -u root -p
```


### PHP MY ADMIN

```bash
docker run --name phpadmin -d -e PMA_HOST=127.0.0.1 -e PMA_PORT=3306 -p 8080:80 phpmyadmin/phpmyadmin
```