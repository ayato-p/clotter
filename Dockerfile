FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/clotter.jar /clotter/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/clotter/app.jar"]
