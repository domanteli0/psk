default: dev

dev ARGS="":
    mvn install liberty:dev {{ARGS}}

run:
    mvn install liberty:run

generate ARGS="":
    mvn mybatis-generator:generate {{ARGS}}
    perl -p -i -e 's/^import org.apache.ibatis.annotations.Mapper/import org.mybatis.cdi.Mapper/g' \
      src/main/kotlin/me/domantelio/psk/mybatis/mapper/ItemMapper.kt
    perl -p -i -e 's/^import org.apache.ibatis.annotations.Mapper/import org.mybatis.cdi.Mapper/g' \
      src/main/kotlin/me/domantelio/psk/mybatis/mapper/InvoiceMapper.kt
    perl -p -i -e 's/^import org.apache.ibatis.annotations.Mapper/import org.mybatis.cdi.Mapper/g' \
      src/main/kotlin/me/domantelio/psk/mybatis/mapper/CategoryMapper.kt

open-index:
    open http://localhost:8080/psk/index.xhtml

clean:
    mvn clean
