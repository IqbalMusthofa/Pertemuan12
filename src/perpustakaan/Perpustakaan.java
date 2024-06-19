/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perpustakaan;

import java.sql.*;
import java.util.Scanner;

/**
 * Iqbal Musthofa
 * A11.2022.14458
 * @author user
 */
public class Perpustakaan {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/perpustakaan";
    static final String USER = "root";
    static final String PASS = "";
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        layout();
    }
    
    public static void layout(){
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("=====  MENU  =====");
            System.out.println("=== 1. Insert  ===");
            System.out.println("=== 2. Show    ===");
            System.out.println("=== 3. Update  ===");
            System.out.println("=== 4. Delete  ===");
            System.out.println("=== 5. Keluar  ===");
            System.out.print("Pilih Menu : ");
            int pilih = input.nextInt();
            input.nextLine();
        
            switch(pilih){
                case 1:
                    insert();
                    break;
                case 2:
                    show();
                    break;
                case 3:
                    update();
                    break;
                case 4: 
                    delete();
                    break;
                case 5:
                    System.exit(0);
                default:
               System.out.println("Salah input ");
            }
        }
    }
    
    
    
    public static void insert(){
        Scanner input = new Scanner(System.in);
        //variabel input dan inputkan
        System.out.print("Judul Buku: ");
        String jdl_buku = input.nextLine();
        System.out.print("Tahun Terbit Buku: ");
        int thn_terbit = input.nextInt();
        System.out.print("Stok Buku: ");
        int stok = input.nextInt();
        System.out.print("Penulis Buku: ");
        int penulis = input.nextInt();
        System.out.println("");
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            
            String sql = "INSERT INTO buku (`judul buku`, `tahun terbit`, `stok`, `penulis`) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, jdl_buku);
            ps.setInt(2, thn_terbit);
            ps.setInt(3, stok);
            ps.setInt(4, penulis);
            
            ps.executeUpdate();
			
            ps.close();
            conn.close();
        }
        catch(Exception e) {
		e.printStackTrace();
	}
    }
    
    public static void show(){
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
			
            rs = stmt.executeQuery("SELECT * FROM buku");
            int i = 1;
            while(rs.next()){
                System.out.println();
                System.out.println("Data ke-"+i);
		System.out.println("Judul Buku: " + rs.getString("judul buku"));
		System.out.println("Tahun Terbit Buku: "+rs.getString("tahun terbit"));
		System.out.println("Stok Buku: "+rs.getString("stok"));
		System.out.println("Penulis Buku: "+rs.getString("penulis"));
                System.out.println("");
		i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }//finally {
            //try {
                //if (rs != null) rs.close();
                //if (stmt != null) stmt.close();
                //if (conn != null) conn.close();
            //}catch (SQLException se){
                //se.printStackTrace();
            //}
        //}
    }
    
    public static void update(){
        Scanner input = new Scanner(System.in);
        
        System.out.println("");
        System.out.print("Data Mana Yang Mau diUpdate : ");
        int id = input.nextInt();
        
        input.nextLine();
        
        System.out.print("Judul Buku : ");
        String jdl_buku = input.nextLine();
        System.out.print("Tahun Terbit Buku: ");
        int thn_terbit = input.nextInt();
        System.out.print("Stok Buku: ");
        int stok = input.nextInt();
        System.out.print("Penulis Buku: ");
        int penulis = input.nextInt();
        System.out.println("");
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "UPDATE buku SET `judul buku` = ?, `tahun terbit` = ?, `stok` = ?, `penulis` = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, jdl_buku);
            ps.setInt(2, thn_terbit);
            ps.setInt(3, stok);
            ps.setInt(4, penulis);
            ps.setInt(5, id);
            
            ps.executeUpdate();

            ps.close();
            conn.close();
            
        }catch (Exception e){
             e.printStackTrace();
        }
    }
    
    public static void delete(){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Data Mana Yang Mau diHapus : ");
        int id = input.nextInt();
        input.nextLine();
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "DELETE FROM buku WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Data Terhapus");
            }else {
                System.out.println("Data Kosong");
            }

            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
