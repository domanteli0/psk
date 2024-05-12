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
    open http://localhost:8080/index.xhtml

copy-db:
    CP_POSTFIX=`date +%Y-%m-%d_%H_%M_%S`; \
    mkdir ~/h2database_$CP_POSTFIX; \
    cp -r ~/h2database ~/h2database_$CP_POSTFIX

drop-db:
    trash ~/h2database/

clean:
    mvn clean

trash: drop-db clean

console:
    java -jar target/liberty/wlp/usr/shared/resources/h2-*.jar

package: clean
    mvn package

config-arq: package
    mvn liberty:create liberty:install-feature
    mvn liberty:configure-arquillian

it-tests: config-arq
    mvn failsafe:integration-test