# Toteutusdokumentaatio

## Rakenne

Ohjelma koostuu neljästä pakkauksesta juuren alla, jotka ovat [huffman](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/huffman), [io](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/io), [util](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/util) ja [ui](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/ui).
Ohjelman main funktio on pakkauksen [packsack](https://github.com/sebazai/packsack/tree/master/packsack/master/src/main/java/packsack) juuressa.

<div style="float: left">
<h3>Pakkauskaavio</h3>
<img src="https://raw.githubusercontent.com/sebazai/packsack/master/documentation/kuvat/pakkaus.png" style="float: left" />
</div>

### Huffman

Huffmanissa sijaitsee luokka [Huffman.java](https://github.com/sebazai/packsack/blob/master/packsack/src/main/java/packsack/huffman/Huffman.java) millä pakataan tiedostot ja puretaan pakkaus. Tämä tiedosto sisältää sovelluslogiikan Huffmanin algoritmiin.

### io

Pakkauksessa [io](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/io) on luokat joilla käsitellään tiedostojen lukemista ja kirjoittamista. Nämä ovat toteutettu käyttäen javan [FileInputStream](https://docs.oracle.com/javase/10/docs/api/java/io/FileInputStream.html) sekä [FileOutputStream](https://docs.oracle.com/javase/10/docs/api/java/io/FileOutputStream.html) valmiita kirjastoja.  

### util

Pakkauksessa [util](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/util) on tietorakenteet, mitä ohjelman logiikka tarvitsee, näistä tärkeimmät lienee [HuffTree](https://github.com/sebazai/packsack/blob/master/packsack/src/main/java/packsack/util/HuffTree.java), [HuffNode](https://github.com/sebazai/packsack/blob/master/packsack/src/main/java/packsack/util/HuffNode.java), joita käytetään Huffmanin algoritmissa.

Util:ssa on myös  apuluokka, jolla voidaan muuttaa merkkijonot tavuiksi ja toisinpäin sekä muita tarvittavia funktioita, jolla on tärkeitä ominaisuuksia ohjelman toiminnan kannalta.

### ui

[ui](https://github.com/sebazai/packsack/tree/master/packsack/src/main/java/packsack/ui):ssa on yksinkertainen tekstipohjainen käyttöliittymä.

<div style="float: left">
<h3>Luokkakaavio</h3>
<img src="https://raw.githubusercontent.com/sebazai/packsack/master/documentation/kuvat/luokka.png" style="float: left" />
</div>
  
## Aika- ja tilavaativukset

### Huffman

#### Pakkauksen vaativuudet

Tällä hetkellä koodi laskee kaikki tavujen esiintymiset ja nämä tallennetaan taulukkoon, jonka koko on 256, mihin mahtuu kaikki eri tavujen esiintymiset. Tavut tallennetaan ilman kahden komplementtia.
Tämä vaatii aikaa O(n), missä n on tiedoston koko. Tilavaativuus on myös O(n), koska tiedosto luetaan muistiin.

Kun on laskettu esiintymiset tavuista, niin näistä eri tavujen esiintymisistä luodaan HuffNodeja, joille annetaan painoarvo, tämän painoarvon perusteella luodaan puu käyttäen HuffTree luokkaa ja tässä käytetään hyödyksi minimikekoa, jossa kevyin solmu on päälimmäisenä. Tämän jälkeen poistetaan keosta aina kaksi pienintä ja luodaan heille uusi vanhempi ja lisätään tämä puuhun. Usein esiintyvät tavut ovat siis korkeammalla puun juuressa kuin harvoin esiintyvät tavut, kun koko minimikeko on käyty läpi ja luotu tästä puu. Tämän puun luomisen aikavaativuus on O(n log n), missä n on HuffNodejen määrä, koska käydään läpi kaikki HuffNodet.

HuffManin puusta luodaan koodaustaulukko jokaista tavua kohden rekursiolla, missä jokaista tavua kohtaan, eli jokaista puun lehteä kohtaan koodataan tähän taulukkoon uusi binäärikoodi, mitä tullaan käyttämään alkuperäisen datan kirjoittamiseen. Usein esiintyvät tavut ovat puun juuren lähellä, jolloin näiden uusi koodaus on lyhyempi kuin 8 bittiä. Tämän taulukon tekemiseen menee O(n) aikaa, missä n on solmujen määrä puussa.

Kun koodaustaulukko on tehty, niin alkuperäinen tiedosto joka luettiin muistiin koodataan uusiksi tällä koodaustaulukolla ja tämä tallennetaan muistiin, jonka jälkeen se kirjoitetaan muistista suoraan kovelevylle, eli tämän pakatun tiedoston tilavaativuus on O(m), missä m on alkuperäinen tiedosto koodattuna uusiksi koodaustaulukolla. Tähän lisätään myös alkuperäisen tiedoston koko ja Huffmanin puu muutettuna tavuiksi.

Koska koodausvaiheessa luodaan taulukko, joka on yhtä iso kuin alkuperäinen tiedosto ja tämän jälkeen luodaan vielä kopio tästä taulukosta kirjoittamista varten, niin oikea tilavaativuus koko pakkaukselle on O(2n+m), missä n on alkuperäisen tiedoston koko ja m on tämän pakatun tiedoston koko.
Aikavaativuus pakkaukselle kokonaisuudessa on O(n log n).

#### Purkamisen vaativuudet

Pakatusta tiedostosta luetaan Huffmanin puu otsakkeesta, josta luodaan sama puu mitä käytettiin pakkaamiseen. 
Puun luonti kestää O(n log n), missä n on HuffNodejen määrä. Tämä luodaan rekursiolla. 

Kun puu on luotu, luetaan data muistiin pakatusta tiedostosta, tämän tilavaativuus on O(1), sillä tavuja luetaan yksi kerralla kun puuta luodaan ja samoin kun dataa aletaan lukemaan pakatusta tiedostosta. 

Pakattujen bittien muuttaminen takaisin normaaliin muotoon veisi siis aikaa O(k),missä k on pakatun tiedoston koko, sillä jokaista bittii kohden mennään puussa eteenpäin, missä bitillä "0" mennään vasemmalle ja "1":llä mennään oikealle kunnes saavutaan puun lehteen, kirjoitetaan ulos tässä lehdessä oleva merkki.
Tilavaativuus tälle on O(m), missä m on alkuperäisen tiedoston koko, sillä nämä tavut kirjoitetaan muistista kokonaisena kovalevylle. 
Pakatun tiedoston lukeminen on O(1), sillä jokainen tavu luetaan kerralla.

### Työn mahdolliset puutteet ja kehitysideat

Työ tällä hetkellä lukee datat muistiin, koska Javan omalla FileInputStream isompien tiedostojen lukeminen ja kirjoittaminen on aikaa vievää. 
Tämä toki estää suurien tiedostojen pakkaamisen, mikäli ei koneessa riitä muisti. Tätä voisi mahdollisesti parantaa käyttämällä FileChannelia ja ByteBufferia ja kirjoittaa vähän isommissa osissa tavuja.

Tavuja käsitellään tällä hetkellä merkkijonona ja tähän käytetään StringBuilderia sekä Stringiä.

Luoda oma kasvava taulukko, koska pakkauksen tilavaativuus on nyt suuri.

Parantaakseen pakkausta, niin tähän voisi implementoida LZSS algoritmin.

### Lähteet

* [Introduction to Data Compression](http://www.cs.cmu.edu/afs/cs/project/pscico-guyb/realworld/www/compression.pdf)
