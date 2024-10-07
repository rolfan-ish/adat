while True:
    pal = input("Introduce la palabra")
    l = len(pal)
    if l == 0:
        break
    if l > 10:
        print(pal)
