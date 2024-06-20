
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

        ArvoreBinaria arv = new ArvoreBinaria();
        data.lerCsv(caminhoVerde);

        Q01.questao01(data, sc);


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

class ArvoreHibrida {
    NoHibrido raiz;
    public ArvoreHibrida() { this.raiz = null; }

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
        System.out.print(name + " => raiz ");
        return pesquisar(raiz, name);
    }

    public boolean pesquisar(No no, String name) {
        boolean resp = false;
        if(no == null){
            System.out.println("NAO");
        } else if (no.p.getPersonagemName().equals(name)) {
            System.out.println("SIM");
            resp = true;
        } else if (no.p.getPersonagemName().compareTo(name) > 0) {
            System.out.print("esq ");
            resp = pesquisar(no.esq, name);
        } else if (no.p.getPersonagemName().compareTo(name) < 0) {
            System.out.print("dir ");
            resp = pesquisar(no.dir, name);
        }
        return resp;
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


class NoHibrido{
    public int elemento;
    public NoHibrido esq, dir;
    public No raiz;

    public NoHibrido(int elemento) {
        this.elemento = elemento % 15;
        this.esq = this.dir = null;
        this.raiz = null;
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