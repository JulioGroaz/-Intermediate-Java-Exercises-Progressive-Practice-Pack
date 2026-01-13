# Exercise 01 - Word Stats (Intermediate Easy)

## English
### Goal
Given a block of text, normalize it and print the top N most frequent words.

### Rules
- Lowercase everything.
- A word is a sequence of letters or digits.
- Ignore empty tokens.
- Sort by frequency desc, then by word asc.

### Input
- Line 1: integer N (how many top words to print)
- Lines 2..: text lines
- A line with `END` marks the end of the text

### Output
Print N lines with `word count`.

### Example
Input:
```
3
Hello, world! Hello again.
World of code.
END
```

Output:
```
hello 2
world 2
again 1
```

## Italiano
### Obiettivo
Dato un testo, normalizzalo e stampa le N parole piu frequenti.

### Regole
- tutto in minuscolo
- una parola e una sequenza di lettere o cifre
- ignora token vuoti
- ordina per frequenza desc, poi parola asc

### Input
- Riga 1: intero N (quante parole stampare)
- Righe successive: testo
- Una riga con `END` termina il testo

### Output
N righe con `parola conteggio`.

### Esempio
Input:
```
3
Hello, world! Hello again.
World of code.
END
```

Output:
```
hello 2
world 2
again 1
```
