# Exercise 04 - Expense Splitter (Intermediate Hard)

## English
### Goal
Split expenses among a group and produce the transfers needed to settle balances.

### Rules
- Each expense line: `payer;amount;participants`
- Participants are comma-separated names
- The amount is split equally among all participants
- Balance = paid - owed (positive means should receive, negative means should pay)
- Use a greedy settlement: match biggest debtor with biggest creditor

### Input
- Line 1: integer E (number of expenses)
- Next E lines: expenses as described above

### Output
Section `BALANCES`, then one line per person sorted by name asc: `name balance` (2 decimals).
Section `TRANSFERS`, then one line per transfer: `from -> to amount` (2 decimals).
If there are no transfers, print `NONE` under the section.

### Example
Input:
```
3
Alice;30;Alice,Bob,Carla
Bob;12;Bob,Carla
Carla;20;Alice,Carla
```

Output:
```
BALANCES
Alice 10.00
Bob -4.00
Carla -6.00
TRANSFERS
Bob -> Alice 4.00
Carla -> Alice 6.00
```

## Italiano
### Obiettivo
Dividi le spese in un gruppo e calcola i trasferimenti per chiudere i conti.

### Regole
- Ogni spesa: `pagante;importo;partecipanti`
- Partecipanti separati da virgola
- L importo si divide in parti uguali
- Saldo = pagato - dovuto (positivo riceve, negativo paga)
- Usa un metodo greedy: abbina il debitore piu grande al creditore piu grande

### Input
- Riga 1: intero E (numero spese)
- Prossime E righe: spese come sopra

### Output
Sezione `BALANCES`, poi una riga per persona ordinata per nome asc: `nome saldo` (2 decimali).
Sezione `TRANSFERS`, poi una riga per trasferimento: `da -> a importo` (2 decimali).
Se non ci sono trasferimenti, stampa `NONE` sotto la sezione.

### Esempio
Input:
```
3
Alice;30;Alice,Bob,Carla
Bob;12;Bob,Carla
Carla;20;Alice,Carla
```

Output:
```
BALANCES
Alice 10.00
Bob -4.00
Carla -6.00
TRANSFERS
Bob -> Alice 4.00
Carla -> Alice 6.00
```
