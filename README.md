# PSK

## Progress

- [x] 1.  Pademonstruoti darbo su pasirinktais įrankiais (IDE, Application Server, Build tool, Version Control System) minimalų ciklą (**0.15**):
    1.  naudojantis IDE padaryti minimalų pakeitimą projekto išeities tekstuose ir surinkti projektą, (**0.05 balo**)
    2.  gebėti parodyti, kurioje vietoje IDE yra pasiekiamas dalykinis serveris (Application Server), kaip startuoti/sustabdyti dalykinį serverį, paleisti surinkimo rezultatus vykdymui dalykiniame serveryje, (**0.05 balo**)
    3.  projekto išeities tekstų pakeitimus patalpinti versijų kontrolės sistemoje. (**0.05 balo**)
- [ ] 2.  DB, ORM/JPA ir DataMapper/MyBatis (**0.25**):
    - [x] 1.  Sukurti duomenų bazę. Turi būti naudojami one-to-many ir many-to-many ryšiai. (**0.05 balo)**
    - [ ] 2.  Sugeneruotoms/parašytoms JPA esybėms gebėti parodyti ir paaiškinti, (**0.1 balo**)
        - [ ] kaip esybių laukai (Java klasių laukai) atitinka DB lentelių stulpelius;
        - [ ] ką daryti, jei norime lauką pavadinti kitaip nei pavadintas atitinkamas stulpelis.
        - [ ] Gebėti parodyti ir paaiškinti, kur esybėse yra one-to-many bei many-to-many ryšiai ir kaip jie atitinka DB lenteles.
    - [ ] 3.  Tą patį gebėti paaiškinti MyBatis esybėms (**0.1**), .i.e:
        - [ ] kaip esybių laukai (Java klasių laukai) atitinka DB lentelių stulpelius;
        - [ ] ką daryti, jei norime lauką pavadinti kitaip nei pavadintas atitinkamas stulpelis.
        - [ ] Gebėti parodyti ir paaiškinti, kur esybėse yra one-to-many bei many-to-many ryšiai ir kaip jie atitinka DB lenteles.
- [ ] 3.  Įgyvendinti vieną panaudos atvejį (**0.6**):
    - [ ] 1.  _UI_: su laisvai pasirinkta technologija:
        - [ ] 1.  reikia sukurti bent vieną puslapį, pateikiantį DB esančius duomenis/dalį duomenų. Būtina pateikti kelių susijusių DB esybių duomenis (pvz.: universitetas ir jo studentai; studentai ir jų lankomi kursai). T.y., formuojant UI turi būti naviguojama per DB ryšius one-to-many/many-to-one/many-to-many (bent per vieną). (**0.1**)
        - [ ] 2.  reikia sukurti bent vieną formą, leidžiančią įvesti duomenis. Įvesti duomenys turi būti automatiškai įrašomi į kokios nors klasės objektą (data binding), o vėliau ir į DB. (**0.1**)
    - [ ] 2.  _Dalykinio funkcionalumo komponentas_ (Business logic component):
        - [ ] turi būti bent vienas CDI komponentas, apdorojantis per UI įvestus duomenis.
        - [ ] Reikia gebėti paaiškinti, kada Java klasė virsta komponentu,
        - [ ] ką reiškia @RequestScoped, @SessionScoped, @ApplicationScoped bei @Inject anotacijos. (**0.05**)
    - [ ] 3.  _Duomenų prieigos komponentas_ (Data Access component - DAO): Turi būti duomenų išsaugojimo/modifikavimo duomenų bazėje DAO komponentas:
        - [ ] 1.  Naudojantis ORM/JPA (**0.1**)
        - [ ] 2.  Naudojantis DataMapper/MyBatis (**0.1**)
    - [ ] 4.  Reikia gebėti paaiškinti skirtumus/privalumus/trūkumus tarp ORM ir DataMapper (kada ką geriau naudoti). (**0.1**)
    - [ ] 5.  Būtinos automatinės/deklaratyvios DB transakcijos (rankomis rašyti "begin()/commit()" negalima). (**0.05**)

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
