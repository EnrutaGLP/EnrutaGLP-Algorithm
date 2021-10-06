#!/bin/bash
gcc generadorPedidos.c
x=1
while [ $x -lt 51 ]
do
	./a.out 10 $x
	x=`expr $x + 1`
done

x=1
while [ $x -lt 51 ]
do
	./a.out 100 $x
	x=`expr $x + 1`
done

x=1
while [ $x -lt 51 ]
do
	./a.out 200 $x
	x=`expr $x + 1`
done