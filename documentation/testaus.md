# Testausdokumentaatio

## Suorituskykytestaus

### Huffman

Suorituskykytestaukseen on käytetty [The Canterbury Corpus](http://corpus.canterbury.ac.nz/descriptions/) ja joitakin muita suurempia tiedostoja, jotka löytyvät sivulta. Tiedot ajasta ja kuinka suuren määrän tilaa on säästänyt tulostuu jokaisen pakatun tiedoston jälkeen. 
Testit on suoritettu Lenovo T480 läppärillä, jossa on muistia 16 Gigatavua ja Intel i5-8250U CPU @ 1.6Ghz x 4.

Jokaista tiedostoa kohden on suoritettu 8 testiä, joista laskettu keskiarvo ajalle.
Testit ovat suoritettu käyttäen .jar tiedostoa, eli luvut eivät ole ohjelman suorituskykytestauksista.

| Tiedosto     |       Koko | Pakattu koko | Tilaa säästetty| Pakkausaika | Purkausaika | .tar.gz |
| -----------  | ---------: | -----------: | ------------:| ----------: | --------: | --------:|
| alice29.txt  |   152 089B |     87 684B  |      42.28 % |      46 ms  |    124 ms |  54 656B |
| asyoulik.txt |   125 179B |     75 895B  |      39.37 % |      43 ms  |    106 ms |  49 150B |
| bible.txt    | 4 047 392B |  2 218 533B  |      45.19 % |     267 ms  |   2239 ms |1 192 150B|
| cp.html      |    24 603B |     16 311B  |      33.70 % |      21 ms  |     30 ms |   8 161B |
| fields.c     |    11 150B |      7 143B  |      35.94 % |      17 ms  |     19 ms |   3 301B |
| E.coli       | 4 638 690B |  1 159 682B  |      75.00 % |     196 ms  |   1166 ms |1 342 310B|
| grammar.lsp  |     3 721B |      2 281B  |      38.70 % |      13 ms  |      9 ms |   1 406B |
| kennedy.xls  | 1 029 744B |    462 856B  |      55.05 % |     102 ms  |    465 ms | 204 288B |
| lcet10.txt   |   426 754B |    250 673B  |      41.26 % |      82 ms  |    280 ms | 145 126B |
| plrabn12.txt |   481 861B |    275 688B  |      42.79 % |      86 ms  |    302 ms | 195 510B |
| ptt5         |   513 216B |    106 754B  |      79.20 % |      68 ms  |    162 ms |  56 705B |
| sum          |    38 240B |     26 116B  |      31.71 % |      28 ms  |     46 ms |  13 173B |
| world192.txt | 2 473 400B |  1 558 717B  |      36.98 % |     207 ms  |   1575 ms | 725 166B |
| xargs.1      |     4 227B |      2 714B  |      35.79 % |      13 ms  |      9 ms |   1 927B |

Tilaa säästynyt keskimäärin 45.21 % ja keskimäärin algoritmi pakkaa siis 54.79 %.

Ohjelmassa voi ajaa ylläolevan suorituskykytestauksen omalla koneella komennolla:

```
mvn test -Dtest=\Performance*
```

## Yksikkötestaus




## Lähteet

* [The Canterbury Corpus](https://corpus.canterbury.ac.nz/descriptions/)
