lst = []
for i in range(10):
    n = int(input(f'Introduce el numero {i}: '))
    if n % 2 == 0:
        raise ValueError("Valor tiene que ser par")
    lst.append(n)

print('Numeros: ', lst)
print('Sumatorio: ', sum(lst))
print('Media: ', sum(lst) / len(lst))