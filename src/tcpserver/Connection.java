// https://www.devmedia.com.br/java-socket-transferencia-de-arquivos-pela-rede/32107
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author luan
 */
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
                        
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			                 // an echo server

			String data = in.readUTF();      // primeiro data é a opcão
                        
                        System.out.println("Cliente  "+ clientSocket.getInetAddress().getHostAddress() + " diz " + data );
                        
                        
                        if(data.equals("1")){
                            try {
                                File dir = new File(in.readUTF());
                                dir.mkdirs();
                                } 
                            catch (Exception ex) {
                                        System.out.println(ex);
                                    }                            
                        }
                        else if(data.equals("2")){
                            try {
                                File dir = new File(in.readUTF());
                                String nome = dir.getName();
                                if(dir.exists()){
                                    
                                    dir.delete();
                                    data = "Arquivo (" + nome + ") deletado!\n";
                                }
                                else
                                    data = "Arquivo " + nome +" não encontrado!\n";
                            } 
                            catch (Exception ex) {
                                        System.out.println(ex);
                            }                            
                        }
                        else if(data.equals("3")){
                            try {
                                File dir = new File(in.readUTF());
                                for(File f: dir.listFiles()){
                                        data = data.concat("\n" + f);
                                }
                            } 
                            catch (Exception ex) {
                                        System.out.println(ex);
                            } 
                        }
                        else if(data.equals("4")){
                            byte[] arqByte = new byte[clientSocket.getReceiveBufferSize()];
                            BufferedInputStream buf = new BufferedInputStream(in);
                            buf.read(arqByte);
                        }
                        else{
			
                        
                            data =  "\n"
                                    + "1 - Criar pasta.\n"
                                    + "2 - Excluir pasta.\n"
                                    + "3 - Enviar arquivo.\n"
                                    + "4 - Excluir arquivo.\n";
                        }
                        out.writeUTF(data);
                        
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}