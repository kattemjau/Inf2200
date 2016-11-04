Ukjente karakterer blir ikke registrert av program.
Liten test på starten av progammet.
Programmet kjores med: java main needle.txt haystack.txt

Programmet starter med å sette prioriteringer på needle. Dermed vet programmet hvor mange Characters den skal hoppe hver gang den får en bad char shift.
Sorterings algorithmen starter å sammenligne needelen bakfra, i starten av haystacken. Hvis det er en char i haystack som er ukjent i needlen så hopper needlen 1 char bort.
Dette er grunnet at det ikke er mulig å vite hva '_' er.
