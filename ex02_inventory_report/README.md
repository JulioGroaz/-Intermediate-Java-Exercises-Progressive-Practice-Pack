# Exercise 02 - Inventory Report (Intermediate)

## English
### Goal
Process stock movements and produce an inventory report plus a low stock list.

### Rules
- Each movement line has: `sku;name;qty;unitPrice`
- qty can be positive (incoming) or negative (outgoing)
- Keep total quantity per SKU
- Use the latest unitPrice for that SKU to compute total value

### Input
- Line 1: integer M (number of movements)
- Next M lines: movement lines
- Last line: integer lowThreshold

### Output
First, one line per SKU sorted by total value desc, then sku asc:
`sku name qty totalValue`

Then print a line `LOW STOCK` and list items with qty <= lowThreshold sorted by qty asc, then sku asc.
If none, print `NONE` under the section.

### Example
Input:
```
4
A12;Mouse;10;15.50
B77;Keyboard;5;30.00
A12;Mouse;-3;15.50
C90;Monitor;2;120.00
5
```

Output:
```
C90 Monitor 2 240.00
B77 Keyboard 5 150.00
A12 Mouse 7 108.50
LOW STOCK
C90 Monitor 2
B77 Keyboard 5
```

## Italiano
### Obiettivo
Processa movimenti di magazzino e genera un report con una lista di scorte basse.

### Regole
- Ogni riga movimento ha: `sku;nome;qty;prezzoUnitario`
- qty puo essere positivo (entrata) o negativo (uscita)
- Mantieni la quantita totale per SKU
- Usa l ultimo prezzoUnitario per calcolare il valore totale

### Input
- Riga 1: intero M (numero di movimenti)
- Prossime M righe: movimenti
- Ultima riga: intero lowThreshold

### Output
Prima, una riga per SKU ordinata per valore totale desc, poi sku asc:
`sku nome qty valoreTotale`

Poi stampa una riga `LOW STOCK` e gli articoli con qty <= lowThreshold ordinati per qty asc, poi sku asc.
Se non ce ne sono, stampa `NONE` sotto la sezione.

### Esempio
Input:
```
4
A12;Mouse;10;15.50
B77;Keyboard;5;30.00
A12;Mouse;-3;15.50
C90;Monitor;2;120.00
5
```

Output:
```
C90 Monitor 2 240.00
B77 Keyboard 5 150.00
A12 Mouse 7 108.50
LOW STOCK
C90 Monitor 2
B77 Keyboard 5
```
