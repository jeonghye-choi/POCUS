FROM mariadb:10

ARG TZ
ENV TZ=${TZ:-Etc/UTC}

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY my.cnf /etc/mysql/conf.d/my.cnf

COPY ./docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/

VOLUME /var/lib/mysql

EXPOSE 3306
CMD ["mysqld"]
