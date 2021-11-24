# Programowanie równoległe i rozproszone
lab5

# Jak uruchomić
```
cd c
# Tworzymy kontener oraz wchodzimy w niego
docker run --rm -it -v $(pwd):/project nlknguyen/alpine-mpich
# Kompilacja w kontenerze
mpicc lab5.c -o lab5.exe
# Uruchomienie
mpirun -np 7 ./lab5.exe
```