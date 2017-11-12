Se rapport.txt for kjørereultater

for å kjøre programmet
make n=1000

endre n etter behov

VRadixMulti er en form for innsertionsort, men skiller seg fra andre sorterings algorithmer da den ikke sammenlikner elementene med hverandre
Den sorterer bassert på indexser.

VenstreRadix er en stabil sorteringsalgorithme da den legger tall med felles nevnere i samme beholdere som gjør at tallene ligger sortert i rekkefølge.
eksempel vis er at hvis det ligger 12 og 14 etter hverandre i et array og den sorterer på 1, så ligger den 12 og 14 etter hverandre i en beholder i den rekkefølgen den leste det i.


VenstreRadix er raskere enn quicksort da den kan splitte inn hele arrayet i opptill 256 arrays på en gang istedet for i 2 for hver gang som quicksort gjør.
Dette gjør at koden ikke trenger å kjøre like mange runder som kanskje den trenger i quicksort, og man ser en tydelig speedup i resultatene.

Hotfix:
Fikset for sortering av arrayer med små tall, uten innstikk sortering ved mindre arrayer, og arrayer med like tall.

For å kjøre innstikk sortering igjen holder det å erstatte linje 60 med:
if(right<=left+15 | leftSortBit<=0){
	innstikk(a, left, right);
	return;
}

Gjennomføring:
Programmet starter ved å opprette et Random array. Den kaller så på radex sorteringen med array a, et tomt array b, posisjon fra venstre høyre posisjonen,
24 for å kunne dekke int verdier. Her var det en feil som tillate leftshift verdien til å bli negative verdier. Har forbedret dette å lagt inn en braker.
En forbedring her kunne vært å finne det største tallet, for så å se hvor mange bits det er.
Dette gjør at programmet skalerer noe bedre, og starter indexen til det største tallet. Den vil for hver iterasjon gå med en konstant 8 bits om gangen.
Den teller alle intences fra arayet, finner posisjonen den skal være i og inserter det i b.

Mask verdien av tallene er på en konstant 255 bits

Under ser du medianen av tidene under kjøring. Ved enkelte tilfeller var sortering for 10000tall ganske like, og noen ganger var quicksort raskest.

N							10				100				1000			10000			100000			1000000			10000000
VenstreRadix	0.070382	0.07972		0.352764	2.945616	11.470642		44.56852		294.526952
QuickSort			0.12506		0.139781	0.420521	3.003639	16.710046		148.949144	882.915008
