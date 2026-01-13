# Exercise 05 - Meeting Planner (Intermediate Hard)

## English
### Goal
Find free time slots inside working hours, given a list of meetings.

### Rules
- Times are in `HH:MM` (24h)
- Meetings can overlap; merge them
- Consider only the part inside working hours
- Free slots are between merged meetings and inside work hours
- Output only slots with duration >= minDuration

### Input
- Line 1: `workStart;workEnd`
- Line 2: integer M (number of meetings)
- Next M lines: `start;end`
- Last line: integer minDuration (minutes)

### Output
One line per slot: `HH:MM-HH:MM`
If there are no slots, print `NONE`.

### Example
Input:
```
09:00;18:00
4
09:30;10:15
12:00;13:00
12:45;14:00
16:00;16:30
30
```

Output:
```
09:00-09:30
10:15-12:00
14:00-16:00
16:30-18:00
```

## Italiano
### Obiettivo
Trova gli slot liberi dentro l orario di lavoro, dato un elenco di meeting.

### Regole
- Orari in `HH:MM` (24h)
- I meeting possono sovrapporsi; vanno fusi
- Considera solo la parte dentro l orario di lavoro
- Gli slot liberi sono tra i meeting fusi e dentro l orario
- Stampa solo gli slot con durata >= minDuration

### Input
- Riga 1: `inizioLavoro;fineLavoro`
- Riga 2: intero M (numero meeting)
- Prossime M righe: `inizio;fine`
- Ultima riga: intero minDuration (minuti)

### Output
Una riga per slot: `HH:MM-HH:MM`
Se non ci sono slot, stampa `NONE`.

### Esempio
Input:
```
09:00;18:00
4
09:30;10:15
12:00;13:00
12:45;14:00
16:00;16:30
30
```

Output:
```
09:00-09:30
10:15-12:00
14:00-16:00
16:30-18:00
```
