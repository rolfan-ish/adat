import pickle as pc
import random as rd

lista = [rd.randrange(-100, 100) for _ in range(1000)]

listapc = []
with open('randoms.data', 'rb') as ifile:
    listapc = pc.load(ifile)

diffs = [abs(a - b) for a, b in zip(lista, listapc)]
print(sum(diffs)/len(diffs))
