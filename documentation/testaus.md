# Testausdokumentaatio

## Suorituskykytestaus

### Huffman

Suorituskykytestaukseen on käytetty [The Cantebury Corpus](http://corpus.canterbury.ac.nz/descriptions/) ja joitakin muita suurempia tiedostoja, jotka löytyvät sivulta. Tiedot ajasta ja kuinka suuren määrän tilaa on säästänyt tulostuu jokaisen pakatun tiedoston jälkeen. 
Testit on suoritettu Lenovo T480 läppärillä, jossa on muistia 16 Gigatavua ja Intel i5-8250U CPU @ 1.6Ghz x 4.

Jokaista tiedostoa kohden on suoritettu 5 testiä, joista laskettu keskiarvo ajalle.

Testit suoritetaan kun oma tietorakenne PriorityQueuesta tehty ja purkaus on muutettu suoriutumaan muistissa.

| Tiedosto     |       Koko | Pakattu koko | Tilaa säästetty  | Pakkausaika | Purkausaika   | Koko Ubuntun .tar.gz |
| -----------  | ---------: | -----------: | ---------------- | ----------: | ------------: | --------------------;|
| alice29.txt  |   152 089B |              |                  |             |               |                      |
| asyoulik.txt |   125 179B |              |                  |             |               |                      |
| bible.txt    | 4 047 392B |              |                  |             |               |                      |
| cp.html      |    24 603B |              |                  |             |               |                      |
| fields.c     |    11 150B |              |                  |             |               |                      |
| E.coli       | 4 638 690B |              |                  |             |               |                      |
| grammar.lsp  |     3 721B |              |                  |             |               |                      |
| kennedy.xls  | 1 029 744B |              |                  |             |               |                      |
| lcet10.txt   |   426 754B |              |                  |             |               |                      |
| plrabn12.txt |   481 861B |              |                  |             |               |                      |
| ptt5         |   513 216B |              |                  |             |               |                      |
| sum          |     38240B |              |                  |             |               |                      |
| world192.txt | 2 473 400B |              |                  |             |               |                      |
| xargs.1      |      4227B |              |                  |             |               |                      |

