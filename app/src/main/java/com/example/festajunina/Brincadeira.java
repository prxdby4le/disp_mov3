package com.example.festajunina;

import android.os.Parcel;
import android.os.Parcelable;

public class Brincadeira implements Parcelable{
    private int id = 0;
    private String nome;
    private String descricao;
    private float preco;
    private String imageUri;

    public Brincadeira() {}

    public Brincadeira(int id, String nome, String descricao, float preco, String imageUri) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imageUri = imageUri;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public float getPreco() { return preco; }
    public void setPreco(float preco) { this.preco = preco; }

    public String getImageUri() { return imageUri; }
    public void setImageUri(String imageUri) { this.imageUri = imageUri; }

    protected Brincadeira(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        descricao = in.readString();
        preco = in.readFloat();
        imageUri = in.readString();
    }

    public static final Parcelable.Creator<Brincadeira> CREATOR = new Parcelable.Creator<Brincadeira>() {
        @Override
        public Brincadeira createFromParcel(Parcel in) {
            return new Brincadeira(in);
        }

        @Override
        public Brincadeira[] newArray(int size) {
            return new Brincadeira[size];
        }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeFloat(preco);
        dest.writeString(imageUri);
    }
}
