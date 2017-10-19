Kjøring av fil kan gjøres med make

Makefilen vil automatisk kompilere filen og kjøre den

For å lage en utskrift av programmet kjør make utskrift og søk som vanelig etter ord.
Når du er ferdig avsluttes programmet med q.
Filen vil automatisk vises i terminalen ved avsluttning med kall på q.

Mye av metodene er rekursive og kaller på seg selv n ganger.

Innsettings metoden er også rekursiv og vil fortsette til den finner slutt noden for hvert ord.
Hver node har opptill 2 barn som vil halvere innsettings tiden for hvert ledd. Denne er derfor logarithmisk vekst.

søkemetoden findSimmular vil raskt lete igjennom listen etter liknende ord fordelt over flere algorithmer. Jo lengre oppe i binærtreet ordet ligger jo raskere vil ordet finnes.
Denne metoden skalerer logarithmisk.

O notasjon for søkefunksjonene.
//endre plass pa bokstaver

Denne har en O notasjon lik n

//bytt ut bokstaver
Denne har en O notasjon lik n*k

//en bokstav er fjernet
denne har en O notasjon lik n^n

//en bokstav lagt til
Denne har en O notasjon lik (n*k)^n	
