package models;

import java.sql.SQLException;

import database.Database;

public class Client extends Database {
    // Constructor untuk Connect ke Database
    public Client() throws ClassNotFoundException, SQLException {
        super();
    }

    // Create
    public void insert(String nama, String alamat, int uang) throws SQLException {
        String sql = String.format("INSERT INTO dataclient (nama, alamat, uang) VALUE ('%s', '%s', %d)", nama, alamat,
                uang);
        this.setQuery(sql);
        this.execute();
    }

    // Read
    public void getAll() throws SQLException {
        String sql = "SELECT * FROM dataclient";
        this.setQuery(sql);
        this.take();
    }
    
    // Update
    public void update(int id, String nama, String alamat, int uang) throws SQLException {
        String sql = String.format("UPDATE dataclient SET nama = '%s', alamat = '%s', uang = %d WHERE id = %d",
                nama, alamat, uang, id);
        this.setQuery(sql);
        this.execute();
    }

    // Delete
    public void delete(int id) throws SQLException {
        String sql = String.format("DELETE FROM dataclient WHERE id = %d", id);
        this.setQuery(sql);
        this.execute();
    }

    // Validation untuk mencegah redudansi data
    public boolean check(String nama) throws SQLException {
        getAll();
        while (this.value.next()) {
            if (this.value.getString("nama").equals(nama)) {
                return false;
            }
        }
        return true;
    }

    public String[][] show() throws SQLException {
        String[][] data = new String[this.len()][4];
        getAll();
        this.take();
        int i = 0;
        while (this.value.next()) {
            data[i][0] =  Integer.toString(this.value.getInt("id"));
            data[i][1] = this.value.getString("nama");
            data[i][2] = this.value.getString("alamat");
            data[i][3] = Integer.toString(this.value.getInt("uang"));
            i++;
        }
        return data;
    }
    
    public int len() throws SQLException {
        getAll();
        this.take();
        int i = 0;
        while (this.value.next()) {
            i++;
        }
        return i;
    }
}
