default: dev

dev ARGS="":
    mvn install liberty:dev {{ARGS}}

run:
    mvn install liberty:run

generate ARGS="":
    mvn mybatis-generator:generate {{ARGS}}
    perl -p -i -e 's/^import org.apache.ibatis.annotations.Mapper/import org.mybatis.cdi.Mapper/g' \
      src/main/kotlin/me/domantelio/psk/mybatis/mapper/*Mapper.kt

open-index:
    open http://localhost:8080/psk/index.xhtml

drop-db:
    trash ~/h2database/

clean:
    mvn clean

trash: drop-db clean

console:
    java -jar target/liberty/wlp/usr/shared/resources/h2-*.jar