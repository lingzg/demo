package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class JdbcTest {

//	@Test
    public void test1() {
        JdbcDao dao = new JdbcDao();
        List<Map<String,Object>> list = dao.query("select table_name from information_schema.`TABLES` where TABLE_SCHEMA='electricai'");
        for(Map<String,Object> map : list){
            String table = (String) map.get("table_name");
            int count = dao.count("select count(1) from electricai."+table);
            if(count>0){
                System.out.println("select * from "+table+"; -- "+count);
                System.out.println("delete from "+table+";");
//                System.out.print(table+",");
            }
        }
        dao.close();
    }
	
	@Test
    public void test2() {
		JdbcDao dao = new JdbcDao();
		String sql = "select t.firmId,t.type,t.percentage,staggerTimeStart,staggerTimeEnd,rowIndex,planGroup,f.firmName from tb_pxfirm_production_plan t,"
				+ "tb_pxfirm_info f where t.firmId=f.firmId and planInfoId=1 order by planGroup,rowIndex";
		List<Map<String,Object>> list = dao.query(sql);
		Map<Object, List<Map<String, Object>>> planList = list.stream().collect(Collectors.groupingBy(x -> x.get("plangroup")));
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		planList.forEach((k,v) -> {
			String type = v.get(0).get("type").toString();
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("type", type);
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			v.stream().collect(Collectors.groupingBy(x -> x.get("rowindex"))).forEach((k2, v2) -> {
				Map<String, Object> map = v2.get(0);
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("percentage", map.get("percentage"));
				row.put("staggerTimeStart", map.get("staggertimestart"));
				row.put("staggerTimeEnd", map.get("staggertimeend"));
				List firms = v2.stream().map(x -> new HashMap<String, Object>(){{put("firmId", x.get("firmid"));put("firmName", x.get("firmname"));}}).collect(Collectors.toList());
				row.put("firms", firms);
				rows.add(row);
			});
			item.put("rows", rows);
			result.add(item);
		});
		System.out.println(planList);
		dao.close();
	}
}
