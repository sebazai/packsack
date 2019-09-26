# packsack

Pakkausohjelma, joka toteutetaan Javalla käyttäen Huffmanin algoritmia. Tietorakenteet ja algoritmit aineharjoitustyö.

###  Dokumentaatio

* [Määrittelydokumentti](https://github.com/sebazai/packsack/blob/master/documentation/maarittelydokumentti.md)


### Viikkoraportit

* [Viikko 1](https://github.com/sebazai/packsack/blob/master/documentation/viikko1.md)
* [Viikko 2](https://github.com/sebazai/packsack/blob/master/documentation/viikko2.md)
* [Viikko 3](https://github.com/sebazai/packsack/blob/master/documentation/viikko3.md)
* [Viikko 4](https://github.com/sebazai/packsack/blob/master/documentation/viikko4.md)

### Käyttöohje

Lataa projekti ja pura se. 
Mene kansioon /packsack-master/packsack/.

#### Komentolinjan käyttö

Voit pakata tiedostoja suoraan komentolinjalta seuraavalla komennolla:

```
mvn compile exec:java -Dexec.mainClass=packsack.huffman.Main -Dexec.args="[argumentit]"
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

Hyväksyttävät argumentit ovat: 

* [-co *tiedoston-nimi*]
* [-de *purettava-tiedosto* *puretaan-tänne-nimellä*]

Missä -co pakkaa tiedoston käyttäen Huffmanin ja -de purkaa tiedoston joka on pakattu ja kirjoittaa sen ulos haluamaansa paikkaan. 
