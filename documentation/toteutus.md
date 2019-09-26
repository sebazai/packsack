# Toteutusdokumentaatio

## Rakenne

Ohjelma koostuu kolmesta pakkauksesta juuren alla, jotka ovat [huffman](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/huffman), [io](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/io) ja [util](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/util).
Ohjelman main funktio on pakkauksen [packsack](https://github.com/sebazai/packsack/tree/master/packsack/master/src/main/java/packsack) juuressa.

**Inset pakkauskuva**

### Huffman

Huffmanissa sijaitsee luokka [Huffman.java](https://github.com/sebazai/packsack/blob/master/packsack/src/main/java/packsack/huffman/Huffman.java) millä pakataan tiedostot ja puretaan pakkaus. Tämä tiedosto sisältää sovelluslogiikan Huffmanin algoritmiin.

### io

Pakkauksessa [io](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/io) on luokat joilla käsitellään tiedostojen lukemista ja kirjoittamista. Nämä ovat toteutettu käyttäen javan [FileInputStream](https://docs.oracle.com/javase/10/docs/api/java/io/FileInputStream.html) sekä [FileOutputStream](https://docs.oracle.com/javase/10/docs/api/java/io/FileOutputStream.html) valmiita kirjastoja.  

### util

Pakkauksessa [util](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/util) on tietorakenteet, mitä ohjelman logiikka tarvitsee, näistä tärkeimmät lienee [HuffTree](https://github.com/sebazai/packsack/blob/master/packsack/src/main/java/packsack/util/HuffTree.java), [HuffNode](https://github.com/sebazai/packsack/blob/master/packsack/src/main/java/packsack/util/HuffNode.java), joita käytetään Huffmanin algoritmissa.

Util:ssa on myös  apuluokka, jolla voidaan muuttaa merkkijonot tavuiksi ja toisinpäin sekä muita tarvittavia funktioita, jolla on tärkeitä ominaisuuksia ohjelman toiminnan kannalta.
  
## Aika- ja tilavaativukset


