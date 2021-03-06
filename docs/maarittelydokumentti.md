# Määrittelydokumentti

Projektin tarkoitus on totetuttaa javalla ohjelma, joka käyttää Huffmanin algoritmia, pakkaamaan ja purkamaan tiedostoja. Tavoitteena olisi saada tiedostot pienentymään 30-70% alkuperäisestä koostaan ja purettua ilman että tiedosto muuttuisi.

### Käytettävät tietorakenteet ja algoritmit

Huffmanin koodi ja tähän tarvittavat tietorakenteet, kuten minimikeko ja puu, jota tarvitaan koodausta varten. Tavujen uloskirjoittamiseen toteutetaan oma tavuja ja bittejä käsittelevä luokka.

### Ratkaistava ongelma

Tiedostojen koon pienentäminen ja pakkaaminen. Tarkoitus on lukea tavut tiedostosta ja tämän perusteella luoda Huffmanin puu, missä merkit jotka esiintyvät usein ovat koodattuna pienillä biteillä. Tiedosto kirjoitetaan uudestaan tavuina tämän puun persuteella, jolloin nämä merkit jotka esiintyvät usein vievät vähemmän tilaa.

### Syöte ja tuloste

Tiedosto, joka pitää pakkaa ja ulos tulee tiedosto pakattuna ja toisinpäin, eli pakattu tiedosto ja tästä tulee tiedosto joka on lukukelpoinen.

### Aika- ja tilavaativuus

Huffmanin koodin aikavaativuus O(N Log N), missä N on tiedoston pituus/koko.
Tilavaativuus tulee olemaan O(N), missä N on tiedoston koko.

### Lähteet

#### Huffman & Min PriorityQueue

* [ECE264: Huffman Coding - Purdue University](https://engineering.purdue.edu/ece264/17au/hw/HW13?alt=huffman)
* [Introduction to Data Compression](http://www.cs.cmu.edu/afs/cs/project/pscico-guyb/realworld/www/compression.pdf)
* Sedgewick Robert & Wayne Kevin, Algorithms 4th Edition (2011), Addison-Wesley Professional
