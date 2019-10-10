# packsack

Pakkausohjelma, joka toteutetaan Javalla käyttäen Huffmanin algoritmia. Tietorakenteet ja algoritmit aineharjoitustyö.

###  Dokumentaatio

* [Määrittelydokumentaatio](https://github.com/sebazai/packsack/blob/master/documentation/maarittelydokumentti.md)
* [Toteutusdokumentaatio](https://github.com/sebazai/packsack/blob/master/documentation/toteutus.md)
* [Testausdokumentaatio](https://github.com/sebazai/packsack/blob/master/documentation/testaus.md)

### Viikkoraportit

* [Viikko 1](https://github.com/sebazai/packsack/blob/master/documentation/viikko1.md)
* [Viikko 2](https://github.com/sebazai/packsack/blob/master/documentation/viikko2.md)
* [Viikko 3](https://github.com/sebazai/packsack/blob/master/documentation/viikko3.md)
* [Viikko 4](https://github.com/sebazai/packsack/blob/master/documentation/viikko4.md)
* [Viikko 5](https://github.com/sebazai/packsack/blob/master/documentation/viikko5.md)
* [Viikko 6](https://github.com/sebazai/packsack/blob/master/documentation/viikko6.md)

### Käyttöohje

Lataa projekti ja pura se. 
Mene kansioon /packsack-master/packsack/.

#### Komentolinjan käyttö

Voit pakata tiedostoja suoraan komentolinjalta seuraavalla komennolla:

```
mvn compile exec:java -Dexec.mainClass=packsack.Main -Dexec.args='[argumentit]'
```

#### JAR:in käyttö

JAR tiedoston voit luoda seuraavalla komennolla:

```
mvn package
```

JAR tiedosto luodaan automaattisesti /target/ kansioon, voit ajaa tiedostoa seuraavalla komennolla:

```
java -jar packsack-1.0-SNAPSHOT.jar [argumentit]
```

##### Hyväksyttävät argumentit ovat: 

* [-co *tiedoston-nimi*]
* [-de *purettava-tiedosto* *puretaan-tänne-nimellä*]

Missä -co pakkaa tiedoston käyttäen Huffmanin ja -de purkaa tiedoston joka on pakattu ja kirjoittaa sen ulos haluamaansa paikkaan. 

#### Checkstyle

Checkstyle:n tarkistaminen onnistuu komennolla:
```
mvn jxr:jxr checkstyle:checkstyle
```
Tiedosto sijaitsee kansiossa /target/site/checkstyle.html

#### Testaus ja Jacoco

Testit voi suorittaa komennolla
```
mvn test
```

Jacoco raportin saa tehtyä komennolla

```
mvn jacoco:report
```

Joka generoi uuden tiedoston /target/site/jacoco/index.html

Suorituskykytestauksen saa tehtyä komennolla
```
mvn test -Dtest=\Performance*
```

#### Javadoc

Javadoc:in saa generoitua seuraavalla komennolla:

```
mvn javadoc:javadoc
```

Javadociin on myös ympätty yWorksin UMLdoclet.
