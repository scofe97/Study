FROM php:7.4-fpm-alpine

WORKDIR /var/www/html

COPY src . 

RUN docker-php-ext-install pdo pdo_mysql

RUN chown -R www-data:www-data /var/www/html

# ENRTYPOINT나 CMD값이 없다면 기본이미지의 값을 사용한다.