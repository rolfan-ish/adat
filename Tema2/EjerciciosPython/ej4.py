import pickle as pc

class Libro:
    def __init__(self, nombre, autor, precio, precio_descuento, num_paginas):
        self.__nombre = nombre
        self.__autor = autor
        self.__precio = precio
        self.__precio_descuento = precio_descuento
        self.__num_paginas = num_paginas

    def get_descuento(self) -> float:
        return self.__precio / (self.__precio - self.__precio_descuento)
    
    def __str__(self) -> str:
        return self.__nombre

    
with open('ej4.db', 'w') as db:        
    while True:
        nombre = input('Nombre del libro: ')
        autor = input('Autor: ')
        precio = float(input('Precio: '))
        precio_descuento = float(input('Precio depues de descuento: '))
        num_paginas = int(input('Numero de páginas: '))

        db.write(repr(Libro(nombre, autor, precio, precio_descuento, num_paginas)))
        
        if input('continuar?') != 'si':
            break
    
