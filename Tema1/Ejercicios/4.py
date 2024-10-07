def menu():
    while True:
        i = input("""
          1. Sumatorio
          2. Media
          3. Máximo
          4. Mínimo
          
          0. Salir
          """)
        if i <= 4 and i >= 0:
            return i
        print("Valor invalido")

def lista():
    lst = []
    for i in range(10):
        n = int(input(f'Introduce el numero {i}: '))
        if n % 2 == 0:
            raise ValueError("Valor tiene que ser par")
        lst.append(n)
    return lst

if __name__ == '__main__':
        lst = lista()
        i = menu()
        if i == 1:
            print(sum(lst))
        elif i == 2:
            print(sum(lst) / len(lst))
        elif i == 3:
            print(max(lst))
        elif i == 4:
            print(min(lst))
        