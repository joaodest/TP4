#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX_NAME_LEN 100
#define MAX_HOUSE_LEN 50
#define MAX_SPECIES_LEN 50
#define MAX_PATRONUS_LEN 50
#define MAX_ACTOR_NAME_LEN 100
#define MAX_EYE_COLOUR_LEN 30
#define MAX_GENDER_LEN 10
#define MAX_BOOLEAN_RESPONSE_LEN 10
#define MAX_HAIR_COLOUR_LEN 30
#define MAX_ALTERNATE_NAMES_LEN 200
#define MAX_DATE_LEN 11

typedef struct {
    char id[37]; // UUID string
    char name[MAX_NAME_LEN];
    char alternateNames[MAX_ALTERNATE_NAMES_LEN];
    char house[MAX_HOUSE_LEN];
    char ancestry[MAX_NAME_LEN];
    char species[MAX_SPECIES_LEN];
    char patronus[MAX_PATRONUS_LEN];
    char hogwartsStaff[MAX_BOOLEAN_RESPONSE_LEN];
    char hogwartsStudent[MAX_BOOLEAN_RESPONSE_LEN];
    char actorName[MAX_ACTOR_NAME_LEN];
    char alive[MAX_BOOLEAN_RESPONSE_LEN];
    char dateOfBirth[MAX_DATE_LEN];
    int yearOfBirth;
    char eyeColour[MAX_EYE_COLOUR_LEN];
    char gender[MAX_GENDER_LEN];
    char hairColour[MAX_HAIR_COLOUR_LEN];
    char wizard[MAX_BOOLEAN_RESPONSE_LEN];
} Personagem;

typedef struct No {
    Personagem p;
    struct No *esq;
    struct No *dir;
    int nivel;
} No;

No *criarNo(Personagem p) {
    No *no = (No *) malloc(sizeof(No));
    no->p = p;
    no->esq = NULL;
    no->dir = NULL;
    no->nivel = 1;
    return (no);
}

typedef struct {
    No *raiz;
} ArvoreAVL;

ArvoreAVL *criarArv() {
    ArvoreAVL *i = (ArvoreAVL *) malloc(sizeof(ArvoreAVL));
    i->raiz = NULL;

    return i;
}


int max(int a, int b) {
    return (a > b) ? a : b;
}

int getAltura(No *i) {
    if (i == NULL) {
        return 0;
    }
    return i->nivel;
}

int getFator(No *i) {
    if (i == NULL) {
        return 0;
    }
    return getAltura(i->dir) - getAltura(i->esq);
}

No *rotacaoDir(No *i) {
    No *tmp = i->esq;
    i->esq = tmp->dir;
    tmp->dir = i;

    i->nivel = max(getAltura(i->esq), getAltura(i->dir)) + 1;
    tmp->nivel = max(getAltura(tmp->esq), getAltura(tmp->dir)) + 1;

    return tmp;
}

No *rotacaoEsq(No *i) {
    No *tmp = i->dir;
    i->dir = tmp->esq;
    tmp->esq = i;

    i->nivel = max(getAltura(i->esq), getAltura(i->dir)) + 1;
    tmp->nivel = max(getAltura(tmp->esq), getAltura(tmp->dir)) + 1;

    return tmp;
}


No* inserirAVL(No *no, Personagem p) {
    if (no == NULL) {
        return criarNo(p);
    } else if (strcmp(p.name, no->p.name) < 0) {
        no->esq = inserirAVL(no->esq, p);
    } else if (strcmp(p.name, no->p.name) > 0) {
        no->dir = inserirAVL(no->dir, p);
    } else {
        printf("Erro ao inserir personagem\n");
        return no;
    }

    no->nivel = max(getAltura(no->esq), getAltura(no->dir)) + 1;

    int balance = getFator(no);

    if (balance < -1 && strcmp(p.name, no->esq->p.name) < 0)
        return rotacaoDir(no);

    if (balance > 1 && strcmp(p.name, no->dir->p.name) > 0)
        return rotacaoEsq(no);

    if (balance < -1 && strcmp(p.name, no->esq->p.name) > 0) {
        no->esq = rotacaoEsq(no->esq);
        return rotacaoDir(no);
    }

    if (balance > 1 && strcmp(p.name, no->dir->p.name) < 0) {
        no->dir = rotacaoDir(no->dir);
        return rotacaoEsq(no);
    }

    return no;
}

int comparisons = 0;
int swaps = 0;

char *caminhoVerde = "/tmp/characters.csv";
char *caminhoPC = "C:/Users/joaod/Downloads/characters.csv";

