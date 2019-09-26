# Viikko 3

Aikaa käytetty, n. 10h

### Mitä olen tehnyt tällä viikolla?

Tehnyt testejä Huffman pakkaus- ja purkausluokalle. Refaktoroinut koodia, kirjoittanut käyttöohjeita, luonut käyttöliittymän ja aloittanut testaus- ja toteutusdokumentaation.

### Miten ohjelma on edistynyt?

Ohjelmasta puuttuu edelleen omien tietorakenteiden toteutus, mutta muuten ohjelma on hyvällä mallilla.

### Haasteita

Hitaus on ihmetyttänyt, kun 20 megatavun tiedosto kestää 1,5 min pakata, mutta selvästi koska käsittelen näitä stringeillä, niin se tuottaa hitautta. [Tämän](https://javapapers.com/java/java-string-vs-stringbuilder-vs-stringbuffer-concatenation-performance-micro-benchmark/) mukaan StringBuilder olisi paras vaihtoehto, mutta sit taas voisi stringeistä koittaa päästä eroon, mikäli aika riittää.

### Seuraavaksi

PriorityQueuen jatkaminen, dokumentaation kirjoittaminen. Ainaki alustavasti StringBuilderin käyttö.
