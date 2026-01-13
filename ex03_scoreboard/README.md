# Exercise 03 - Scoreboard (Intermediate)

## English
### Goal
Compute a tournament ranking from match results using tie-breakers.

### Rules
- Win = 3 points, draw = 1 point, loss = 0 points
- Goal difference = goalsFor - goalsAgainst
- Sort by points desc, then goal difference desc, then goalsFor desc, then team name asc

### Input
- Line 1: integer G (number of games)
- Next G lines: `teamA;teamB;scoreA;scoreB`

### Output
One line per team: `team points goalDiff goalsFor`

### Example
Input:
```
4
Lions;Tigers;2;1
Lions;Bears;0;0
Bears;Tigers;1;3
Tigers;Lions;2;2
```

Output:
```
Lions 5 1 4
Tigers 4 1 6
Bears 1 -2 1
```

## Italiano
### Obiettivo
Calcola la classifica di un torneo con i criteri di spareggio.

### Regole
- Vittoria = 3 punti, pareggio = 1 punto, sconfitta = 0 punti
- Differenza reti = goalFatti - goalSubiti
- Ordina per punti desc, poi differenza reti desc, poi goalFatti desc, poi nome squadra asc

### Input
- Riga 1: intero G (numero partite)
- Prossime G righe: `squadraA;squadraB;punteggioA;punteggioB`

### Output
Una riga per squadra: `squadra punti diffReti goalFatti`

### Esempio
Input:
```
4
Lions;Tigers;2;1
Lions;Bears;0;0
Bears;Tigers;1;3
Tigers;Lions;2;2
```

Output:
```
Lions 5 1 4
Tigers 4 1 6
Bears 1 -2 1
```