void assignField(char *field, const char *value, size_t max_len) {
    if (strlen(value) < max_len) {
        strcpy(field, value);
    } else {
        strncpy(field, value, max_len);
        field[max_len - 1] = '\0';
    }
}

void cleanAlternateNames(char *src) {
    char *dst = src;
    while (*src) {
        if (*src != '[' && *src != ']' && *src != '\'' && *src != '\"') {
            *dst++ = *src;
        }
        src++;
    }
    *dst = '\0';
}

char *my_strsep(char **stringp, const char *delim) {
    if (*stringp == NULL) {
        return NULL;
    }

    char *start = *stringp;
    char *p;

    p = (delim[0] == '\0') ? NULL : strpbrk(start, delim);
    if (p == NULL) {
        *stringp = NULL;
    } else {
        *p = '\0';
        *stringp = p + 1;
    }

    return start;
}

void lerCsv(const char *path, Personagem **personagens, int *count) {
    FILE *file = fopen(path, "r");
    if (!file) {
        printf("File could not be opened.\n");
        return;
    }
    *personagens = malloc(sizeof(Personagem) * 405);

    *count = 0;
    char line[1024];
    char *rest;

    fgets(line, sizeof(line), file);
    while (fgets(line, sizeof(line), file)) {
        Personagem p = {0};
        char *token;
        int index = 0;
        rest = line;


        while ((token = my_strsep(&rest, ";")) && index < 18) {
            if (token == NULL || strlen(token) == 0) {
                token = "";
            }

            switch (index) {
                case 0:
                    assignField(p.id, token, sizeof(p.id));
                    break;
                case 1:
                    assignField(p.name, token, sizeof(p.name));
                    break;
                case 2:
                    assignField(p.alternateNames, token, sizeof(p.alternateNames));
                    break;
                case 3:
                    assignField(p.house, token, sizeof(p.house));
                    break;
                case 4:
                    assignField(p.ancestry, token, sizeof(p.ancestry));
                    break;
                case 5:
                    assignField(p.species, token, sizeof(p.species));
                    break;
                case 6:
                    assignField(p.patronus, token, sizeof(p.patronus));
                    break;
                case 7:
                    assignField(p.hogwartsStaff, (strcmp(token, "VERDADEIRO") == 0) ? "true" : "false",
                                sizeof(p.hogwartsStaff));
                    break;
                case 8:
                    assignField(p.hogwartsStudent, (strcmp(token, "VERDADEIRO") == 0) ? "true" : "false",
                                sizeof(p.hogwartsStudent));
                    break;
                case 9:
                    assignField(p.actorName, token, sizeof(p.actorName));
                    break;
                case 10:
                    assignField(p.alive, (strcmp(token, "VERDADEIRO") == 0) ? "true" : "false", sizeof(p.alive));
                    break;
                case 11:
                    break;
                case 12:
                    assignField(p.dateOfBirth, token, sizeof(p.dateOfBirth));
                    break;
                case 13:
                    p.yearOfBirth = atoi(token);
                    break;
                case 14:
                    assignField(p.eyeColour, token, sizeof(p.eyeColour));
                    break;
                case 15:
                    assignField(p.gender, token, sizeof(p.gender));
                    break;
                case 16:
                    assignField(p.hairColour, token, sizeof(p.hairColour));
                    break;
                case 17:
                    assignField(p.wizard, (strcmp(token, "VERDADEIRO") == 0) ? "true" : "false", sizeof(p.wizard));
                    break;
            }
            index++;
        }
        cleanAlternateNames(p.alternateNames);
        (*personagens)[*count] = p;
        (*count)++;
    }

    fclose(file);
}

Personagem *getPersonagemById(Personagem *personagens, int count, const char *id) {
    for (int i = 0; i < count; i++) {
        if (strcmp(personagens[i].id, id) == 0) {
            return &personagens[i];
        }
    }
    return NULL;
}

bool isFim(char str[]){
    bool c = true;

    if(str[0] == 'F' && str[1] == 'I' && str[2] == 'M'){
        c = false;
    }

    return c;
}

void readIDsAndInsert(Personagem *personagens, int totalPersonagensCount, ArvoreAVL *arvore) {
    char id[100];

    scanf("%99[^\n]%*c", id);
    id[strcspn(id, "\r")] = '\0';

    while(isFim(id)){
        Personagem *p = getPersonagemById(personagens, totalPersonagensCount, id);
        if (p != NULL) {
            arvore->raiz = inserirAVL(arvore->raiz, *p);
        }
        scanf("%99[^\n]%*c", id);
        id[strcspn(id, "\r")] = '\0';
    }
}

