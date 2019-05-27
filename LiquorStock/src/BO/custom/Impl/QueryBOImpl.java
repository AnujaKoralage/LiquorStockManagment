package BO.custom.Impl;

import DAO.DAO.custom.DAO.custom.Impl.QueryDAOImpl;
import DAO.DAO.custom.QueryDAO;
import DTO.SearchDTO;
import Entities.Search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryBOImpl implements BO.custom.QueryBO {
    @Override
    public List<SearchDTO> searchLiquorID(int ID) throws SQLException {
        QueryDAO queryDAO = new QueryDAOImpl();
        List<Search> searches = queryDAO.searchLiquorID(ID);
        List<SearchDTO> searchDTOS = new ArrayList<>();
        for (Search search:searches) {
            searchDTOS.add(new SearchDTO(search.getLiquorID(),search.getLiquorName(),search.getSellingPrice(),search.getBuyingPrince()));
        }
        return searchDTOS;
    }

    @Override
    public List<SearchDTO> searchLiquorName(String name) throws SQLException {
        QueryDAO queryDAO = new QueryDAOImpl();
        List<Search> searches = queryDAO.searchLiquorName(name);
        List<SearchDTO> searchDTOS = new ArrayList<>();
        for (Search search:searches) {
            searchDTOS.add(new SearchDTO(search.getLiquorID(),search.getLiquorName(),search.getSellingPrice(),search.getBuyingPrince()));
        }
        return searchDTOS;
    }
}
