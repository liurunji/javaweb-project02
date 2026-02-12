package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /*
    原始分页方法
     */
   /* @Select("select count(*) from emp e left join  dept d on e.dept_id = d.id")
    public Long getTotal();

    @Select("select e.*, d.name deptName from emp e left join  dept d on e.dept_id = d.id order by e.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);*/

    /*
    使用pagehelper分页
    只需写查询，不需要分页参数
     */
    //@Select("select e.*, d.name deptName from emp e left join  dept d on e.dept_id = d.id order by e.update_time desc ")
   // public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    //带条件的查询
    public List<Emp> list(EmpQueryParam empQueryParam);


   /* @Select("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")*/
    //@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")  //获取到生成的主键  主键返回  ，这方法被调用后会返回给emp中的id
    void insert(Emp emp);

    /*
    根据ID（批量）删除
     */
    void deleteByIds(List<Integer> ids);

    /*
    根据ID查询对应员工信息
     */
    Emp getInfoById(Integer id);

    /*
    根据员工id更新员工信息
     */
    void updateById(Emp emp);

    /*
    查询所有班主任
     */
    List<Emp> findMasterEmp();

    /*
    根据用户名和密码查询用户
     */
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp findEmpByUsernameAndPassword(Emp emp);
}
