
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Data data = new Data();

        String caminhoVerde = "/tmp/characters.csv";
        String caminhoPc = "C:/Users/João/Downloads/characters.csv";

        data.lerCsv(caminhoVerde);

        //Q01.questao01(data, sc);
        //Q02.questao02(data, sc);
        //Q04.questao04(data, sc);
        //Q05.questao05(data, sc);
        Q06.questao06(data, sc);
        sc.close();
    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

}

class Q01 {
    public static void questao01(Data data, Scanner sc) throws Exception {
        ArvoreBinaria arv = new ArvoreBinaria();
        while (sc.hasNext()) {
            String id = sc.nextLine();

            if (Main.isFim(id)) {
                break;
            }

            Personagem personagem = data.getPersonagemById(id);
            arv.inserir(personagem);
        }

        while (sc.hasNext()) {
            String name = sc.nextLine();

            if (Main.isFim(name)) {
                break;
            }

            arv.pesquisar(name);
        }
    }
}

class Q02 {
    public static void questao02(Data data, Scanner sc) throws Exception {
        ArvoreHibrida arv = new ArvoreHibrida();
        inicializaArv(arv);
        inserirPersonagensNaArvore(arv, data);
        while (sc.hasNext()) {
            String id = sc.nextLine();

            if (Main.isFim(id)) {
                break;
            }

            Personagem personagem = data.getPersonagemById(id);
            arv.inserir(personagem);
        }

        while (sc.hasNext()) {
            String name = sc.nextLine();

            if (Main.isFim(name)) {
                break;
            }

            arv.pesquisar(name);
        }
    }

    private static void inicializaArv(ArvoreHibrida arv) throws Exception {
        int values[] = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
        for (int i = 0; i < values.length; i++) {
            arv.inserir(values[i]);
        }

    }

    public static void inserirPersonagensNaArvore(ArvoreHibrida arv, Data data) throws Exception {
        String[] ids = {
                "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
                "1413e1b3-2903-4a47-a2d5-e8abc5ce8014",
                "ca3827f0-375a-4891-aaa5-f5e8a5bad225",
                "36bfefd0-e0bb-4d11-be98-d1ef6117a77a",
                "20354d7a-e4fe-47af-8ff6-187bca92f3f9",
                "57fe29d4-312a-4711-bd9a-c320253d9176",
                "b415c867-1cff-455e-b194-748662ac2cca",
                "5a4c95db-947d-4914-a631-41e8d466328e",
                "861c4cde-2f0f-4796-8d8f-9492e74b2573",
                "2cfd2d4b-5d1e-4dc5-8837-37a97c7e2f2f",
                "41cd0bbe-a943-431b-9bde-bb2cad3491a1",
                "2a0615de-8aa4-41e7-9504-dd875f5f3f01",
                "11b5ca88-64ad-41a4-9f36-317b66c290af",
                "eaea5eb3-48a3-41c6-9ea5-c695299bab16",
                "0d8ea37f-35c4-4c7d-9dd2-8ccd96b0a2b3",
                "b0620914-858d-46fc-8e6d-033c565e138b",
                "6b59be3f-e527-422d-959d-79fcdb3b24eb",
                "fed624df-56d9-495e-9ad4-ea77000957e8",
                "d58e7249-19d1-40bd-a43f-1da0497fe8aa",
                "3a0fe4df-2e40-4541-8d7f-13586f0b9294",
                "6afb1960-febd-418d-b604-e50c1b59459b",
                "efa802c8-ae18-4ae1-a524-49df21d05939",
                "933787c2-51e3-4eac-8a85-ab332cac0456",
                "94c993f6-a443-4408-b908-6e78e223e8ac",
                "00434cd3-fcc7-44c7-8f98-7368415b4206",
                "7614cf6e-689e-47ac-a976-b1e9997637e9",
                "d59691a4-f830-4eb0-a819-a0fb00b7e80f"
        };

        for (String id : ids) {
            Personagem personagem = data.getPersonagemById(id);
            if (personagem != null) {
                arv.inserir(personagem);
            } else {
                System.out.println("Personagem com ID " + id + " não encontrado.");
            }
        }
    }
}

