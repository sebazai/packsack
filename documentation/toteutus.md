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

### Huffman

#### Pakkauksen vaativuudet

Tällä hetkellä koodi laskee kaikki tavujen esiintymiset ja nämä tallennetaan taulukkoon, jonka koko on 256, mihin mahtuu kaikki eri tavujen esiintymiset. Tavut tallennetaan ilman kahden komplementtia.
Tämä vaatii aikaa O(n), missä n on tiedoston koko. Tilavaativuus on myös O(n), koska tiedosto luetaan muistiin.

Kun on laskettu esiintymiset tavuista, niin näistä eri tavujen esiintymisistä luodaan HuffNodeja, joille annetaan painoarvo, tämän painoarvon perusteella luodaan puu käyttäen HuffTree luokkaa ja tässä käytetään hyödyksi javan PriorityQueueä, eli kekoa, jossa painavin solmu on päälimmäisenä. Usein esiintyvät tavut ovat siis korkeammalla puun juuressa kuin harvoin esiintyvät tavut. Tämän puun luomisen aikavaativuus on O(n log n), missä n on HuffNodejen määrä.

HuffManin puusta luodaan koodaustaulukko jokaista tavua kohden rekursiolla, missä jokaista tavua kohtaan, eli jokaista puun lehteä kohtaan koodataan tähän taulukkoon uusi binäärikoodi, mitä tullaan käyttämään alkuperäisen datan kirjoittamiseen. Usein esiintyvät tavut ovat puun juuren lähellä, jolloin näiden uusi koodaus on lyhyempi kuin 8 bittiä. Tämän taulukon tekemiseen menee O(n) aikaa, missä n on solmujen määrä puussa.

Kun koodaus on tehty, niin alkuperäinen tiedosto joka luettiin muistiin koodataan uusiksi tällä koodaustaulukolla ja tämä tallennetaan muistiin, jonka jälkeen se kirjoitetaan muistista suoraan kovelevylle, eli tämän pakatun tiedoston tilavaativuus on O(m), missä m on alkuperäinen tiedosto koodattuna uusiksi koodaustaulukolla. Tähän lisätään myös alkuperäisen tiedoston koko ja Huffmanin puu muutettuna tavuiksi.


