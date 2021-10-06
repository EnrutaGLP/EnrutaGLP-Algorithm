#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#define NUM_EMPRESAS 5
#define ANHO 2021
#define DIA_INI 13
#define DIA_FIN 15

int dimX;
int dimY;
enum TipoBloqueo {Vertical = 0, Horizontal = 1, EnL = 2};

void generarBloqueo(FILE *arch){
    int tipoBloqueoSeleccionado = rand()%3;
    int x1,x2,x3,y1,y2,y3;
    x1 = rand() % dimX; 
    y1 = rand() % dimY;
    if(tipoBloqueoSeleccionado == Vertical){
        x2 = x1; 
        y2 = ( y1  + rand() % (dimY-1) + 1 ) % dimY;
    } else if (tipoBloqueoSeleccionado == Horizontal){
        y2 = y1; 
        x2 = ( x1  + rand() % (dimX-1) + 1 ) % dimX;
    } else{
        x3 = ( x1  + rand() % (dimX-1) + 1 ) % dimX;; 
        y3 = ( y1  + rand() % (dimY-1) + 1 ) % dimY;
        int decision = rand() % 2; 
        if(decision == 0){
            x2 = x3; 
            y2 = y1;
        } else if (decision == 1){
            x2 = x1; 
            y2 = y3;
        }
    }

    fprintf(arch,"%02d:%02d:%02d-%02d:%02d:%02d,%02d,%02d,%02d,%02d",
            rand()%(DIA_FIN-DIA_INI+1) + DIA_INI,
            rand()%24,
            rand()%60,
            rand()%(DIA_FIN-DIA_INI+1) + DIA_INI,
            rand()%24,
            rand()%60,
            x1,
            y1,
            x2,
            y2
            );
    if(tipoBloqueoSeleccionado == EnL){
        fprintf(arch,",%02d,%02d",x3,y3);
    }
    fprintf(arch,"\n");
    
}
int main(int argc, char *argv[]){
    if(argc<4){
        printf("Debe ingresar los argumentos: numBloqueos dimXCiudad dimYCiudad");
        exit(1);
    }
    int numBloqueos = atoi(argv[1]); 
    dimX = atoi(argv[2]);
    dimY = atoi(argv[3]);
    srand(time(NULL)); 
    //Abrir archivo
    FILE* arch = fopen("bloqueos.txt", "w");
    if(arch == NULL){
        printf("No se pudo abrir el archivo");
        exit(1);
    }

    for(int i=0;i<numBloqueos;i++){
        generarBloqueo(arch);
    }
    

    fclose(arch);
}