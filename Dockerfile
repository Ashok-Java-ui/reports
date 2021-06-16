
FROM tomcat:8.5.41-jre8-alpine

ARG SOFTWARE_DIR="/usr/local/tomcat"
#ENV JAR_INSTANCE="/backup/vm_2020_11_16_14_31.war"
ENV JAR_INSTANCE="./target/Reports-0.0.1-SNAPSHOT.war"
ENV MIN_MEM="512M"
ENV MAX_MEM="768M"


ENV JAVA_OPTS="-Xms$MIN_MEM -Xmx$MAX_MEM -Dlog4j.debug=true -Dlog4jLocation=$SOFTWARE_DIR/logs -DconfigDeamonEndpoint=http://configdaemon:80 -Dlog4j.configuration=log4j.xml -Djava.rmi.server.hostname=localhost -Djava.security.egd=file:/dev/urandom -DLOG_REQUEST=false -DLOG_REQUEST_HEADERS=false -DuseCacheSync=true -DenvTarget=devkube"

RUN rm -rf /usr/local/tomcat/webapps/*
COPY $JAR_INSTANCE $SOFTWARE_DIR/webapps/ROOT.war

CMD ["catalina.sh","run"]