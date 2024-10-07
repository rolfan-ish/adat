import pickle as pc
import random as rd

lista = [rd.randrange(-100, 100) for _ in range(1000)]
with open('randoms.data', 'wb') as ofile:
    pc.dump(lista, ofile)
