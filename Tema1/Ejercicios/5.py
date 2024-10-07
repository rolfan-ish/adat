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
    
def sumatorio(lst):
    return sum(lst)
def media(lst):
    return sum(lst) / len(lst)
def maximo(lst):
    return max(lst)
def minimo(lst):
    return min(lst)

if __name__ == '__main__':
        lst = lista()
        i = menu()
        mp = [sumatorio, media, maximo, minimo]
        print(mp[i-1](lst))
        