class Q04 {
    public static void questao04(Data data, Scanner sc) throws Exception {
        ArvoreAlvinegra arv = new ArvoreAlvinegra();
        //inserirPersonagensNaArvore(arv, data);
        while (sc.hasNext()) {
            String id = sc.nextLine();

            if (Main.isFim(id)) {
                break;
            }

            Personagem personagem = data.getPersonagemById(id);
            arv.inserir(personagem);
        }

        while (sc.hasNext()) {
            String name = sc.nextLine();

            if (Main.isFim(name)) {
                break;
            }

            arv.pesquisar(name);

        }
    }

    public static void inserirPersonagensNaArvore(ArvoreAlvinegra arv, Data data) throws Exception {
        String[] ids = {
                "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
                "1413e1b3-2903-4a47-a2d5-e8abc5ce8014",
                "ca3827f0-375a-4891-aaa5-f5e8a5bad225",
                "36bfefd0-e0bb-4d11-be98-d1ef6117a77a",
                "20354d7a-e4fe-47af-8ff6-187bca92f3f9",
                "57fe29d4-312a-4711-bd9a-c320253d9176",
                "b415c867-1cff-455e-b194-748662ac2cca",
                "5a4c95db-947d-4914-a631-41e8d466328e",
                "861c4cde-2f0f-4796-8d8f-9492e74b2573",
                "2cfd2d4b-5d1e-4dc5-8837-37a97c7e2f2f",
                "41cd0bbe-a943-431b-9bde-bb2cad3491a1",
                "2a0615de-8aa4-41e7-9504-dd875f5f3f01",
                "11b5ca88-64ad-41a4-9f36-317b66c290af",
                "eaea5eb3-48a3-41c6-9ea5-c695299bab16",
                "0d8ea37f-35c4-4c7d-9dd2-8ccd96b0a2b3",
                "b0620914-858d-46fc-8e6d-033c565e138b",
                "6b59be3f-e527-422d-959d-79fcdb3b24eb",
                "fed624df-56d9-495e-9ad4-ea77000957e8",
                "d58e7249-19d1-40bd-a43f-1da0497fe8aa",
                "3a0fe4df-2e40-4541-8d7f-13586f0b9294",
                "6afb1960-febd-418d-b604-e50c1b59459b",
                "efa802c8-ae18-4ae1-a524-49df21d05939",
                "933787c2-51e3-4eac-8a85-ab332cac0456",
                "94c993f6-a443-4408-b908-6e78e223e8ac",
                "00434cd3-fcc7-44c7-8f98-7368415b4206",
                "7614cf6e-689e-47ac-a976-b1e9997637e9",
                "d59691a4-f830-4eb0-a819-a0fb00b7e80f"
        };

        for (String id : ids) {
            Personagem personagem = data.getPersonagemById(id);
            if (personagem != null) {
                arv.inserir(personagem);
            } else {
                System.out.println("Personagem com ID " + id + " não encontrado.");
            }
        }
    }
}

class Q05 {
    public static void questao05(Data data, Scanner sc) throws Exception {
        HashDireta hashDireta = new HashDireta();
//        inserirPersonagens(hashDireta, data);
        while (sc.hasNext()) {
            String id = sc.nextLine();

            if (Main.isFim(id)) {
                break;
            }

            Personagem personagem = data.getPersonagemById(id);
            hashDireta.inserir(personagem);
        }

        while (sc.hasNext()) {
            String name = sc.nextLine();

            if (Main.isFim(name)) {
                break;
            }
            hashDireta.pesquisar(name);

        }
    }

