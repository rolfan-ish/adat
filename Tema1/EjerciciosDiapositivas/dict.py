def elem(d, bus):
    for k,v in d:
        if v == bus:
            return bus
    return None