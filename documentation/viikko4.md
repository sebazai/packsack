# Viikko 4

Aikaa käytetty, n. 15h

### Mitä olen tehnyt tällä viikolla?

Tehnyt testejä Huffman pakkaus- ja purkausluokalle. Refaktoroinut koodia, kirjoittanut käyttöohjeita, luonut käyttöliittymän ja aloittanut testaus- ja toteutusdokumentaation. Selvitellyt ohjelman hitautta.

### Miten ohjelma on edistynyt?

Ohjelmasta puuttuu edelleen omien tietorakenteiden toteutus, mutta muuten ohjelma on hyvällä mallilla.

### Haasteita ja ihmetyksiä

Hitaus on ihmetyttänyt, kun 20 megatavun tiedosto kestää 1,5 min pakata, mutta koska käsittelen näitä stringeillä ja stringin concat metodilla, niin se tuottaa hitautta. [Tämän](https://javapapers.com/java/java-string-vs-stringbuilder-vs-stringbuffer-concatenation-performance-micro-benchmark/) mukaan StringBuilder olisi paras vaihtoehto.

Samoin tavun lukeminen ja kirjoittaminen kerrallaan aiheuttaa hitautta, sillä jos luen tiedoston muistiin ja kirjoitan suoraan muistista, niin 20 megatavun tiedoston pakkaus kestää 7 sekunttia vs. tuo 1,5 min. Luettu FileChannel / ByteBufferin käytöstä, mikäli lukisi/kirjoittaisi isommissa erissä, niin tämä nopeuttaisihuomattavasti. Tällä hetkellä ohjelman compress muutettu lukemaan tavut muistiin ja käsittelemään niitä muistissa.

Kysymys kuuluu, saako FileOutputStream, FileChannelia ja ByteBufferia käyttää vai täytyisikö nämä toteuttaa itse?

### Seuraavaksi

PriorityQueuen jatkaminen, dokumentaation kirjoittaminen. Ainaki alustavasti StringBuilderin käyttö missä helpointa ja tutustuminen streamil kirjoittamista binäärejä.