    public static void inserirPersonagens(HashDireta hashDireta, Data data) throws Exception {
        String[] ids = {
                "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
                "1413e1b3-2903-4a47-a2d5-e8abc5ce8014",
                "ca3827f0-375a-4891-aaa5-f5e8a5bad225",
                "36bfefd0-e0bb-4d11-be98-d1ef6117a77a",
                "20354d7a-e4fe-47af-8ff6-187bca92f3f9",
                "57fe29d4-312a-4711-bd9a-c320253d9176",
                "b415c867-1cff-455e-b194-748662ac2cca",
                "5a4c95db-947d-4914-a631-41e8d466328e",
                "861c4cde-2f0f-4796-8d8f-9492e74b2573",
                "2cfd2d4b-5d1e-4dc5-8837-37a97c7e2f2f",
                "41cd0bbe-a943-431b-9bde-bb2cad3491a1",
                "2a0615de-8aa4-41e7-9504-dd875f5f3f01",
                "11b5ca88-64ad-41a4-9f36-317b66c290af",
                "eaea5eb3-48a3-41c6-9ea5-c695299bab16",
                "0d8ea37f-35c4-4c7d-9dd2-8ccd96b0a2b3",
                "b0620914-858d-46fc-8e6d-033c565e138b",
                "6b59be3f-e527-422d-959d-79fcdb3b24eb",
                "fed624df-56d9-495e-9ad4-ea77000957e8",
                "d58e7249-19d1-40bd-a43f-1da0497fe8aa",
                "3a0fe4df-2e40-4541-8d7f-13586f0b9294",
                "6afb1960-febd-418d-b604-e50c1b59459b",
                "efa802c8-ae18-4ae1-a524-49df21d05939",
                "933787c2-51e3-4eac-8a85-ab332cac0456",
                "94c993f6-a443-4408-b908-6e78e223e8ac",
                "00434cd3-fcc7-44c7-8f98-7368415b4206",
                "7614cf6e-689e-47ac-a976-b1e9997637e9",
                "d59691a4-f830-4eb0-a819-a0fb00b7e80f"
        };

        for (String id : ids) {
            Personagem personagem = data.getPersonagemById(id);
            if (personagem != null) {
                hashDireta.inserir(personagem);
            } else {
                System.out.println("Personagem com ID " + id + " não encontrado.");
            }
        }
    }
}

class Q06 {
    public static void questao06(Data data, Scanner sc) throws Exception {
        HashRehash hashRehash = new HashRehash();
//        inserirPersonagens(hashRehash, data);
        while (sc.hasNext()) {
            String id = sc.nextLine();

            if (Main.isFim(id)) {
                break;
            }

            Personagem personagem = data.getPersonagemById(id);
            hashRehash.inserir(personagem);
        }

        while (sc.hasNext()) {
            String name = sc.nextLine();

            if (Main.isFim(name)) {
                break;
            }
            hashRehash.pesquisar(name);

        }
    }

    public static void inserirPersonagens(HashRehash hashDireta, Data data) throws Exception {
        String[] ids = {
                "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
                "1413e1b3-2903-4a47-a2d5-e8abc5ce8014",
                "ca3827f0-375a-4891-aaa5-f5e8a5bad225",
                "36bfefd0-e0bb-4d11-be98-d1ef6117a77a",
                "20354d7a-e4fe-47af-8ff6-187bca92f3f9",
                "57fe29d4-312a-4711-bd9a-c320253d9176",
                "b415c867-1cff-455e-b194-748662ac2cca",
                "5a4c95db-947d-4914-a631-41e8d466328e",
                "861c4cde-2f0f-4796-8d8f-9492e74b2573",
                "2cfd2d4b-5d1e-4dc5-8837-37a97c7e2f2f",
                "41cd0bbe-a943-431b-9bde-bb2cad3491a1",
                "2a0615de-8aa4-41e7-9504-dd875f5f3f01",
                "11b5ca88-64ad-41a4-9f36-317b66c290af",
                "eaea5eb3-48a3-41c6-9ea5-c695299bab16",
                "0d8ea37f-35c4-4c7d-9dd2-8ccd96b0a2b3",
                "b0620914-858d-46fc-8e6d-033c565e138b",
                "6b59be3f-e527-422d-959d-79fcdb3b24eb",
                "fed624df-56d9-495e-9ad4-ea77000957e8",
                "d58e7249-19d1-40bd-a43f-1da0497fe8aa",
                "3a0fe4df-2e40-4541-8d7f-13586f0b9294",
                "6afb1960-febd-418d-b604-e50c1b59459b",
                "efa802c8-ae18-4ae1-a524-49df21d05939",
                "933787c2-51e3-4eac-8a85-ab332cac0456",
                "94c993f6-a443-4408-b908-6e78e223e8ac",
                "00434cd3-fcc7-44c7-8f98-7368415b4206",
                "7614cf6e-689e-47ac-a976-b1e9997637e9",
                "d59691a4-f830-4eb0-a819-a0fb00b7e80f"
        };

        for (String id : ids) {
            Personagem personagem = data.getPersonagemById(id);
            if (personagem != null) {
                hashDireta.inserir(personagem);
            } else {
                System.out.println("Personagem com ID " + id + " não encontrado.");
            }
        }
    }
}

