/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.Serializable;

/**
 *
 * @author luan
 */
public class Arquivo implements Serializable{
    
    private String nome;
    private byte[] dado;
    private String dir;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getDado() {
        return dado;
    }

    public void setDado(byte[] dado) {
        this.dado = dado;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    
    
    
}
