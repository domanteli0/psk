run:
    mvn install wildfly:run \
      -Dswarm.logging.loggers.[org.jboss.weld].level=DEBUG \
      -Dorg.jboss.logging.provider=slf4j \
      -Dorg.slf4j.simpleLogger.log.org.jboss.weld=debug

watch:
    mvn install wildfly:dev

generate ARGS="":
    mvn mybatis-generator:generate {{ARGS}}
    perl -p -i -e 's/^import org.apache.ibatis.annotations.Mapper/import org.mybatis.cdi.Mapper/g' \
      src/main/kotlin/me/domantelio/psk/mybatis/mapper/ItemMapper.kt

clean:
    mvn clean
