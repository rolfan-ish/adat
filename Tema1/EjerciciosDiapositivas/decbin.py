def num():
    return int(input('Introduce el numero en decimal: '))

def abin(n):
    s = ''
    while n > 0:
        r = n % 2
        if n % 2 == 1:
            s = r + s
            n -= r
        else:
            s = '0' + s
        n = n / 2
    return s

if __name__ == '__main__':
    # En vez de 'abin' se puede usar 'bin'
    print(abin(num()))