class HashDireta {
    public Personagem[] personagens;
    public int tam;
    Reserva reserva;

    public HashDireta() {
        tam = 21;
        personagens = new Personagem[tam];

        for (int i = 0; i < tam; i++) {
            personagens[i] = null;
        }
        reserva = new Reserva(9);
    }

    public int hash(String name) {
        return Data.ASCII(name) % tam;
    }

    public void inserir(Personagem personagem) {
        int pos = hash(personagem.getPersonagemName());
        if (personagens[pos] == null) {
            personagens[pos] = personagem;
        } else {
            for (int i = 0; i < reserva.area.length; i++) {
                if (reserva.area[i] == null) {
                    reserva.area[i] = personagem;
                    i = reserva.area.length;
                }
            }
        }
    }

    public void pesquisar(String name) {
        int pos = hash(name);

        if (personagens[pos] != null && personagens[pos].getPersonagemName().equals(name)) {
            System.out.println(name + " (Posicao: " + pos + ") SIM");
        } else {
            boolean resp = false;
            for (Personagem personagem : reserva.area) {
                if (personagem.getPersonagemName().equals(name)) {
                    resp = true;
                    System.out.println(name + " (Posicao: " + pos + ") SIM");
                }
            }

            if (!resp) {
                System.out.println(name + " NAO");
            }
        }
    }

    public void mostrar() {
        for (Personagem personagem : personagens) {
            System.out.println(personagem.getPersonagemName());
        }

        for (Personagem personagem : reserva.area) {
            System.out.println(personagem.getPersonagemName());
        }
    }
}

class Reserva {
    Personagem[] area;
    int tam;

    public Reserva(int tam) {
        this.tam = tam;
        area = new Personagem[tam];

        for (int i = 0; i < area.length; i++) {
            area[i] = null;
        }
    }
}

class HashRehash {
    public Personagem[] personagens;
    public int tam;

    public HashRehash() {
        tam = 21;
        personagens = new Personagem[tam];

        for (int i = 0; i < tam; i++) {
            personagens[i] = null;
        }

    }

    public int hash(String name) {
        return Data.ASCII(name) % tam;
    }

    public int rehash(String name) {
        return (Data.ASCII(name) + 1) % tam;
    }

    public void inserir(Personagem personagem) {
        int pos = hash(personagem.getPersonagemName());
        if (personagens[pos] == null) {
            personagens[pos] = personagem;
        } else {
            if (personagens[rehash(personagem.getPersonagemName())] == null) {
                personagens[rehash(personagem.getPersonagemName())] = personagem;
            }
        }
    }

    public void pesquisar(String name) {
        int pos = hash(name);

        if (personagens[pos] != null && personagens[pos].getPersonagemName().equals(name)) {
            System.out.println(name + " (pos: " + pos + ") SIM");
        } else if (personagens[rehash(name)] != null && personagens[rehash(name)].getPersonagemName().equals(name)) {
            System.out.println(name + " (pos: " + rehash(name) + ") SIM");
        } else {
            System.out.println(name + " NAO");
        }
    }
}

