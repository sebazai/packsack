# Viikko 4

Aikaa käytetty, n. 15h

### Mitä olen tehnyt tällä viikolla?

Tehnyt testejä Huffman pakkaus- ja purkausluokalle. Refaktoroinut koodia, kirjoittanut käyttöohjeita, luonut käyttöliittymän ja aloittanut testaus- ja toteutusdokumentaation. Selvitellyt ohjelman hitautta.
Minimikekoon tutustuminen.

### Miten ohjelma on edistynyt?

Ohjelmasta puuttuu edelleen minimikeon tietorakententeen toteutus, mutta muuten ohjelma on hyvällä mallilla.

### Haasteita ja ihmetyksiä

Hitaus on ihmetyttänyt, kun 20 megatavun tiedosto kestää 1,5 min pakata, mutta koska käsittelen näitä stringeillä ja stringin concat metodilla, niin se tuottaa hitautta. [Tämän](https://javapapers.com/java/java-string-vs-stringbuilder-vs-stringbuffer-concatenation-performance-micro-benchmark/) mukaan StringBuilder olisi paras vaihtoehto.

Samoin tavun lukeminen ja kirjoittaminen kerrallaan aiheuttaa hitautta, sillä jos luen tiedoston muistiin ja kirjoitan suoraan muistista, niin 20 megatavun tiedoston pakkaus kestää 7 sekunttia vs. tuo 1,5 min. Luettu FileChannel / ByteBufferin käytöstä, mikäli lukisi/kirjoittaisi isommissa erissä, niin tämä varmaan nopeuttaisi ohjelmaa. Tällä hetkellä ohjelman compress muutettu lukemaan tavut muistiin ja käsittelemään niitä muistissa.

### Seuraavaksi

Minimikeon aloittaminen, dokumentaation kirjoittaminen. Ainaki alustavasti StringBuilderin käyttö missä helpointa ja decompress muuttaminen käyttäen muistia, jotta isohkojen tiedostojen purkaminen ei kestäisi niin kauaan.
