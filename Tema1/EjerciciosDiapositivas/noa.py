txt = input('Texto: ').split(' ')

noa = 0
for w in txt:
    if w.find('a') == -1:
        noa += 1

print("Palabras sin a: %", noa * 100 / len(txt))