class ArvoreAlvinegra {
    private NoAN raiz;

    public ArvoreAlvinegra() {
        raiz = null;
    }


    public void inserir(Personagem p) throws Exception {
        if (raiz == null) {
            raiz = new NoAN(p);
        } else if (raiz.dir == null && raiz.esq == null) {
            if (compare(p, raiz.p) < 0) {
                raiz.esq = new NoAN(p);
            } else {
                raiz.dir = new NoAN(p);
            }
        } else if (raiz.esq == null) {
            if (compare(p, raiz.p) < 0) {
                raiz.esq = new NoAN(p);
            } else if (compare(p, raiz.dir.p) < 0) {
                raiz.esq = new NoAN(raiz.p);
                raiz.p = p;
            } else {
                raiz.esq = new NoAN(raiz.p);
                raiz.p = raiz.dir.p;
                raiz.dir.p = p;
            }

            raiz.esq.cor = raiz.dir.cor = false;
        } else if (raiz.dir == null) {
            if (compare(p, raiz.p) > 0) {
                raiz.dir = new NoAN(p);
            } else if (compare(p, raiz.esq.p) > 0) {
                raiz.dir = new NoAN(raiz.p);
                raiz.p = p;
            } else {
                raiz.dir = new NoAN(raiz.p);
                raiz.p = raiz.esq.p;
                raiz.esq.p = p;
            }
        } else {
            inserir(p, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void inserir(Personagem p, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        if (i == null) {
            if (compare(p, pai.p) < 0) {
                i = pai.esq = new NoAN(p);
            } else {
                i = pai.dir = new NoAN(p);
            }

            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            isTipo4(bisavo, avo, pai, i);
            if (compare(p, i.p) < 0) {
                inserir(p, avo, pai, i, i.esq);
            } else if (compare(p, i.p) > 0) {
                inserir(p, avo, pai, i, i.dir);
            } else {
                System.out.println("Elemento repetido");
            }
        }
    }

    private void isTipo4(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;

            if (i == raiz) {
                i.cor = false;
            } else if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        }
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        if (pai.cor == true) {
            if (compare(pai.p, avo.p) > 0) {
                if (compare(i.p, pai.p) > 0) {
                    avo = rotEsq(avo);
                } else {
                    avo.dir = rotDir(pai);
                    avo = rotEsq(avo);
                }
            } else {
                if (compare(i.p, pai.p) < 0) {
                    avo = rotDir(avo);
                } else {
                    avo.esq = rotEsq(pai);
                    avo = rotDir(avo);
                }
            }
        }
        if (bisavo == null) {
            raiz = avo;
        } else if (compare(avo.p, bisavo.p) < 0) {
            bisavo.esq = avo;
        } else {
            bisavo.dir = avo;
        }

        avo.cor = false;
        if (avo.esq != null) avo.esq.cor = true;
        if (avo.dir != null) avo.dir.cor = true;
    }

    private NoAN rotDir(NoAN i) {
        NoAN tmp = i.esq;
        i.esq = tmp.dir;
        tmp.dir = i;
        tmp.cor = i.cor;
        i.cor = true;
        return tmp;
    }

    private NoAN rotEsq(NoAN i) {
        NoAN tmp = i.dir;
        i.dir = tmp.esq;
        tmp.esq = i;
        tmp.cor = i.cor;
        i.cor = true;
        return tmp;
    }

    public boolean pesquisar(String name) {
        System.out.print(name + " => raiz");
        return pesquisar(raiz, name);
    }

    private boolean pesquisar(NoAN no, String name) {
        if (no == null) {
            System.out.println(" NAO");
            return false;
        }
        if (no.p.getPersonagemName().equals(name)) {
            System.out.println(" SIM");
            return true;
        } else if (compare(name, no.p) < 0) {
            System.out.print(" esq");
            return pesquisar(no.esq, name);
        } else {
            System.out.print(" dir");
            return pesquisar(no.dir, name);
        }
    }

    private int compare(Personagem p1, Personagem p2) {
        return p1.getPersonagemName().compareTo(p2.getPersonagemName());
    }

    private int compare(String name, Personagem p) {
        return name.compareTo(p.getPersonagemName());
    }
}

class ArvoreHibrida {
    NoHibrido raiz;

    public ArvoreHibrida() {
        this.raiz = null;
    }

    public void inserir(int n) throws Exception {
        raiz = inserir(raiz, n);
    }

    private NoHibrido inserir(NoHibrido no, int n) throws Exception {
        if (no == null) {
            no = new NoHibrido(n);
        } else if (n > no.elemento) {
            no.dir = inserir(no.dir, n);
        } else if (n < no.elemento) {
            no.esq = inserir(no.esq, n);
        }

        return no;
    }

    public void inserir(Personagem p) throws Exception {
        raiz = inserir(raiz, p);
    }

    private NoHibrido inserir(NoHibrido no, Personagem p) throws Exception {
        if (no == null) {
            throw new Exception("Erro ao inserir personagem");
        } else if (no.elemento == (p.getYearOfBirth() % 15)) {
            no.arvoreBinaria.inserir(p);
        } else if (no.elemento > (p.getYearOfBirth() % 15)) {
            no.esq = inserir(no.esq, p);
        } else if (no.elemento < (p.getYearOfBirth() % 15)) {
            no.dir = inserir(no.dir, p);
        }
        return no;
    }


    public void pesquisar(String name) {
        boolean resp;
        System.out.print(name + " => raiz");
        resp = pesquisar(raiz, name);
        if (resp) {
            System.out.println(" SIM");
        } else {
            System.out.println(" NAO");
        }
    }

    private boolean pesquisar(NoHibrido no, String name) {
        boolean resp = false;
        if (no != null) {
            if (no.arvoreBinaria.raiz != null) {
                resp = no.arvoreBinaria.pesquisar(name);
            }
            if (!resp) {
                System.out.print(" ESQ");
                resp = pesquisar(no.esq, name);
            }
            if (!resp) {
                System.out.print(" DIR");
                resp = pesquisar(no.dir, name);
            }
        }
        return resp;
    }
}

class ArvoreBinaria {
    public No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(Personagem p) throws Exception {
        raiz = inserir(raiz, p);
    }

    private No inserir(No no, Personagem p) throws Exception {
        if (no == null) {
            no = new No(p);
        } else if (no.p.getPersonagemName().compareTo(p.getPersonagemName()) > 0) {
            no.esq = inserir(no.esq, p);
        } else if (no.p.getPersonagemName().compareTo(p.getPersonagemName()) < 0) {
            no.dir = inserir(no.dir, p);
        } else {
            throw new Exception("Nao eh permitido inserir valores iguais");
        }

        return no;
    }

    public boolean pesquisar(String name) {
        //System.out.print(name + " => raiz ");
        return pesquisar(raiz, name);
    }

    private boolean pesquisar(No no, String name) {
        if (no == null) {
            return false;
        }
        if (no.p.getPersonagemName().equals(name)) {
            return true;
        } else if (no.p.getPersonagemName().compareTo(name) > 0) {
            System.out.print("->esq");
            return pesquisar(no.esq, name);
        } else {
            System.out.print("->dir");
            return pesquisar(no.dir, name);
        }
    }

    public void mostrarPre() {
        mostrarPre(raiz);
    }

    private void mostrarPre(No no) {
        if (no != null) {
            System.out.print(no.p);
            mostrarPre(no.esq);
            mostrarPre(no.dir);
        }
    }

}

class NoAN {
    public Personagem p;
    public NoAN esq, dir;
    public boolean cor;

    NoAN(Personagem p) {
        this.p = p;
        esq = dir = null;
        cor = true;
    }

}

class NoHibrido {
    public int elemento;
    public NoHibrido esq, dir;
    public ArvoreBinaria arvoreBinaria;

    public NoHibrido(int elemento) {
        this.elemento = elemento % 15;
        this.esq = this.dir = null;
        arvoreBinaria = new ArvoreBinaria();
    }
}

class No {
    public Personagem p;
    public No esq, dir;

    public No(Personagem p, No esq, No dir) {
        this.p = p;
        this.esq = esq;
        this.dir = dir;
    }

    public No(Personagem p) {
        this(p, null, null);
    }

}


class Data {
    final private List<Personagem> personagens;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public Data() {
        super();
        this.personagens = new ArrayList<>();
    }

    public List<Personagem> getPersonagens() {
        return personagens;
    }

    public Personagem getPersonagemById(String idString) {
        UUID id = UUID.fromString(idString);

        for (Personagem p : personagens) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void lerCsv(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;

            br.readLine();
            while ((linha = br.readLine()) != null) {

                try {
                    Personagem p = instanciaPersonagemPeloCsv(linha);
                    personagens.add(p);
                } catch (Exception e) {
                    System.err.println("Erro ao processar a linha: " + linha);
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Personagem instanciaPersonagemPeloCsv(String linha) throws ParseException {
        String[] values = splitString(linha, ';');
        UUID id = UUID.fromString(values[0]);
        String name = values[1];

        String alternateNamesRaw = replaceAllSimple(values[2], "[", "");
        alternateNamesRaw = replaceAllSimple(alternateNamesRaw, "]", "");
        alternateNamesRaw = replaceAllSimple(alternateNamesRaw, "\"", "");

        ArrayList<String> alternateNames = new ArrayList<>(Arrays.asList(splitString(alternateNamesRaw, ',')));

        String house = values[3];
        String ancestry = values[4];
        String species = values[5];
        String patronus = values[6];
        boolean hogwartsStaff = values[7].equals("VERDADEIRO");
        String hogwartsStudent = values[8];
        String actorName = values[9];
        boolean alive = values[10].equalsIgnoreCase("VERDADEIRO");
        Date dateOfBirth = values[12].isEmpty() ? null : sdf.parse(values[12]);
        int yearOfBirth = Integer.parseInt(values[13]);
        String eyeColour = values[14];
        String gender = values[15];
        String hairColour = values[16];
        boolean wizard = values[17].equalsIgnoreCase("VERDADEIRO");
        int contador = 0;

        return new Personagem(id.toString(), name, alternateNames, house, ancestry, species, patronus, hogwartsStaff,
                hogwartsStudent, actorName, alive, dateOfBirth, yearOfBirth, eyeColour, gender, hairColour, wizard, contador);
    }

    public static String[] splitString(String input, char delimiter) {
        List<String> result = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (ch == delimiter) {
                result.add(buffer.toString());
                buffer = new StringBuilder();
            } else {
                buffer.append(ch);
            }
        }
        if (buffer.length() > 0) {
            result.add(buffer.toString());
        }
        return result.toArray(new String[0]);
    }

    public static String replaceAllSimple(String input, String toReplace, String replacement) {
        StringBuilder result = new StringBuilder();
        int start = 0;
        int end = input.indexOf(toReplace);
        while (end != -1) {
            result.append(input, start, end).append(replacement);
            start = end + toReplace.length();
            end = input.indexOf(toReplace, start);
        }
        result.append(input.substring(start));
        return result.toString();
    }

    public static int compareStrings(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        int minLen = Math.min(length1, length2);

        for (int i = 0; i < minLen; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 != c2) {
                return c1 - c2;
            }
        }

        return length1 - length2;
    }

    public static void printSortedList(ArrayList<Personagem> personagens) {
        for (Personagem p : personagens) {
            System.out.println(p);
        }
    }

    public static int ASCII(String s) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            sum += c;
        }
        return sum;
    }
}

class Personagem {

    private UUID _id;
    private int _yearOfBirth;
    private String _name;
    private ArrayList<String> _alternateNames;
    private String _house;
    private String _ancestry;
    private String _species;
    private String _patronus;
    private String _hogwartsStudent;
    private String _actorName;
    private String _eyeColour;
    private String _gender;
    private String _hairColour;
    private Date _dateOfBirth;
    private boolean _hogwartsStaff;
    private boolean _alive;
    private boolean _wizard;
    public Personagem prox;
    private int contador;

    public Personagem() {
        _id = null;
        _yearOfBirth = 0;
        _name = "";
        _alternateNames = new ArrayList<>();
        _house = "";
        _ancestry = "";
        _species = "";
        _patronus = "";
        _hogwartsStaff = false;
        _hogwartsStudent = "";
        _actorName = "";
        _alive = false;
        _eyeColour = "";
        _gender = "";
        _hairColour = "";
        _dateOfBirth = new Date(0);
        _wizard = false;
        this.prox = null;
        this.contador = 0;
    }

    public Personagem(String id, String name, ArrayList<String> alternateNames, String house, String ancestry,
                      String species, String patronus, boolean hogwartsStaff, String hogwartsStudent, String actorName,
                      boolean alive, Date dateOfBirth, int yearOfBirth, String eyeColour, String gender, String hairColour,
                      boolean wizard, int contador) {

        _id = UUID.fromString(id);
        _yearOfBirth = yearOfBirth;
        _name = name;
        _alternateNames = alternateNames;
        _house = house;
        _ancestry = ancestry;
        _species = species;
        _patronus = patronus;
        _hogwartsStudent = hogwartsStudent;
        _actorName = actorName;
        _eyeColour = eyeColour;
        _gender = gender;
        _hairColour = hairColour;
        _dateOfBirth = dateOfBirth;
        _hogwartsStaff = hogwartsStaff;
        _alive = alive;
        _wizard = wizard;
        this.prox = null;
        this.contador = contador;
    }

    public Personagem(String id, String name, ArrayList<String> alternateNames, String house, String ancestry,
                      String species, String patronus, boolean hogwartsStaff, String hogwartsStudent, String actorName,
                      boolean alive, Date dateOfBirth, int yearOfBirth, String eyeColour, String gender, String hairColour,
                      boolean wizard) {

        _id = UUID.fromString(id);
        _yearOfBirth = yearOfBirth;
        _name = name;
        _alternateNames = alternateNames;
        _house = house;
        _ancestry = ancestry;
        _species = species;
        _patronus = patronus;
        _hogwartsStudent = hogwartsStudent;
        _actorName = actorName;
        _eyeColour = eyeColour;
        _gender = gender;
        _hairColour = hairColour;
        _dateOfBirth = dateOfBirth;
        _hogwartsStaff = hogwartsStaff;
        _alive = alive;
        _wizard = wizard;
        this.prox = null;
    }

    public static Personagem getPersonagem() {
        return new Personagem();
    }

    public UUID getId() {
        return _id;
    }

    public String getPersonagemName() {
        return _name;
    }

    public String getPersonagemDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(_dateOfBirth);
    }

    public String getHairColour() {
        return _hairColour;
    }

    public int getYearOfBirth() {
        return _yearOfBirth;
    }

    public String getActorName() {
        return _actorName;
    }

    public String getHouse() {
        return _house;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = (_dateOfBirth != null) ? sdf.format(_dateOfBirth) : "";

        String alternateNamesFormatted = Data.replaceAllSimple(
                Data.replaceAllSimple(Data.replaceAllSimple(this._alternateNames.toString(), "[", ""), "]", ""), "'",
                "");

        return String.format(
                "[%d ## %s ## %s ## {%s} ## %s ## %s ## %s ## %s ## %b ## %b ## %s ## %b ## %s ## %d ## %s ## %s ## %s ## %b]",
                this.contador, this._id, this._name, alternateNamesFormatted, this._house, this._ancestry, this._species,
                this._patronus, this._hogwartsStaff, this._hogwartsStudent.equalsIgnoreCase("VERDADEIRO"),
                this._actorName, this._alive, formattedDate, this._yearOfBirth, this._eyeColour, this._gender,
                this._hairColour, this._wizard);
    }
}