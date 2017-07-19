FROM java:8-alpine
MAINTAINER  Shunsuke Tadokoro <s.tadokoro0317@gmail.com>

ADD target/uberjar/clotter.jar /clotter/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/clotter/app.jar"]
