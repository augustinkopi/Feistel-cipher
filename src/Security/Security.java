/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author augustin kopi
 */

public class Security{

    /**
     * @param args the command line arguments
     */
    public static int [] swap(int [] word, int [] swapp){
        int index=0;
        int[] tampon=new int[word.length];
         for (int i =0; i < swapp.length; i++) {
           index = swapp[i];
          tampon[i] = word[index];
          
          }
         word =tampon;
         return word;
    }
    public static List<int []> DivisionTableau(int [] Tab1){
        List<int []> list=new ArrayList<>();
        int taille = Tab1.length;
       int lengthK1 = Tab1.length/2;
      int lengthK2 = taille-lengthK1;
       int [] K1= new int[lengthK1];
       int [] K2 = new int[lengthK2];
       
        for (int i = 0; i < lengthK1; i++) {
            
            K1[i]=Tab1[i];
        }
        
        for (int i=lengthK1 ; i < taille; i++) {
            
            K2[i-lengthK1]=Tab1[i];
            
        }
        list.add(K1);
        list.add(K2);
        return list;
    }
    
    public static  int [] Et(int [] Tab1,int [] Tab2){
        int[] tampon=new int[Tab1.length];
        for (int i = 0; i < Tab1.length; i++) {
        tampon[i]= (Tab1[i]==1 && Tab2[i]==1)?1:0;
        }
        return tampon;
    }
    
    public static  int [] Ou(int [] Tab1,int [] Tab2){
        int[] tampon=new int[Tab1.length];
        for (int i = 0; i < Tab1.length; i++) {
        tampon[i]= (Tab1[i]==1||Tab2[i]==1)?1:0;
        }
        return tampon;
    }
    
    public static  int [] Ouexclusive(int [] Tab1,int [] Tab2){
        int[] tampon=new int[Tab1.length];
        for (int i = 0; i < Tab1.length; i++) {
        tampon[i]= (Tab1[i]==Tab2[i])?0:1;
        }
        return tampon;
    }
    public static void main(String[] args) {
       Scanner ecrireposition = new Scanner(System.in);
       int index=0;
       int a = 0;
       int b = 0;
      int [] H = {6,5,2,7,4,1,3,0};
      
        for (int i = 0; i < H.length; i++) {
            System.out.println("L'élément à la position"+i+" est:"+H[i]);
        }
        
        Scanner valeurCle = new  Scanner(System.in);
        int [] K = {0,1,1,0,1,1,0,1};
     
       int[] tampon;
         
         K=swap(K, H);
 
      
         int taille = K.length;
       int lengthK1 = K.length/2;
      int lengthK2 = taille-lengthK1;
       int [] K1= new int[lengthK1];
       int [] K2 = new int[lengthK2];
        
        K1=DivisionTableau(K).get(0);
        K2=DivisionTableau(K).get(1);
       
        System.out.println("Clé avec H appliqué : " +Arrays.toString(K));
        System.out.println("Première clé générée : " +Arrays.toString(K1));
        System.out.println("Deuxième clé générée : " +Arrays.toString(K2));        
      
        
        //Application des  opérations logiques sur les deux portions de la clé
        int[] K11=new int [K1.length];
        int [] K12= new int [K1.length];
       
        K11=Ouexclusive(K1, K2);
        K12=Et(K1, K2);
        
         //Application du decalage 
         tempo=new int[K11.length];
         for (int i = 2; i < K12.length; i++) {
            tempo[i-2]=K11[i];
        }
         tampon[K12.length-1]=K11[1];
         tampon[K11.length-2]=K11[0];
         K11=tampon;
         tampon=new int[K11.length];
         System.out.println("Première clé générée K1 : " +Arrays.toString(K11));
         
         a= K12[K12.length-1];
         
         tampon[0]=a;
         System.out.println("Mot de gauche K1: " +Arrays.toString(K11));
         for (int i = 0; i < K12.length-1; i++) {
            tampon[i+1]=K12[i];
        }
      K12=tampon;
      
       System.out.println("Deuxième clé générée K2 : " +Arrays.toString(K12));
       
       
       
       
       //Cryptage du mot
       
       
       //initialisation valeurs
       
       int [] word = {0,1,1,0,1,1,1,0};
       int [] pi = {4,6,0,2,7,3,1,5};
       
       
       //application de la fonction de permutation et Round 1
        mot=swap(word, pi);
       System.out.println("Mot : " +Arrays.toString(mot));
       int[] Go,G1; //G0 est le premier G0, G1
       int[] Do,D1; 
       int [] p={2,0,1,3};
       
        Left=DivisionTableau(word).get(0);
        Right=DivisionTableau(word).get(1);
        
        
        System.out.println("Mot de gauche Left : " +Arrays.toString(Left));
        System.out.println("Mot de droite Right: " +Arrays.toString(Right));
  
        D1=Ouexclusive(swap(Left, p), K11);
        G1=Ouexclusive(Right, Ou(Left, K11));
        
       System.out.println("Mot de gauche G1: " +Arrays.toString(G1));
        System.out.println("Mot de droite D1: " +Arrays.toString(D1));
      
        //Application d ela permutaion et Round 2
        
        int [] D2,G2;
        
        
        D2 = Ouexclusive((swap(G1, p)), K12);
        G2 = Ouexclusive(D1, Ou(G1, K12));
        System.out.println("Mot de gauche G2: " +Arrays.toString(G2));
        System.out.println("Mot de droite D2: " +Arrays.toString(D2));
        
        //Concatenation de G2 et D2
        
        int [] C = new int [D2.length+G2.length];
        
        System.arraycopy(G2,0, C, 0, G2.length);
        System.arraycopy(D2,0, C, G2.length, D2.length);
        
        System.out.println("C = "+Arrays.toString(C));
        tampon=new int[pi.length];
        for (int i = 0; i < pi.length; i++) {
            int j=0;
            boolean bl=false;
            do{
                if(pi[j]==i){
                    bl=true;
                }
                else j++;
            }while(!bl);
            tampon[i]=j;
        }
        int[] pi1=tampon;
        
        int [] inversePi= new int [pi.length];
        
        System.out.println("pi1 = "+Arrays.toString(pi1));
        
        C= swap(C, pi1);
         System.out.println("Mot Crypté = "+Arrays.toString(C));
        
        
    }
   
}
