package com.micro.profession.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HelloMyBatisMoreOp {

	public static void main(String[] args) {
		moreOp();
	}
	
	public static void moreOp() {
		//1. 声明配置文件的目录位置
		String resource = "conf.xml";
		//2. 加载应用配置文件
		InputStream is = HelloMyBatisMoreOp.class.getClassLoader()
				.getResourceAsStream(resource);
		//3. 创建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		//4. 获取Session
		SqlSession session = sessionFactory.openSession(true);
		try {
			//5. 获取操作类
			UserOp userOp = session.getMapper(UserOp.class);
			User user = new User("XiaoMing", "Netease");
			// 插入用户
			userOp.addUser(user);
			System.out.println(user.getId());
			// 查询用户
			user = userOp.getUser(user.getId());
			System.out.println("userId:" + user.getId() + ",userName:"
					+ user.getUserName() + ",corp:" + user.getCorp());
			user.setUserName("LiMing");
			// 更新用户
			userOp.updateUser(user);
			// 删除用户
			userOp.deleteUser(user.getId());
		} finally {
			//7. 关闭Session
			session.close();
		}
	}

}
