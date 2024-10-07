import random

class Persona:
    DEFAULT_SEXO = 'Hombre'
    PESO_IDEAL = 0
    PESO_SOBREPESO = 1
    PESO_DESNUTRIDO = -1
    
    def __init__(self):
        self.__nombre = ''
        self.__edad = 0
        self.__dni = self.__generaDNI()
        self.__sexo = Persona.DEFAULT_SEXO
        self.__peso = 0
        self.__altura = 0
    
    def setNombre(self, n):
        self.__nombre = n

    def setEdad(self, e):
        self.__edad = e
    
    def setSexo(self, s):
        self.__sexo = s

    def setPeso(self, p):
        self.__peso = p
    
    def setAltura(self, a):
        self.__altura = a
    
    def calcularIMC(self):
        rango = self.__peso / self.__altura**2
        if rango < 20:
            return Persona.PESO_DESNUTRIDO
        if rango > 25:
            return Persona.PESO_SOBREPESO
        return Persona.PESO_IDEAL
        
    def esMayorDeEdad(self):
        return self.__edad >= 18
    
    def toString(self):
        return f'{self.__nombre} {self.__edad} {self.__dni} {self.__sexo} {self.__altura} {self.__altura}'

    def __generaDNI(self):
        n = random.randrange(0, 99999999)
        rango = ord('Z') - ord('A')
        letra = chr((n % rango) + ord('A'))
        return f'%8d%s'%(n, letra)
        
    
