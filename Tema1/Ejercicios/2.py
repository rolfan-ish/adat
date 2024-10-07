lst = []
for i in range(10):
    lst.append(int(input(f'Introduce el numero {i}: ')))
print('Numeros: ', lst)
print('Sumatorio: ', sum(lst))
print('Media: ', sum(lst) / len(lst))