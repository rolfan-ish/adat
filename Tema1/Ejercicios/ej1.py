from random import choice

def rnd_word(fitx1, fitx2):
    with open(fitx1, 'r') as infile, open(fitx2, 'w') as outfile:
        for line in infile.readlines():
            outfile.write(choice(line.split(' ')))
