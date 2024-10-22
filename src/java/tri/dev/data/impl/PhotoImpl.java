///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package tri.dev.data.impl;
//
//import tri.dev.data.model.Photo;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import tri.dev.data.dao.PhotoDao;
//
//public class PhotoImpl implements PhotoDao {
//    private Connection connection;
//
//    public PhotoImpl(Connection connection) {
//        this.connection = connection;
//    }
//
//    @Override
//    public void insert(Photo photo) {
//        String sql = "INSERT INTO photos (product_id, photo_url) VALUES (?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, photo.getProductId());
//            stmt.setString(2, photo.getPhotoUrl());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<Photo> findByProductId(int productId) {
//        List<Photo> photos = new ArrayList<>();
//        String sql = "SELECT * FROM photos WHERE product_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, productId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Photo photo = new Photo(rs.getInt("id"), rs.getInt("product_id"), rs.getString("photo_url"));
//                photos.add(photo);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return photos;
//    }
//
//    @Override
//    public void deleteByProductId(int productId) {
//        String sql = "DELETE FROM photos WHERE product_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, productId);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
