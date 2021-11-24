#include <stdio.h>
#include <math.h>
#include "mpi.h"

double fun(double x)
{
    return pow(x, 2);
}

double leibniz(int num_of_proc, int curr_proc)
{
    double sum = 0.00;

    for (int i = curr_proc; i <= 1000000000; i += num_of_proc)
    {
        double tmp = 1.0 / (2.0 * (double)i + 1);
        if (i % 2 == 0)
        {
            sum += tmp;
        }
        else
        {
            sum -= tmp;
        }
    }

    return sum * 4.0;
}

int main(argc, argv) int argc;
char **argv;
{
    // --- ZADANIE 1
    // int rank, size;
    // double total;
    // /* inicjalizacja MPI */
    // MPI_Init(&argc, &argv);
    // /* okresla liczba uruchomonych procesow */
    // MPI_Comm_size(MPI_COMM_WORLD, &size);
    // /* okresla numer aktualnego procesu */
    // MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    // double sum = leibniz(size, rank);
    // MPI_Reduce(&sum, &total, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

    // if (rank == 0)
    // {
    //     // Finalize
    //     printf("%f", total);
    // }

    // MPI_Finalize();

    // --- ZADANIE 2 metoda prostokatow
    // int rank, size;
    // double total;
    // /* inicjalizacja MPI */
    // MPI_Init(&argc, &argv);
    // /* okresla liczba uruchomonych procesow */
    // MPI_Comm_size(MPI_COMM_WORLD, &size);
    // /* okresla numer aktualnego procesu */
    // MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    // int a = 0;
    // int b = 10;
    // int N = size;
    // double dx = (double)(b - a) / N;

    // double partial_sum = 0.0;
    // partial_sum = fun(a + rank * dx);

    // MPI_Reduce(&partial_sum, &total, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

    // if (rank == 0)
    // {
    //     double result = total * dx;
    //     // Finalize
    //     printf("%f\n", result);
    // }

    // MPI_Finalize();

    // --- ZADANIE 2 metoda trapezow
    int rank, size;
    double total;
    /* inicjalizacja MPI */
    MPI_Init(&argc, &argv);
    /* okresla liczba uruchomonych procesow */
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    /* okresla numer aktualnego procesu */
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    int a = 0;
    int b = 10;
    int N = size;
    double dx = (double)(b - a) / N;

    double partial_sum = 0.0;
    partial_sum = fun(a + rank * dx);

    MPI_Reduce(&partial_sum, &total, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

    if (rank == 0)
    {
        double result = (total + (fun(a) + fun(b) / 2)) * dx;
        // Finalize
        printf("%f\n", result);
    }

    MPI_Finalize();

    return 0;
}