package com.gl.test.beetlsql;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;

import java.util.Date;

/**
 * @author gantrylau
 * @since 2018/5/3
 */
public class Main {
    public static void main(String[] args) {
        ConnectionSource source = ConnectionSourceHelper.getSimple("com.mysql.cj.jdbc.Driver", "jdbc:mysql://192.168.33.10:3306/test-beetlsql?characterEncoding=utf-8&useSSL=false", "root", "123456");
        DBStyle mysql = new MySqlStyle();

        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});


        User user = new User();
        user.setAge(19);
        user.setName("xiandafu");
        user.setUserName("xxx");
        user.setCreateDate(new Date());
        sqlManager.insert(user);

        sqlManager.insert(user);

        User u1  = sqlManager.unique(User.class, 2);
        User u2  = sqlManager.unique(User.class, 3);
        System.out.println(u1.getName());


    }
}
