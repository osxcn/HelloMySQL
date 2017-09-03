package com.micro.profession.mybatis;

public interface GetUserInfo {

	/**
	 * 根据用户id获取用户信息
	 * @param id 用户id
	 * @return 用户信息
	 */
	public User getUser(int id);
	
	/**
	 * 新增用户
	 * @param user 用户信息
	 */
	public void addUser(User user);
	
	/**
	 * 修改用户信息
	 * @param user 用户信息
	 */
	public void updateUser(User user);

	/**
	 * 删除用户
	 * @param user 用户信息
	 */
	public void deleteUser(User user);
}
