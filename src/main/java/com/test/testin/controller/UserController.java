package com.test.testin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.github.pagehelper.PageHelper;
import com.test.testin.mapper.UserMapper;
import com.test.testin.models.ResultModel;
import com.test.testin.models.UserInfo;
import com.test.testin.util.ResultTools;
 
@RestController
public class UserController {
 
	@Autowired
	private UserMapper userMapper;
 
	@RequestMapping(value = { "/selectUserByAll" }, method = RequestMethod.GET)
	public ResultModel selectUserByAll() {
		try {
			List<UserInfo> userLs = userMapper.selectUserByAll();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", userLs);
			return ResultTools.result(0, "", map);
		} catch (Exception e) {
			return ResultTools.result(404, e.getMessage(), null);
		}
	}
 
	@RequestMapping(value = { "/selectUserById" }, method = RequestMethod.GET)
	public ResultModel selectUserById(Integer id) {
		try {
			if (null == id) {
				return ResultTools.result(1001, "", null);
			}
			UserInfo user = userMapper.selectUserById(id);
			if (null == user) {
				return ResultTools.result(1002, "", null);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", user);
			return ResultTools.result(0, "", map);
		} catch (Exception e) {
			return ResultTools.result(404, e.getMessage(), null);
		}
	}
 
	@RequestMapping(value = { "/selectUserByName" }, method = RequestMethod.GET)
	public ResultModel selectUserByName(String name, Integer page, Integer pageSize) {
		try {
			if (null == name) {
				return ResultTools.result(1001, "", null);
			}
			PageHelper.startPage(page, pageSize);// 分页配置
			List<UserInfo> userLs = userMapper.selectUserByName(name);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", userLs);
			return ResultTools.result(0, "", map);
		} catch (Exception e) {
			return ResultTools.result(404, e.getMessage(), null);
		}
	}
 
	@RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
	public ResultModel addUser(UserInfo user) {
		try {
			int code = userMapper.addUser(user);
			if (1 == code) {
				return ResultTools.result(0, "", null);
			}
			return ResultTools.result(404, "失败", null);
		} catch (Exception e) {
			return ResultTools.result(404, e.getMessage(), null);
		}
	}
 
	@RequestMapping(value = { "/updateUser" }, method = RequestMethod.POST)
	public ResultModel updateUser(UserInfo user) {
		try {
			int code = userMapper.updateUser(user);
			if (1 == code) {
				return ResultTools.result(0, "", null);
			}
			return ResultTools.result(404, "失败", null);
		} catch (Exception e) {
			return ResultTools.result(404, e.getMessage(), null);
		}
	}
 
	@RequestMapping(value = { "/deleteUser" }, method = RequestMethod.POST)
	public ResultModel deleteUser(String id) {
		try {
			int code = userMapper.deleteUser(Integer.parseInt(id));
			if (1 == code) {
				return ResultTools.result(0, "", null);
			}
			return ResultTools.result(404, "失败", null);
		} catch (Exception e) {
			return ResultTools.result(404, e.getMessage(), null);
		}
	}
 
}

