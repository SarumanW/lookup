package com.lookup.dao.rowmappers;

import com.lookup.domain.AnalyticVM;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.lookup.keys.Key.ANALYTIC_MESSAGE_COUNT;
import static com.lookup.keys.Key.ANALYTIC_SENT_DATE;

@Component
public class AnalyticVMRowMapper implements RowMapper<AnalyticVM> {
    @Override
    public AnalyticVM mapRow(ResultSet resultSet, int i) throws SQLException {
        AnalyticVM vm = new AnalyticVM();

        vm.setSentDate(resultSet.getString(ANALYTIC_SENT_DATE));
        vm.setMessageCount(resultSet.getInt(ANALYTIC_MESSAGE_COUNT));

        return vm;
    }
}
