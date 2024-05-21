# PSK

## TODO/Progress

- [ ] 1. Optimistinis rakinimas: pademonstruoti, kaip gaunamas OptimisticLockException, ir kaip programuotojas gali įgyvendinti tinkamą apdorojimą (0.3)
   - [ ] 1.1. Kas nutinka su einamąja transakcija, kai gaunamas OptimisticLockException? 
   - [ ] 1.2. Kas nutinka su einamuoju EntityManager, kai gaunamas OptimisticLockException? Kaip išsaugoti esybę į DB po OptimisticLockException?
- [ ] 2. Asinchroninis komunikavimas: pateikti ilgą skaičiavimą atliekančio komponento (galima naudoti Thread.sleep(...) ir teisingo komunikavimo su juo pavyzdį (0.2)
    - [ ] 2.1. Ar gali asinchroninis komponentas įsijungti į kvietėjo pradėtą transakciją?
    - [ ] 2.2. Ar gali asinchroninis komponentas naudoti @RequestScoped EntityManager?
- [ ] 3. Glass-box extensibility: pateikti po vieną pavyzdį:
    - [ ] 3.1. CDI alternatives (@Alternative), alternatyvos pasirinkimas beans.xml faile (0.1)
    - [ ] 3.2. CDI specialization (@Specializes) (0.1)
    - [ ] 3.3. CDI Interceptor, interceptor'ių įjungimas/išjungimas beans.xml faile (0.1)
    - [ ] 3.4. CDI Decorator, dekoratorių įjungimas/išjungimas beans.xml faile ( 0.1)
- [ ] 4. RESTful paslaugos pavyzdys: įgyvendinti RESTful paslaugas, leidžiančias gauti (GET), sukurti (POST) ir modifikuoti (PUT) kokią nors esybę duomenų bazėje (0.1)

## Development

> [!IMPORTANT]
> 
> When using MyBatis Generator you'll need to replace:
> 
> ```kotlin
> import org.apache.ibatis.annotations.Mapper
> ```
> 
> with
> ```kotlin
> import org.mybatis.cdi.Mapper
> ```


## Useful links

- [How to import Wildfly dependencies in Maven](https://stackoverflow.com/a/47561592/16619794)

## Example usage

```bash
curl 'http://localhost:8080/psk/rest/item/0'
```

```bash
open http://localhost:8080/index.xhtml
```
