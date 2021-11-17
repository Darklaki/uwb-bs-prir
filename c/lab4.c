#include <stdio.h>
#include <time.h>
#include <math.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

#define P_PROCESOW 10

float funkcja_podcalkowa(float x)
{
    return 4 * x - 6 * x + 5;
}

double funkcja_podcalkowa_2(float x)
{
    return 4 * x - 6 * x + 5;
}

float metoda_trapezow(int a, int b, int N)
{
    float s = 0.0;
    float dx = (float)(b - a) / N;

    for (int i = 1; i <= N; i++)
    {
        s += funkcja_podcalkowa(a + i * dx);
    }

    return dx * (s + (funkcja_podcalkowa(a) + funkcja_podcalkowa(b)) / 2);
}

double wezel_kg(int a, int b, double wezel)
{
    return (((double)((b - a) / 2)) * wezel + (double)((b + a) / 2));
}

double kwadratura_gaussa(double wagi[], double wezly[], int a, int b, size_t length)
{
    double res = (double)(b - a) / 2;
    double sum = 0.0;
    for (int i = 0; i < length; i++)
    {
        double wezel = wezly[i];
        double waga = wagi[i];
        sum += waga * funkcja_podcalkowa_2(wezel_kg(a, b, wezel));
    }
    return sum * res;
}

double leibniz(int N) {
    double sum = 0.00;

    for (int i = 1; i <= N; i++) {
        sum += (double)((double)(pow((double)-1.00, (double)(i - 1))))/(double)(2 * i - 1);
    }
    printf("%f", sum);
    return sum * 4.0;
}

int main(int argc, char *argv[])
{
    // Zadanie 1
    printf("--- METODA TRAPEZOW ---\n");
    for (int i = 0; i < P_PROCESOW; i++)
    {
        pid_t pid = fork();
        srand(time(NULL) ^ (getpid() << 16));
        if (pid == 0)
        {
            // Wylosuj przedział oraz ilosc trapezow
            int a = (rand() % (100 - 0 + 1)) + 0;
            int b = (rand() % (200 - a + 1)) + a;
            int N = (rand() % (5000 - 1 + 1)) + 1;
            // Wypisz wynik
            printf("PID %d: Przedział: <%d, %d>; N = %d; Wynik = %f\n", getpid(), a, b, N, metoda_trapezow(a, b, N));
            return 0;
        }

        // Rodzic czeka na ukonczenie procesow potomnych
        wait(0);
    }

    printf("--- METODA KWADRATURY GAUSSA ---\n");
    double n16_w[16] = {0.1894506104550685, 0.1894506104550685, 0.1826034150449236, 0.1826034150449236,
                        0.1691565193950025, 0.1691565193950025, 0.1495959888165767, 0.1495959888165767,
                        0.1246289712555339, 0.1246289712555339, 0.0951585116824928, 0.0951585116824928,
                        0.0622535239386479, 0.0622535239386479, 0.0271524594117541, 0.0271524594117541};
    double n16_t[16] = {-0.0950125098376374, 0.0950125098376374, -0.2816035507792589, 0.2816035507792589,
                        -0.4580167776572274, 0.4580167776572274, -0.6178762444026438, 0.6178762444026438,
                        -0.755404408355003, 0.755404408355003, -0.8656312023878318, 0.8656312023878318,
                        -0.9445750230732326, 0.9445750230732326, -0.9894009349916499, 0.9894009349916499};

    for (int i = 0; i < P_PROCESOW; i++)
    {
        pid_t pid = fork();
        srand(time(NULL) ^ (getpid() << 16));
        if (pid == 0)
        {
            // Wylosuj przedział
            int a = (rand() % (100 - 0 + 1)) + 0;
            int b = (rand() % (200 - a + 1)) + a;
            // Wypisz wynik
            printf("PID %d: Przedział: <%d, %d>; Wynik = %f\n", getpid(), a, b, kwadratura_gaussa(n16_w, n16_t, a, b, sizeof(n16_w) / sizeof(n16_w[0])));
            return 0;
        }

        // Rodzic czeka na ukonczenie procesow potomnych
        wait(0);
    }

    printf("--- LEIBNIZ ---\n");

    for (int i = 0; i < P_PROCESOW; i++)
    {
        pid_t pid = fork();
        srand(time(NULL) ^ (getpid() << 16));
        if (pid == 0)
        {
            // Wylosuj przedział
            int N = (rand() % (5000 - 100 + 1)) + 100;
            // Wypisz wynik
            printf("PID %d: N = %d; Wynik = %f\n", getpid(), N, leibniz(N));
            return 0;
        }

        // Rodzic czeka na ukonczenie procesow potomnych
        wait(0);
    }

    return 0;
}