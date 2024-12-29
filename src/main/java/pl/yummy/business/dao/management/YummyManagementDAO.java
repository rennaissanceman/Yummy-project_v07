package pl.yummy.business.dao.management;

import java.util.List;

public interface YummyManagementDAO {

    void purge();

    void saveAll(List<?> entities);
}
