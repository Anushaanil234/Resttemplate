package Vendingmachine.dao;

import Vendingmachine.dto.InventoryDTO;
import Vendingmachine.model.Inventry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class InventoryDAOImp implements InventoryDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    @Override
    public List<Inventry> findAll() {
        String SQL = "SELECT * FROM productlist";
        return getNamedParameterJdbcTemplate().query(SQL, new BeanPropertyRowMapper<>(Inventry.class));
    }

    @Override
    public List<Inventry> findById(int productId) {
        SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource().addValue("productId", productId);
        return getNamedParameterJdbcTemplate().query("SELECT * FROM productlist WHERE LOWER(productId) = LOWER(:productId)", mapSqlParameterSource, new BeanPropertyRowMapper<>(Inventry.class));
    }

    @Override
    public List<Inventry> findByInventryCount(int productInventoryCount) {
        SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("productInventoryCount", productInventoryCount);
        return getNamedParameterJdbcTemplate().query("SELECT * FROM productlist WHERE productInventoryCount > :productInventoryCount", mapSqlParameterSource, new BeanPropertyRowMapper<>(Inventry.class));
    }

    @Override
    public int save(InventoryDTO e) {
        String sql = "INSERT INTO productlist (name, productInventoryCount, productprice) VALUES (:name, :productInventoryCount, :productprice)";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("name", e.getName());
        paramSource.addValue("productInventoryCount", e.getProductInventoryCount());
        paramSource.addValue("productprice", e.getProductPrice());

        int update = getNamedParameterJdbcTemplate().update(sql, paramSource);
        if (update == 1) {
            System.out.println("Product is added..");
        }
        return update;
    }

    @Override
    public void updatedStock(int productId, int productInventoryCount) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("productid", productId).addValue("productInventoryCount", productInventoryCount);
        getNamedParameterJdbcTemplate().update("UPDATE productlist SET productInventoryCount = :productInventoryCount WHERE productid = :productid", sqlParameterSource);
    }

    @Override
    public int deleteById(int productId) {
        return jdbcTemplate.update("DELETE FROM productlist WHERE productid=?", productId);
    }

    @Override
    public int update(Inventry e, int productId) {
        return jdbcTemplate.update("UPDATE productlist SET name = ?, productInventoryCount = ?, productprice = ? WHERE productid = ?", e.getName(), e.getProductInventoryCount(), e.getProductPrice(), productId);
    }
}
