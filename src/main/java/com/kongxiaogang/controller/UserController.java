package com.kongxiaogang.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kongxiaogang.model.MenuModel;
import com.kongxiaogang.model.UserModel;
import com.kongxiaogang.model.auth.AuthorityMenu;
import com.kongxiaogang.model.auth.PermissionMenu;
import com.kongxiaogang.model.auth.UserAuthModel;
import com.kongxiaogang.service.MenuService;
import com.kongxiaogang.service.UserService;
import com.kongxiaogang.service.vo.ServiceVO;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger _log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	
	/**
	 * <pre>initlogin(显示登陆页面)   
	 * 创建人：孔小刚 xiaogangkong@galaxyinternet.com    
	 * 创建时间：2017年5月12日 下午5:21:21    
	 * 修改人：孔小刚 xiaogangkong@galaxyinternet.com     
	 * 修改时间：2017年5月12日 下午5:21:21    
	 * 修改备注： 
	 * @param request
	 * @param response
	 * @param mav
	 * @return
	 * @throws Exception</pre>
	 */
	@RequestMapping(value = "/initlogin.do")
	public ModelAndView initlogin(HttpServletRequest request,HttpServletResponse response,ModelAndView mav)
			throws Exception {
		mav.setViewName("/user/login");
		return mav;
	}
	
	@RequestMapping(value = "/login.do")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String username = request.getParameter("userName");
		String passwd = request.getParameter("userPwd");
		String login_captcha = request.getParameter("captcha");
		String captcha = (String) request.getSession().getAttribute("randomString");
		_log.debug("username:" + username+"captcha"+captcha+"login_captcha"+login_captcha);
		//校验验证码
		/*if(null==captcha||null==login_captcha||!captcha.toLowerCase().equals(login_captcha.toLowerCase())) {
			redirectAttributes.addFlashAttribute("errMessage", "验证码错误！");
			mav.setViewName("redirect:/user/initlogin.do");
			return mav;
		} */
		//校验用户名和密码
		if(null != username &&!"".equals(username) && null != passwd&&!"".equals(passwd)){
			try {
				UserModel login = new UserModel();
				login.setUserName(username);
				login.setUserPwd(passwd);
				ServiceVO<UserModel> result = userService.loginOnSlave(login);
				if(result.isRunResult()) { //登录成功
					//session中userAuth初始化
					UserAuthModel userAuth = new UserAuthModel(result.getResultObject().getUserId(),result.getResultObject().getUserName(),result.getResultObject().getRealName());
					//获取用户菜单的查询条件user
					UserModel user = new UserModel();
					user.setUserId(result.getResultObject().getUserId());
					List<AuthorityMenu> authorityMenus=new ArrayList<AuthorityMenu>();//用户存储登录后左侧菜单树行结构
					List<PermissionMenu> permissionMenus=new ArrayList<PermissionMenu>(); 
					if("admin".equals(username)) { //管理员登录
						List<MenuModel> allmenuList = menuService.getAllMenusList(new MenuModel());
						Map<Integer,AuthorityMenu> allLevelTwoAuthorityMap = new LinkedHashMap<Integer,AuthorityMenu>();
						Map<Integer,AuthorityMenu> allLevelThreeAuthorityMap = new LinkedHashMap<Integer,AuthorityMenu>();
						for(MenuModel menu : allmenuList){
							if("1".equals(menu.getLevel().toString())) {//一级菜单
								AuthorityMenu authorityMenu=new AuthorityMenu(null,menu.getMenuId(), menu.getMenuName(), "", menu.getPageUrl(),menu.getParentId(),new ArrayList<AuthorityMenu>());
								authorityMenus.add(authorityMenu);
							}
							if("2".equals(menu.getLevel().toString())) {//二级菜单
								AuthorityMenu authorityMenu=new AuthorityMenu(null,menu.getMenuId(), menu.getMenuName(), "", menu.getPageUrl(),menu.getParentId(),new ArrayList<AuthorityMenu>());
								allLevelTwoAuthorityMap.put(authorityMenu.getMenuId(),authorityMenu);
							}
							if("3".equals(menu.getLevel().toString())) {//三级菜单
								AuthorityMenu authorityMenu=new AuthorityMenu(null,menu.getMenuId(), menu.getMenuName(), "", menu.getPageUrl(),menu.getParentId());
								allLevelThreeAuthorityMap.put(authorityMenu.getMenuId(),authorityMenu);
							}
						}
						//将所有三级菜单放在二级菜单的children
						for(Map.Entry<Integer,AuthorityMenu> authority : allLevelThreeAuthorityMap.entrySet()) {
							AuthorityMenu levelThreeAuthority = authority.getValue();
							allLevelTwoAuthorityMap.get(levelThreeAuthority.getParentId()).getChildrens().add(levelThreeAuthority);
						}
						System.out.println(allLevelThreeAuthorityMap.size());
						//将所有二级菜单放在一级菜单的children
						for(Map.Entry<Integer,AuthorityMenu> authority : allLevelTwoAuthorityMap.entrySet()) {
							AuthorityMenu levelTwoAuthority = authority.getValue();
							for(AuthorityMenu levelOneAuthority : authorityMenus) {
								if(levelTwoAuthority.getParentId().toString().equals(levelOneAuthority.getMenuId().toString())) {
									levelOneAuthority.getChildrens().add(levelTwoAuthority);
								}
							}
						}
						//权限列表赋值
						for(AuthorityMenu levelOneMenu : authorityMenus) {
							PermissionMenu firstAuthMenu = new PermissionMenu(levelOneMenu.getMenuId(),levelOneMenu.getMenuName(),levelOneMenu.getMenuName(),levelOneMenu.getMenuPageurl());
							permissionMenus.add(firstAuthMenu);
							for(AuthorityMenu levelTwoMenu : levelOneMenu.getChildrens()) {
								PermissionMenu secondAuthMenu = new PermissionMenu(levelOneMenu.getMenuId(),levelOneMenu.getMenuName(),levelTwoMenu.getMenuId(),levelTwoMenu.getMenuName(),levelTwoMenu.getMenuName(),levelTwoMenu.getMenuPageurl());
								permissionMenus.add(secondAuthMenu);
								for(AuthorityMenu levelThreeMenu : levelTwoMenu.getChildrens()) {
									PermissionMenu thirdAuthMenu = new PermissionMenu(levelOneMenu.getMenuId(),levelOneMenu.getMenuName(),levelTwoMenu.getMenuId(),levelTwoMenu.getMenuName(),levelThreeMenu.getMenuId(),levelThreeMenu.getMenuName(),levelThreeMenu.getMenuName(),levelThreeMenu.getMenuPageurl());
									permissionMenus.add(thirdAuthMenu);
								}
							}
						}
					} else {
						List<Map<String, Object>> roleAuthorities = userService.getUserAndRoleAndPerList(user);//获取该用户的所有菜单
						Map<Integer,AuthorityMenu> allLevelTwoAuthorityMap = new LinkedHashMap<Integer,AuthorityMenu>();
						Map<Integer,AuthorityMenu> allLevelThreeAuthorityMap = new LinkedHashMap<Integer,AuthorityMenu>();
						for(Map<String,Object> authority : roleAuthorities){
							if("1".equals(authority.get("menuLevel").toString())) {//一级菜单
								AuthorityMenu authorityMenu=new AuthorityMenu((Integer)authority.get("perId"),(Integer)authority.get("menuId"), (String)authority.get("menuName"), "", (String)authority.get("menuPageurl"),(Integer)authority.get("menuParentid"),new ArrayList<AuthorityMenu>() );
								authorityMenus.add(authorityMenu);
							}
							if("2".equals(authority.get("menuLevel").toString())) {//二级菜单
								AuthorityMenu authorityMenu=new AuthorityMenu((Integer)authority.get("perId"),(Integer)authority.get("menuId"), (String)authority.get("menuName"), "", (String)authority.get("menuPageurl"),(Integer)authority.get("menuParentid"),new ArrayList<AuthorityMenu>() );
								allLevelTwoAuthorityMap.put(authorityMenu.getMenuId(),authorityMenu);
							}
							if("3".equals(authority.get("menuLevel").toString())) {//三级菜单
								AuthorityMenu authorityMenu=new AuthorityMenu((Integer)authority.get("perId"),(Integer)authority.get("menuId"), (String)authority.get("menuName"), "", (String)authority.get("menuPageurl"),(Integer)authority.get("menuParentid"));
								allLevelThreeAuthorityMap.put(authorityMenu.getMenuId(),authorityMenu);
							}
						}
						//将所有三级菜单放在二级菜单的children
						for(Map.Entry<Integer,AuthorityMenu> authority : allLevelThreeAuthorityMap.entrySet()) {
							AuthorityMenu levelThreeAuthority = authority.getValue();
							allLevelTwoAuthorityMap.get(levelThreeAuthority.getParentId()).getChildrens().add(levelThreeAuthority);
						}
						System.out.println(allLevelThreeAuthorityMap.size());
						//将所有二级菜单放在一级菜单的children
						for(Map.Entry<Integer,AuthorityMenu> authority : allLevelTwoAuthorityMap.entrySet()) {
							AuthorityMenu levelTwoAuthority = authority.getValue();
							for(AuthorityMenu levelOneAuthority : authorityMenus) {
								if(levelTwoAuthority.getParentId().toString().equals(levelOneAuthority.getMenuId().toString())) {
									levelOneAuthority.getChildrens().add(levelTwoAuthority);
								}
							}
						}
						//权限列表赋值
						for(AuthorityMenu levelOneMenu : authorityMenus) {
							PermissionMenu firstAuthMenu = new PermissionMenu(levelOneMenu.getMenuId(),levelOneMenu.getMenuName(),levelOneMenu.getMenuName(),levelOneMenu.getMenuPageurl());
							permissionMenus.add(firstAuthMenu);
							for(AuthorityMenu levelTwoMenu : levelOneMenu.getChildrens()) {
								PermissionMenu secondAuthMenu = new PermissionMenu(levelOneMenu.getMenuId(),levelOneMenu.getMenuName(),levelTwoMenu.getMenuId(),levelTwoMenu.getMenuName(),levelTwoMenu.getMenuName(),levelTwoMenu.getMenuPageurl());
								permissionMenus.add(secondAuthMenu);
								for(AuthorityMenu levelThreeMenu : levelTwoMenu.getChildrens()) {
									PermissionMenu thirdAuthMenu = new PermissionMenu(levelOneMenu.getMenuId(),levelOneMenu.getMenuName(),levelTwoMenu.getMenuId(),levelTwoMenu.getMenuName(),levelThreeMenu.getMenuId(),levelThreeMenu.getMenuName(),levelThreeMenu.getMenuName(),levelThreeMenu.getMenuPageurl());
									permissionMenus.add(thirdAuthMenu);
								}
							}
						}
					}
					userAuth.setAuthorityMenus(authorityMenus);
					userAuth.setPermissionMenus(permissionMenus);
					request.getSession().setAttribute("userAuth", userAuth); //将userAuth存入session
					mav.setViewName("home");
				} else {
					redirectAttributes.addFlashAttribute("errMessage", result.getMessage());
					mav.setViewName("redirect:/user/initlogin.do");
				}
			} catch (Exception e) {
				_log.error("loginOnSlave error",e);
				redirectAttributes.addFlashAttribute("errMessage", "登录失败，请重新登录！");
				mav.setViewName("redirect:/user/initlogin.do");
			}
		} else {
			_log.debug("用户名和密码为空"+captcha);
			redirectAttributes.addFlashAttribute("errMessage", "用户名和密码不能为空！");
			mav.setViewName("redirect:/user/initlogin.do");
		}
		return mav;
	}
}
