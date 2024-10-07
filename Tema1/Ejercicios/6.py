class Cryptografo:
    def encriptar(txt):
        res = ''
        for c in txt:
            res += chr(ord(c) + 1)
        return res
    
    def desencriptar(txt):
        res = ''
        for c in txt:
            res += chr(ord(c) - 1)
        return res

if __name__ == '__main__':
    txt = input("Txt: ")
    cryp = Cryptografo.encriptar(txt)
    print(cryp)
    print(Cryptografo.desencriptar(cryp))