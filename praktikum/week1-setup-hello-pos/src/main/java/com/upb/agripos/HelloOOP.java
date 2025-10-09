package main.java.com.upb.agripos;


class Mahasiswa{
    String Nama;
    Integer NIM;
    void sapa(String Nama,Integer Nim){
    this.Nama = Nama;
    this.NIM = Nim;

        IO.println("hello world, i'am " + Nama + "-" + Nim);
    }
}
public class HelloOOP {
    public static void main(String[] args) {
        Mahasiswa mahasiswa = new Mahasiswa();

        mahasiswa.sapa("vigian Agus Isnaeni", 240202888 );
    }
}