void searchAVLNode(No *no, char *name) {
    if (no == NULL) {
        printf(" NAO\n");
    } else if (strcmp(name, no->p.name) < 0) {
        printf(" esq");
        searchAVLNode(no->esq, name);
    } else if (strcmp(name, no->p.name) > 0) {
        printf(" dir");
        searchAVLNode(no->dir, name);
    } else {
        printf(" SIM\n");
    }
}


void searchNamesInAVL(ArvoreAVL *arvore) {
    char nome[MAX_NAME_LEN];

    scanf("%99[^\n]%*c", nome);
    nome[strcspn(nome, "\r")] = '\0';

    while(isFim(nome)){
        printf("%s => raiz", nome);
        searchAVLNode(arvore->raiz, nome);
        scanf("%99[^\n]%*c", nome);
        nome[strcspn(nome, "\r")] = '\0';
    }
}


Personagem *initializeData(char *path, int *count) {
    Personagem *personagens;
    lerCsv(path, &personagens, count);
    return personagens;
}

// void inserirPersonagensNaArvore(ArvoreAVL *arv, Personagem *personagens, int totalPersonagensCount) {
//     const char *ids[] = {
//         "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
//         "1413e1b3-2903-4a47-a2d5-e8abc5ce8014",
//         "ca3827f0-375a-4891-aaa5-f5e8a5bad225",
//         "36bfefd0-e0bb-4d11-be98-d1ef6117a77a",
//         "20354d7a-e4fe-47af-8ff6-187bca92f3f9",
//         "57fe29d4-312a-4711-bd9a-c320253d9176",
//         "b415c867-1cff-455e-b194-748662ac2cca",
//         "5a4c95db-947d-4914-a631-41e8d466328e",
//         "861c4cde-2f0f-4796-8d8f-9492e74b2573",
//         "2cfd2d4b-5d1e-4dc5-8837-37a97c7e2f2f",
//         "41cd0bbe-a943-431b-9bde-bb2cad3491a1",
//         "2a0615de-8aa4-41e7-9504-dd875f5f3f01",
//         "11b5ca88-64ad-41a4-9f36-317b66c290af",
//         "eaea5eb3-48a3-41c6-9ea5-c695299bab16",
//         "0d8ea37f-35c4-4c7d-9dd2-8ccd96b0a2b3",
//         "b0620914-858d-46fc-8e6d-033c565e138b",
//         "6b59be3f-e527-422d-959d-79fcdb3b24eb",
//         "fed624df-56d9-495e-9ad4-ea77000957e8",
//         "d58e7249-19d1-40bd-a43f-1da0497fe8aa",
//         "3a0fe4df-2e40-4541-8d7f-13586f0b9294",
//         "6afb1960-febd-418d-b604-e50c1b59459b",
//         "efa802c8-ae18-4ae1-a524-49df21d05939",
//         "933787c2-51e3-4eac-8a85-ab332cac0456",
//         "94c993f6-a443-4408-b908-6e78e223e8ac",
//         "00434cd3-fcc7-44c7-8f98-7368415b4206",
//         "7614cf6e-689e-47ac-a976-b1e9997637e9",
//         "d59691a4-f830-4eb0-a819-a0fb00b7e80f"
//     };
//
//     int numIds = sizeof(ids) / sizeof(ids[0]);
//
//     for (int i = 0; i < numIds; i++) {
//         Personagem *personagem = getPersonagemById(personagens, totalPersonagensCount, ids[i]);
//         if (personagem != NULL) {
//
//             arv->raiz = inserirAVL(arv->raiz, *personagem);
//         } else {
//             printf("Personagem com ID %s não encontrado.\n", ids[i]);
//         }
//     }
// }

void clear(Personagem *allPersonagens) {
    free(allPersonagens);
}

int main() {
    char *caminhoVerde = "/tmp/characters.csv";
    char *caminhoPc = "../characters.csv";

    int totalPersonagensCount = 0;
    Personagem *allPersonagens = initializeData(caminhoVerde, &totalPersonagensCount);

    ArvoreAVL *arvore = criarArv();

    readIDsAndInsert(allPersonagens, totalPersonagensCount, arvore);
    //inserirPersonagensNaArvore(arvore, allPersonagens, totalPersonagensCount);
    searchNamesInAVL(arvore);


    clear(allPersonagens);
    return 0;
}
