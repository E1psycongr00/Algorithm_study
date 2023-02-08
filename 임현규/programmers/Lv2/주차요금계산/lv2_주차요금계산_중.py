from math import ceil
from collections import defaultdict


def getTotalPrice(spend_time, fees):
    default_time, default_price, unit_time, unit_price = fees
    spend_time = spend_time - default_time
    if spend_time <= 0:
        return default_price
    return default_price + int(ceil(spend_time / unit_time)) * unit_price


def solution(fees, records):
    table = defaultdict(lambda: Car())
    
    for record in records:
        time, num, stat = record.split(" ")
        print(time, num, stat)
        table[int(num)].setTime(time=time, stat=stat)
    
    answer = []
    for key in sorted(table.keys()):
        table[key].update()
        price = getTotalPrice(table[key].spend_time, fees)
        answer.append(price)
    return answer


class Car:
    in_time = None
    dead_line = "23:59"
    spend_time = 0
    
    def setTime(self, time, stat):
        if stat == "IN":
            self.in_time = time
        else:
            self.updateSpendTime(self.in_time, time)
            self.in_time = None
    
    def updateSpendTime(self, in_time, out_time):
        in_time = self._getMinuteFromTime(in_time)
        out_time = self._getMinuteFromTime(out_time)
        
        self.spend_time += out_time - in_time
    
    def update(self):
        if self.in_time is not None:
            self.spend_time += self._getMinuteFromTime("23:59") - self._getMinuteFromTime(self.in_time)
        
    def _getMinuteFromTime(self, time):
        hour, minute = map(int, time.split(":"))
        return hour * 60 + minute

