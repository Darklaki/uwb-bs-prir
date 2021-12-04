#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include "mpi.h"

// Stany
#define START 1
#define BIEG 2
#define KONIEC 3

#define DYSTANS_M 250
int LADUJ = 1, NIE_LADUJ = 0;
int liczba_procesow;
int nr_procesu;
int ilosc_uczestnikow;
int ilosc_biegnacych;
int tag = 1;
int wyslij[3];
int odbierz[3];
MPI_Status mpi_status;
void Wyslij(int nr_biegacza, int stan, int zostalo_m)
{
    wyslij[0] = nr_biegacza;
    wyslij[1] = stan;
    wyslij[2] = zostalo_m;
    MPI_Send(&wyslij, 3, MPI_INT, 0, tag, MPI_COMM_WORLD);
    sleep(1);
}

void Biegacz()
{
    int stan = START;
    int zostalo_m = DYSTANS_M;
    while (1)
    {
        srand(time(NULL) ^ (nr_procesu << 16));
        if (stan == START)
        {
            Wyslij(nr_procesu, stan, zostalo_m);
            stan = BIEG;
        }
        if (stan == BIEG) {
            zostalo_m -= rand() % 50;
            if (zostalo_m <= 0) {
                Wyslij(nr_procesu, KONIEC, 0);
                return;
            } else {
                Wyslij(nr_procesu, stan, zostalo_m);
            }
        }
    }
}

void Maraton(int liczba_procesow)
{
    int nr_biegacza, stan, pozostale_m_biegacza;
    ilosc_uczestnikow = liczba_procesow - 1;
    ilosc_biegnacych = ilosc_uczestnikow;
    int obecne_miejsce = 1;
    while (ilosc_biegnacych)
    {
        MPI_Recv(&odbierz, 3, MPI_INT, MPI_ANY_SOURCE, tag, MPI_COMM_WORLD, &mpi_status);
        nr_biegacza = odbierz[0];
        stan = odbierz[1];
        pozostale_m_biegacza = odbierz[2];
        if (stan == START)
        {
            printf("Biegacz o numerze %d wystartował\n", nr_biegacza);
        }
        if (stan == BIEG)
        {
            printf("Biegaczowi o numerze %d zostało %dm do mety\n", nr_biegacza, pozostale_m_biegacza);
        }
        if (stan == KONIEC)
        {
            printf("Biegaczo o numerze %d zakończył maraton na %d miejscu\n", nr_biegacza, obecne_miejsce);
            obecne_miejsce += 1;
            ilosc_biegnacych -= 1;
            if (ilosc_biegnacych == 0) {
                printf("Koniec maratonu");
                return;
            }
        }
    }
}

// --- ZADANE 1
int main(int argc, char *argv[])
{
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &nr_procesu);
    MPI_Comm_size(MPI_COMM_WORLD, &liczba_procesow);
    srand(time(NULL));

    if (nr_procesu == 0)
    {
        Maraton(liczba_procesow);
    }
    else
    {
        Biegacz();
    }

    MPI_Finalize();
    return 0;
}