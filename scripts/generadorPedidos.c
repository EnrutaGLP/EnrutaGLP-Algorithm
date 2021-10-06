#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#define NUM_EMPRESAS 5
#define MES 9
#define ANHO 2021
#define DIA_INI 13
#define DIA_FIN 17
#define CANT_MIN 1
#define CANT_MAX 5
const char NOMBRES_EMPRESAS[NUM_EMPRESAS][20] = {
    "IKEA",
    "CANON",
    "LEGO",
    "PEPSI",
    "YAHOO"
};
int dimX = 70;
int dimY = 50;
void generarPedido(FILE *arch){
    fprintf(arch,"%s,%d,%d,%02d/%02d/%d,%02d:00,%d\n",
            NOMBRES_EMPRESAS[rand()%(NUM_EMPRESAS)],
            rand()%dimX,
            rand()%dimY,
            rand()%(DIA_FIN-DIA_INI+1) + DIA_INI,
            MES,
            ANHO,
            rand()%24,
            rand()%(CANT_MAX-CANT_MIN+1) + CANT_MIN
            );
}
int main(int argc, char *argv[]){
    if(argc<3){
        printf("Debe ingresar los argumentos: numPedidos numMuestra");
        exit(1);
    }
    int numPedidos = atoi(argv[1]); 
    int numMuestra = atoi(argv[2]);
    char fileName[50];
    srand(time(NULL) + numPedidos + numMuestra); 
    sprintf(fileName,"../parametros/muestra%d_%d.txt",numPedidos,numMuestra);
    //Abrir archivo
    FILE* arch = fopen(fileName, "w");
    if(arch == NULL){
        printf("No se pudo abrir el archivo");
        exit(1);
    }

    for(int i=0;i<numPedidos;i++){
        generarPedido(arch);
    }
    

    fclose(arch);
}
