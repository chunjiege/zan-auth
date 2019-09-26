package com.zan.hu.auth.mapper;

import com.zan.hu.auth.Account;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import tk.mybatis.mapper.common.Mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@CacheConfig(cacheNames = "account")
public interface AccountMapper extends Mapper<Account> {

    @Cacheable(key = "#p0")
    Account selectByUsername(String username);

    void insertBatchXml(List<Account> accounts);

    @InsertProvider(type = TutorDynaSqlProvider.class, method = "insertBatch")
    void insertBatchByAnnotations(List<Account> accounts);

    class TutorDynaSqlProvider {
        public String findTutorByIdSql(final int tutorId) {
            return new SQL() {
                {
                    SELECT("tutor_id as tutorId, name, email");
                    FROM("tutors");
                    WHERE("tutor_id=" + tutorId);
                }
            }.toString();
        }

        public String insertBatch(Map map) {
            List<Account> users = (List<Account>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO account= ");
            sb.append("(guid,username, password)");
            sb.append("VALUES ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].guid},#'{'list[{0}].username},#'{'list[{0}].password})");
            for (int i = 0; i < users.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < users.size() - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }
}