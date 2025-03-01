
from ana_STL import STL_computation
from ana_STL import directed_distance

F=STL_computation(2,120)

f_0 = F.add_predicate(1,"<",0.1)
f_1 = F.add_predicate(2,"<",0.6)
f_2 = F.Conj([f_0, f_1])
f_3 = F.add_predicate(2,">=",0.4)
f_4 = F.Conj([f_2, f_3])
f_5 = F.G(range(0, 10+1), f_4)
f_6 = F.add_predicate(1,">=",0.7)
f_7 = F.add_predicate(2,">=",0.8)
f_8 = F.add_predicate(2,"<",0.2)
f_9 = F.Disj([f_7, f_8])
f_10 = F.Conj([f_6, f_9])
f_11 = F.G(range(70, 100+1), f_10)
f_12 = F.Conj([f_5, f_11])
f_13 = F.add_predicate(1,"<=",0.9)
f_14 = F.G(range(0, 40+1), f_13)
f_15 = F.add_predicate(1,"<=",1.2)
f_16 = F.G(range(40, 80+1), f_15)
f_17 = F.Conj([f_14, f_16])
f_18 = F.add_predicate(1,"<=",1.3)
f_19 = F.G(range(80, 120+1), f_18)
f_20 = F.add_predicate(1,">=",0.0)
f_21 = F.G(range(0, 40+1), f_20)
f_22 = F.Conj([f_19, f_21])
f_23 = F.Conj([f_17, f_22])
f_24 = F.add_predicate(1,">=",0.4)
f_25 = F.G(range(40, 120+1), f_24)
f_26 = F.Conj([f_23, f_25])
f_27 = F.add_predicate(2,"<=",1.0)
f_28 = F.G(range(0, 40+1), f_27)
f_29 = F.add_predicate(2,"<=",1.2)
f_30 = F.G(range(40, 80+1), f_29)
f_31 = F.Conj([f_28, f_30])
f_32 = F.add_predicate(2,"<=",1.3)
f_33 = F.G(range(80, 120+1), f_32)
f_34 = F.add_predicate(2,">=",0.0)
f_35 = F.G(range(0, 120+1), f_34)
f_36 = F.Conj([f_33, f_35])
f_37 = F.Conj([f_31, f_36])
f_38 = F.Conj([f_26, f_37])

r=directed_distance(F, f_38, f_12)
print(r)

