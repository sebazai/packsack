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

Lataa projekti ja pura se. Mene kansioon /packsack-master/packsack/.

Voit pakata tiedostoja suoraan komentolinjalta seuraavalla komennolla:

´´´
mvn compile exec:java -Dexec.mainClass=packsack.huffman.Main -Dexec.args="<arguments>"
´´´

Hyväksyttävät argumentit ovat: 
* -co <pakattava tiedosto>
* -de <pakattu tiedosto> <puretaan tänne nimellä>
