run:
    mvn install wildfly:run

generate ARGS="":
    mvn mybatis-generator:generate {{ARGS}}
