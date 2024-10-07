from random import randint, sample

def foo(oracion):
    pal1, pal2 = sample(oracion.split(), 2)
    res = ''
    i = 0
    j = 0
    for _ in range(len(pal1) + len(pal2)):
        if i < len(pal1):
            res += pal1[i]
            i += 1
        elif j < len(pal2):
            res += pal2[j]
            j += 1
        elif randint(0, 1) == 0:
            res += pal1[i]
            i += 1
        else:
            res += pal2[j]
            j += 1
    return res
