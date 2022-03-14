FROM openjdk:11
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

VOLUME /tmp

ARG DEPENDENCY=build/dependency
ARG APPCLASS=hello.Application
ENV APPCLASS_ENV=$APPCLASS

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT java -cp app:app/lib/* $APPCLASS_ENV