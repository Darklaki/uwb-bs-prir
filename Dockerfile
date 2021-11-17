FROM gcc:4.9
COPY ./c /usr/src/myapp
WORKDIR /usr/src/myapp
RUN gcc -std=c99 -o lab4 lab4.c -lm
CMD ["./lab4"]